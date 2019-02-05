package ru.tinkoff.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.app.model.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {

    Contact findByApplications(long applicationId);

}
