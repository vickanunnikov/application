package ru.tinkoff.app.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.app.model.Application;
import ru.tinkoff.app.model.ApplicationXmlResponse;
import ru.tinkoff.app.service.AppService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AppController {

    @Autowired
    private AppService appService;


    @RequestMapping(value = "/application/last/xml/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    @ApiOperation(value = "Search last application, response in xml")
    public ApplicationXmlResponse findLastXml(@PathVariable("id") long id) {
        Application lastApplication = appService.getLastApplication(id);
        ApplicationXmlResponse response = new ApplicationXmlResponse(lastApplication);
        return response;
    }

    @RequestMapping(value = "/application/last/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "Search last application, response in json")
    public Map<String, String> find(@PathVariable("id") long id) {
        Application lastApplication = appService.getLastApplication(id);
        Map<String, String> responseMap = new LinkedHashMap<>();
        responseMap.put("CONTACT_ID", String.valueOf(id));
        responseMap.put("APPLICATION_ID", String.valueOf(lastApplication.getId()));
        responseMap.put("DT_CREATED", String.valueOf(lastApplication.getCreatedDate()));
        responseMap.put("PRODUCT_NAME", lastApplication.getProductName());
        return responseMap;
    }

    @RequestMapping(value = "/application/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "Search all the applications")
    public List<Map<String, String>> findAll() {
        List<Map<String, String>> responseArray = new ArrayList<>();
        List<Application> lastApplication = appService.getAllApplications();
        for (Application application : lastApplication) {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("CONTACT_ID", String.valueOf(application.getContact().getId()));
            map.put("APPLICATION_ID", String.valueOf(application.getId()));
            map.put("DT_CREATED", String.valueOf(application.getCreatedDate()));
            map.put("PRODUCT_NAME", application.getProductName());
            responseArray.add(map);
        }
        return responseArray;
    }
}
