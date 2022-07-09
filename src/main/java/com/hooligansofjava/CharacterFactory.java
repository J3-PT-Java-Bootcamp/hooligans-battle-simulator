package com.hooligansofjava;

public class CharacterFactory {
    public static Character getCharacter(TypeOfCharacter type, int id, String name, int hp, int classFirstAttribute, int classSecondAttribute) {
        return switch (type) {
            case WARRIOR -> new Warrior(id, name, hp, classFirstAttribute, classSecondAttribute);
            case WIZARD -> new Wizard(id, name, hp, classFirstAttribute, classSecondAttribute);
        };
    }
}
