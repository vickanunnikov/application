package ru.tinkoff.app.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Application")
public class ApplicationXmlResponse {

    private String contactId;
    private String id;
    private String date;
    private String productName;

    public ApplicationXmlResponse() {
    }

    public ApplicationXmlResponse(Application application) {
        setContactId(String.valueOf(application.getContact().getId()));
        setId(String.valueOf(application.getId()));
        setDate(String.valueOf(application.getCreatedDate()));
        setProductName(application.getProductName());
    }

    public String getContactId() {
        return contactId;
    }

    @XmlElement(name = "CONTACT_ID")
    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getId() {
        return id;
    }

    @XmlElement(name = "APPLICATION_ID")
    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    @XmlElement(name = "DT_CREATED")
    public void setDate(String date) {
        this.date = date;
    }

    public String getProductName() {
        return productName;
    }

    @XmlElement(name = "PRODUCT_NAME")
    public void setProductName(String productName) {
        this.productName = productName;
    }
}
