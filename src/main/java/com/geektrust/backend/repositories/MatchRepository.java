package com.geektrust.backend.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.geektrust.backend.entities.Match;

public class MatchRepository implements IMatchRepository{
    private static Map<String, Match> matchMap;

    public MatchRepository(){
        matchMap = new HashMap<String, Match>();
    }
    @Override
    public Match save(Match entity) {
      matchMap.put(entity.getId(), entity);
      return entity;
    }

    @Override
    public List<Match> findAll() {
        List<Match> matches = new ArrayList<Match>();
        for (Match match: matchMap.values()) {
            matches.add(match);
        }
        return matches;
    }
    @Override
    public Optional<Match> findById(String id) {
        return Optional.ofNullable(matchMap.get(id));
    }
    @Override
    public boolean existsById(String id) {
        if (matchMap.containsKey(id)) {
            return true;
        }
        return false;
    }
    
}
