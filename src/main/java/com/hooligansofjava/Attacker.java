package com.hooligansofjava;

import net.datafaker.Faker;

public interface Attacker {

    public int attack();
    public void generateRandomCharacter(Faker faker);

    public int receiveAttack(int damage);
    public int getHp();





}
