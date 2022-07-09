package com.hooligansofjava;

public enum TypeOfCharacter {
    WARRIOR(10,50,1,10),
    WIZARD(10,50,1,50);

    public final int firstParamMin;
    public final int firstParamMax;
    public final int secondParamMin;
    public final int secondParamMax;

    TypeOfCharacter(int firstParamMin, int firstParamMax, int secondParamMin, int secondParamMax) {
        this.firstParamMin = firstParamMin;
        this.firstParamMax = firstParamMax;
        this.secondParamMin = secondParamMin;
        this.secondParamMax = secondParamMax;
    }

}
