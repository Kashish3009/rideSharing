package com.geektrust.backend.entities;

import java.util.List;

public class Match extends BaseEntity {
    
    private String riderId;
    private List<String> driverIdList;

    public Match (String id ,String riderId, List<String> driverIdList) {
        this.riderId = riderId;
        this.driverIdList = driverIdList;
        this.id = id;
    }
    public String getRiderId(){
        return riderId;
    }
    public List<String> driverIdList(){
        return driverIdList;
    }
    public String getId(){
        return id;
    }
}
