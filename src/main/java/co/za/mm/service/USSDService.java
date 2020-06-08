package co.za.mm.service;

import co.za.mm.model.USSDRequestModel;
import co.za.mm.model.USSDResponseModel;

public interface USSDService {

    USSDResponseModel processUSSDRequest(USSDRequestModel ussdRequestModel);
}
