package ru.tinkoff.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tinkoff.app.exceptions.ApplicationNotFoundException;
import ru.tinkoff.app.model.Application;
import ru.tinkoff.app.model.Contact;
import ru.tinkoff.app.repository.ApplicationRepository;
import ru.tinkoff.app.repository.ContactRepository;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppServiceImpl implements AppService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Application> getAllApplications() {
        List<Application> applications = new ArrayList<>();
        applications = applicationRepository.findAll();
        return applications;
    }

    @Override
    public List<String> getAllApplicationsById(long contactId) {
        List<String> result = new ArrayList<>();
        List<Application> contacts = applicationRepository.findByContactId(contactId);
        for (Application contact : contacts) {
            result.add(contact.getProductName());
        }
        return result;
    }

    @Override
    public void addApplication(Date createdDate, String productName) {

        Optional<Contact> contact = contactRepository.findById(1L);
        Application application = new Application();
        application.setCreatedDate(createdDate);
        application.setProductName(productName);
        application.setContact(contact.get());
        applicationRepository.save(application);

    }

    @Override
    public Application getLastApplication(long contactId) {
        List<Application> applications = new ArrayList<>();
        List<Application> sortedList = new ArrayList<>();
        applications = applicationRepository.findByContactId(contactId);
        if (applications.size() == 0) throw new ApplicationNotFoundException();
        sortedList = applications.stream().sorted(Comparator.comparing(Application::getCreatedDate).reversed()).collect(Collectors.toList());
        return sortedList.get(0);
    }
}
