package ru.tinkoff.app.service;

import ru.tinkoff.app.model.Application;

import java.util.Date;
import java.util.List;

public interface AppService {

    List<Application> getAllApplications();

    List<String> getAllApplicationsById(long contactId);

    void addApplication(Date createdDate, String productName);

    Application getLastApplication(long contactId);

}
