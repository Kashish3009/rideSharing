package com.geektrust.backend.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.geektrust.backend.entities.Driver;

public class DriverRepository implements IDriverRepository {

    private static Map<String, Driver> driverMap;
    
    public DriverRepository () {
        driverMap = new HashMap<String, Driver>();
    }
    @Override
    public Driver save(Driver entity) {
       return driverMap.put(entity.getId(),entity);
    }

    @Override
    public List<Driver> findAll() {
        List<Driver> drivers = new ArrayList<Driver>();
        for (Driver driver: driverMap.values()) {
            drivers.add(driver);
        }
        return drivers;
    }

    @Override
    public Optional<Driver> findById(String id) {
       return Optional.ofNullable(driverMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
       if(driverMap.containsKey(id)){
        return true;
       }
       return false;
    }
}
