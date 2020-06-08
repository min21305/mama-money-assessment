package co.za.mm.controller;

import co.za.mm.model.USSDRequestModel;
import co.za.mm.model.USSDResponseModel;
import co.za.mm.service.USSDService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ussd")
@Api(tags = "USSD Service")
public class USSDController {
    private static final Logger LOGGER = LoggerFactory.getLogger(USSDController.class);

    @Autowired
    private USSDService ussdService;


    @PostMapping
    public ResponseEntity<USSDResponseModel> processUSSDRequest(@RequestBody USSDRequestModel session) {
        LOGGER.info("USSDController :: processUSSDRequest with msisdn: {} & userEntry :{}", session.getMsisdn(), session.getUserEntry());
        return ResponseEntity.ok(ussdService.processUSSDRequest(session));
    }
}
