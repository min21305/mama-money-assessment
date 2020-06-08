package co.za.mm.service.impl;

import co.za.mm.enums.Countries;
import co.za.mm.enums.CurrencyCodes;
import co.za.mm.enums.Menus;
import co.za.mm.model.MSISDNData;
import co.za.mm.model.SessionInformation;
import co.za.mm.model.USSDRequestModel;
import co.za.mm.model.USSDResponseModel;
import co.za.mm.repo.MSISDNRepo;
import co.za.mm.repo.SessionsRepo;
import co.za.mm.service.USSDService;
import co.za.mm.utils.Constants;
import co.za.mm.utils.CurrencyConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class USSDServiceImpl implements USSDService {

    private static final Logger LOGGER = LoggerFactory.getLogger(USSDServiceImpl.class);

    @Autowired
    private SessionsRepo sessionsRepo;

    @Autowired
    private MSISDNRepo msisdnRepo;

    @Override
    public USSDResponseModel processUSSDRequest(USSDRequestModel session) {
        LOGGER.info("USSDServiceImpl :: processUSSDRequest ");

        final USSDResponseModel response = new USSDResponseModel();

        final Optional<MSISDNData> msisdn = msisdnRepo.findByMsisdn(session.getMsisdn());
        final Optional<SessionInformation> sessionData2 = sessionsRepo.findBySessionIdAndStatusIsNot(session.getSessionId(), "FAILED");


        if (msisdn.isPresent() && !sessionData2.isPresent()) {
            saveSession(session, msisdn, Menus.MENU_1.getValue());
            response.setMessage(Constants.MENU_1);
            response.setSessionId(session.getSessionId());
        } else if (sessionData2.isPresent()) {

            if (findMenu(Menus.MENU_1.getValue(), session.getSessionId()).isPresent()) {

                switch (session.getUserEntry()) {
                    case Constants.CASE_1:
                        response.setMessage(String.format(Constants.MENU_2, Countries.KENYA.getValue()));
                        response.setSessionId(session.getSessionId());
                        updateMenu(sessionData2.get(), Menus.MENU_2.getValue(), CurrencyCodes.KES.getValue(), Constants.STATUS_1);
                        break;

                    case Constants.CASE_2:
                        response.setMessage(String.format(Constants.MENU_2, Countries.MALAWI.getValue()));
                        response.setSessionId(session.getSessionId());
                        updateMenu(sessionData2.get(), Menus.MENU_2.getValue(), CurrencyCodes.KES.name(), Constants.STATUS_1);
                        break;

                    default:
                        response.setMessage(Constants.ERROR_MSG_2);
                        response.setSessionId(session.getSessionId());
                        updateMenu(sessionData2.get(), Menus.FAILED.getValue(), CurrencyCodes.KES.name(), Constants.STATUS_3);
                        break;
                }
            } else if (findMenu(Menus.MENU_3.getValue(), session.getSessionId()).isPresent()) {

                switch (session.getUserEntry()) {
                    case Constants.CASE_1:
                        response.setMessage(Constants.MENU_4);
                        response.setSessionId(session.getSessionId());
                        updateMenu(sessionData2.get(), Menus.MENU_4.getValue(), null, Constants.STATUS_2);
                        break;

                    default:
                        response.setMessage(Constants.ERROR_MSG_2);
                        response.setSessionId(session.getSessionId());
                        updateMenu(sessionData2.get(), Menus.FAILED.getValue(), null, Constants.STATUS_3);
                        break;
                }
            } else {
                final Optional<SessionInformation> menu = findMenu(Menus.MENU_2.getValue(), session.getSessionId());
                if (menu.isPresent()) {
                    final SessionInformation sessionInformation = menu.get();
                    if (sessionInformation.getCountryCode().equalsIgnoreCase(Countries.KENYA.getValue())) {
                        response.setMessage(String.format(Constants.MENU_3,
                                CurrencyConverter.randToKWS(session.getUserEntry()), CurrencyCodes.KES.name()));
                    } else {
                        response.setMessage(String.format(Constants.MENU_3,
                                CurrencyConverter.randToKWS(session.getUserEntry()), CurrencyCodes.MWK.name()));
                    }
                    response.setSessionId(session.getSessionId());
                    updateMenu(sessionData2.get(), Menus.MENU_3.getValue(), null, Constants.STATUS_1);
                }
            }
        } else {
            response.setMessage(String.format(Constants.ERROR_MSG_1, session.getMsisdn()));
            response.setSessionId(session.getSessionId());
        }
        return response;
    }

    /**
     * This method is used to save session corresponding to msisdn.
     *
     * @param mnoSession
     * @param msisdn
     */
    private void saveSession(USSDRequestModel mnoSession, Optional<MSISDNData> msisdn, String menu) {
        final SessionInformation data = new SessionInformation();
        data.setMsisdnData(msisdn.get());
        data.setMenu(menu);
        data.setSessionId(mnoSession.getSessionId());
        data.setStatus(Constants.STATUS_1);
        sessionsRepo.save(data);
    }

    /**
     * @param data
     * @param menu
     * @param countryCode
     * @param status
     * @return
     */
    private boolean updateMenu(SessionInformation data, String menu, String countryCode, String status) {
        try {
            data.setMenu(menu);
            if (!isNull(countryCode)) {
                data.setCountryCode(countryCode);
            }
            data.setStatus(status);
            sessionsRepo.save(data);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private Optional<SessionInformation> findMenu(String menu, String sessionId) {
        final Optional<SessionInformation> byMenu = sessionsRepo.findBySessionIdAndMenu(sessionId, menu);
        return byMenu;
    }
}
