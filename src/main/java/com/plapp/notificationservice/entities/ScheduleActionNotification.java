package com.plapp.notificationservice.entities;

import com.plapp.entities.greenhouse.Plant;

import java.util.Date;

public class ScheduleActionNotification {

    private long plantId;
    private long userId;
    private String plantName;
    private String plantDescription;
    private Plant.PlantHealthStatus status;
    private String imageURL;
    private String action;
    private Date date;
    private int periodicity;
    private String additionalInfo;

    public ScheduleActionNotification(long plantId, long userId, String plantName, String plantDescription, Plant.PlantHealthStatus status, String imageURL, String action, Date date, int periodicity, String additionalInfo) {
        this.plantId = plantId;
        this.userId = userId;
        this.plantName = plantName;
        this.plantDescription = plantDescription;
        this.status = status;
        this.imageURL = imageURL;
        this.action = action;
        this.date = date;
        this.periodicity = periodicity;
        this.additionalInfo = additionalInfo;
    }

    public long getPlantId() {
        return plantId;
    }

    public void setPlantId(long plantId) {
        this.plantId = plantId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getPlantDescription() {
        return plantDescription;
    }

    public void setPlantDescription(String plantDescription) {
        this.plantDescription = plantDescription;
    }

    public Plant.PlantHealthStatus getStatus() {
        return status;
    }

    public void setStatus(Plant.PlantHealthStatus status) {
        this.status = status;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(int periodicity) {
        this.periodicity = periodicity;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
