package ru.tinkoff.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such application")
public class ApplicationNotFoundException extends RuntimeException {
}
