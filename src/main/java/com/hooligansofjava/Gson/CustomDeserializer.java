package com.hooligansofjava.Gson;

import com.google.gson.*;
import com.hooligansofjava.Character;
import com.hooligansofjava.Warrior;
import com.hooligansofjava.Wizard;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CustomDeserializer implements JsonDeserializer<List<Character>> {

    private static Map<String, Class> map = new TreeMap<String, Class>();

    static {
        map.put("Character", Character.class);
        map.put("Wizard", Wizard.class);
        map.put("Warrior", Warrior.class);
    }

    public List<Character> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        List list = new ArrayList<Character>();
        JsonArray ja = json.getAsJsonArray();

        for (JsonElement je : ja) {

            String type = je.getAsJsonObject().get("isA").getAsString();
            Class c = map.get(type);
            if (c == null)
                throw new RuntimeException("Unknow class: " + type);
            list.add(context.deserialize(je, c));
        }

        return list;

    }


}