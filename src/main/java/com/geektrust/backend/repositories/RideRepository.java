package com.geektrust.backend.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.geektrust.backend.entities.Ride;

public class RideRepository implements IRideRepository {

    private static Map<String, Ride> rideMap;
    
    public RideRepository(){
        rideMap = new HashMap<String,Ride>();
    }

    @Override
    public Ride save(Ride entity) {
        return rideMap.put(entity.getId(), entity);
    }

    @Override
    public List<Ride> findAll() {
        List<Ride> rides = new ArrayList<Ride>();
        for (Ride ride: rideMap.values()) {
           rides.add(ride);
        }
        return rides;
    }

    @Override
    public Optional<Ride> findById(String id) {
        return Optional.ofNullable(rideMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        if (rideMap.containsKey(id)) {
            return true;
        }
        return false;
    }
}
