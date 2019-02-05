package ru.tinkoff.app;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.tinkoff.app.model.Application;
import ru.tinkoff.app.model.Contact;
import ru.tinkoff.app.repository.ApplicationRepository;
import ru.tinkoff.app.repository.ContactRepository;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AppApplicationTests {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() {
        try {
            Connection connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findContactByIdTest() {
        Application application = new Application();
        Contact contact = new Contact();
        contact.setId(1001L);
        application.setId(1002L);
        application.setProductName("Test");
        application.setContact(contact);
        contactRepository.save(contact);
        applicationRepository.save(application);
        Application found = applicationRepository.findByContactId(1001L).get(0);
        Assert.assertThat(found.getProductName(), is(application.getProductName()));
    }
}

