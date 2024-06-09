package com.example.email_sending_spring_boot_app.model.request;


import java.util.Arrays;

public class EmailRequest {

    private String[] toEmail;


    private String subject;

    private String eventName;
    private String eventDate;
    private String eventTime;
    private String eventLocation;
    private String eventRegistrationLink;
    private String recipientName;
    private String companyName;
    private String yourName;
    private String yourJobTitle;

    private String signature;


    public EmailRequest(String[] toEmail,
                        String subject,
                        String eventName,
                        String eventDate,
                        String eventTime,
                        String eventLocation,
                        String eventRegistrationLink,
                        String recipientName,
                        String companyName,
                        String yourName,
                        String yourJobTitle,
                        String signature) {
        this.toEmail = toEmail;
        this.subject = subject;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventLocation = eventLocation;
        this.eventRegistrationLink = eventRegistrationLink;
        this.recipientName = recipientName;
        this.companyName = companyName;
        this.yourName = yourName;
        this.yourJobTitle = yourJobTitle;
        this.signature = signature;

    }

    public EmailRequest() {

    }


    public String[] getToEmail() {
        return toEmail;
    }

    public void setToEmail(String[] toEmail) {
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventRegistrationLink() {
        return eventRegistrationLink;
    }

    public void setEventRegistrationLink(String eventRegistrationLink) {
        this.eventRegistrationLink = eventRegistrationLink;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getYourName() {
        return yourName;
    }

    public void setYourName(String yourName) {
        this.yourName = yourName;
    }

    public String getYourJobTitle() {
        return yourJobTitle;
    }

    public void setYourJobTitle(String yourJobTitle) {
        this.yourJobTitle = yourJobTitle;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "EmailRequest{" +
                "toEmail=" + Arrays.toString(toEmail) +
                ", subject='" + subject + '\'' +
                ", eventName='" + eventName + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", eventTime='" + eventTime + '\'' +
                ", eventLocation='" + eventLocation + '\'' +
                ", eventRegistrationLink='" + eventRegistrationLink + '\'' +
                ", recipientName='" + recipientName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", yourName='" + yourName + '\'' +
                ", yourJobTitle='" + yourJobTitle + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }

}
