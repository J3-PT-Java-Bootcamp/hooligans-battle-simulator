package com.hooligansofjava.Gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.hooligansofjava.Character;
import com.hooligansofjava.Warrior;
import com.hooligansofjava.Wizard;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class CustomSerializer implements JsonSerializer<ArrayList<Character>> {

    private static Map<String, Class> map = new TreeMap<String, Class>();

    static {
        map.put("Character", Character.class);
        map.put("Wizard", Wizard.class);
        map.put("Warrior", Warrior.class);
    }

    @Override
    public JsonElement serialize(ArrayList<Character> src, Type typeOfSrc, JsonSerializationContext context) {
        System.out.println("fjasdklfjasñdlkfjasdñlfkj");
        if (src == null)
            return null;
        else {
            JsonArray ja = new JsonArray();
            for (Character bc : src) {
                Class c = map.get(bc.isA);
                if (c == null)
                    throw new RuntimeException("Unknow class: " + bc.isA);
                ja.add(context.serialize(bc, c));
            }
            return ja;
        }
    }
}