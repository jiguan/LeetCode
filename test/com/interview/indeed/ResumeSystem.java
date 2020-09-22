package com.interview.indeed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResumeSystem {
    // profileId - versions
    Map<String, List<Map<String, String>>> map = new HashMap<>();

    public int update(String profileId, String field, String value) {
        List<Map<String, String>> versions = map.get(profileId);
        Map<String, String> lastVersion = versions.get(versions.size() - 1);
        if(lastVersion.containsKey(field)) {
            // new version is created
            Map<String, String> newVersion = new HashMap<>();
            newVersion.put(field, value);
            versions.add(newVersion);
        } else {
            // add new attribute into existing one
            lastVersion.put(field, value);
        }
        return versions.size();
    }

    // profile+version, serialized json
    Map<String, String> cached = new HashMap<>();
    public String get(String profileId, int version) {
        return serialize(map.get(profileId).get(version));
    }

    public String getField(String profileId, int version, String field) {
        return map.get(profileId).get(version).get(field);
    }

    private String serialize(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey() + ":" + entry.getValue());
        }
        sb.setCharAt(0, '[');
        sb.append(']');
        return sb.toString();
    }
}

class Improve {
    // profileId -<field, value with versions> 
    Map<String, Map<String, List<String>>> map = new HashMap<>();
    public int update(String profileId, String field, String value) {
        int res;
        if(map.containsKey(profileId)) {
            Map<String, List<String>> profile = map.get(profileId);
            if(profile.containsKey(field)){
                List<String> versions = profile.get(field);
                if(!versions.get(versions.size() - 1).equals(value)) {
                    // new version should be created, but all records from previous version are discarded
                    versions.add(value);
                }
            } else {
                profile.put(field, new ArrayList<>());
                profile.get(field).add(value);
            }
            res = map.get(profileId).size();
        } else {
            Map<String, List<String>> profile = new HashMap<>();
            profile.put(field, new ArrayList<>());
            profile.get(field).add(value);
            map.put(profileId, profile);
            res = 1;
        }
        return res;
    }
    
}
