package com.hooligansofjava;

public class CharacterFactory {
    public static Character getCharacter(String type, int id, String name, int hp, int classFirstAttribute, int classSecondAttribute) {
        if (type.equalsIgnoreCase("WARRIOR")) {
            return new Warrior(id, name, hp, classFirstAttribute, classSecondAttribute);
        }
        if(type.equalsIgnoreCase("WIZARD")) {
            return new Wizard(id, name, hp, classFirstAttribute, classSecondAttribute);
        }
        return null;
    }
}
