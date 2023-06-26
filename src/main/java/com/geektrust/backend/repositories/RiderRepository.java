package com.geektrust.backend.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.geektrust.backend.entities.Rider;

public class RiderRepository implements IRiderRepository {

    private static Map<String, Rider> riderMap;
    
    public RiderRepository(){
        riderMap = new HashMap<String,Rider>();
    }

    @Override
    public Rider save(Rider entity) {
       return riderMap.put(entity.getId(), entity);
    }

    @Override
    public List<Rider> findAll() {
       List<Rider> riders = new ArrayList<Rider>();
       for (Rider rider: riderMap.values()) {
          riders.add(rider);
       }
       return riders;
    }

    @Override
    public Optional<Rider> findById(String id) {
        return Optional.ofNullable(riderMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        if (riderMap.containsKey(id)) {
            return true;
        }
        return false;
    }
}
