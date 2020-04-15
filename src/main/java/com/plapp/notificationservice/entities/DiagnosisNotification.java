package com.plapp.notificationservice.entities;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.plapp.entities.greenhouse.Plant;

public class DiagnosisNotification {

    private long userId;
    private long plantId;
    private String plantName;
    private String plantDescription;
    private String plantType;
    Plant.PlantHealthStatus plantHealthStatus;
    private String imageURL;
    private String disease;

    public DiagnosisNotification(long userId, long plantId, String plantName, String plantDescription, String plantType, Plant.PlantHealthStatus plantHealthStatus, String imageURL, String disease) {
        this.userId = userId;
        this.plantId = plantId;
        this.plantName = plantName;
        this.plantDescription = plantDescription;
        this.plantType = plantType;
        this.plantHealthStatus = plantHealthStatus;
        this.imageURL = imageURL;
        this.disease = disease;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPlantId() {
        return plantId;
    }

    public void setPlantId(long plantId) {
        this.plantId = plantId;
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

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public Plant.PlantHealthStatus getPlantHealthStatus() {
        return plantHealthStatus;
    }

    public void setPlantHealthStatus(Plant.PlantHealthStatus plantHealthStatus) {
        this.plantHealthStatus = plantHealthStatus;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
}
