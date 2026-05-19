package com.itmaxsoftwaretechsolutions.ai_generator_poc.dto;

public class TicketEventRequest {

    private String eventId;
    private String dateTime;
    private String cameraName;
    private String eventType;
    private String status;
    private String handlingUser;
    private String imageBase64;

    // getters and setters
    
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHandlingUser() {
        return handlingUser;
    }

    public void setHandlingUser(String handlingUser) {
        this.handlingUser = handlingUser;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}
