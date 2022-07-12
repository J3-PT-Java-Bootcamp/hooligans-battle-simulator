package com.hooligansofjava;

import net.datafaker.Faker;

public interface Attacker {

     int attack();
     int receiveAttack(int damage);
     int getHp();

}
