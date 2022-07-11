package com.hooligansofjava;

public enum TypeOfCharacter {
    WARRIOR(10, 50, 1, 10,100,200),
    WIZARD(10, 50, 1, 50,50,100);

    public final int firstParamMin;
    public final int firstParamMax;
    public final int secondParamMin;
    public final int secondParamMax;
    public final int HP_Min;
    public final int HP_Max;

    TypeOfCharacter(int firstParamMin, int firstParamMax, int secondParamMin, int secondParamMax,int hpMin, int hpMax) {
        this.firstParamMin = firstParamMin;
        this.firstParamMax = firstParamMax;
        this.secondParamMin = secondParamMin;
        this.secondParamMax = secondParamMax;
        this.HP_Min = hpMin;
        this.HP_Max = hpMax;
    }

}
