package com.thebestteamever.game.serviceapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alexss8 on 24.05.2016.
 */
public class TopListMock {
    public List<Map<String, String>> testMap() {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> myMap1 = new HashMap<>();
        myMap1.put("login","Ishoma");
        myMap1.put("name", "Shoma");
        myMap1.put("rating", "10");
        Map<String, String> myMap2 = new HashMap<>();
        myMap2.put("login", "IAnna");
        myMap2.put("name", "Anna");
        myMap2.put("rating", "20");
        Map<String, String> myMap3 = new HashMap<>();
        myMap3.put("login", "IBernie");
        myMap3.put("name", "Bernie");
        myMap3.put("rating", "40");

        list.add(myMap1);
        list.add(myMap2);
        list.add(myMap3);

        return list;
    }
}
