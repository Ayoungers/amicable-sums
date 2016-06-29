package com.ayoungers.dto;

// Wrapper class to map keys from request body to an object
public class AmicableRequestDto {
    private Integer missionId;
    private Integer seed;

    public Integer getMissionId(){
        return missionId;
    }

    public Integer getSeed(){
        return seed;
    }

    public void setMissionId(Integer missionId){
        this.missionId = missionId;
    }

    public void setSeed(Integer seed){
        this.seed = seed;
    }
}
