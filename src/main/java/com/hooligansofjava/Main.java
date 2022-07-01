package com.hooligansofjava;

public class Main {
    public static void main(String[] args) {


        Character p1 = new Warrior(123,"Spartan", 100,2,2);



        Warrior p2 = new Warrior();
        Wizard p3 = new Wizard();


        for (int i = 0; i < 50; i++) {
            System.out.println(p3);
//            System.out.println(p2.attack());
            System.out.println(p3.receiveAttack(p3.attack()));
        }


    }
}