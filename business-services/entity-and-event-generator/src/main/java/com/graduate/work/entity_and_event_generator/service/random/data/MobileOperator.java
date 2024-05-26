package com.graduate.work.entity_and_event_generator.service.random.data;

public enum MobileOperator {
    Beeline,
    MTS,
    Tele2,
    Vodafone,
    Yota,
    Tinkoff;

    public static MobileOperator getRandom() {
        return MobileOperator.values()[new java.util.Random().nextInt(MobileOperator.values().length)];
    }
}
