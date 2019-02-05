package ru.tinkoff.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.app.model.Application;

import java.util.List;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Long > {
    List<Application> findByContactId(long contactId);
    List<Application> findAll();
}
