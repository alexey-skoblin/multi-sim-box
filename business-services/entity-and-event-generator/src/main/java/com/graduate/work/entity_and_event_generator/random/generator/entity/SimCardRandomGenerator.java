package com.graduate.work.entity_and_event_generator.random.generator.entity;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import com.graduate.work.entity_and_event_generator.random.Randomizer;
import com.graduate.work.entity_and_event_generator.random.generator.RandomGenerator;
import com.graduate.work.model.entity.SimCard;
import com.graduate.work.entity_and_event_generator.random.data.MobileOperator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.awt.geom.Point2D;
import java.util.concurrent.TimeUnit;

@Component
public class SimCardRandomGenerator extends RandomGenerator {

    @Override
    public SimCard create() {
        String iccid = faker.number().numberBetween(100000000000000L, 9999999999999999L) + "";
        String defNumber = faker.phoneNumber().phoneNumber();
        String mobileOperator = MobileOperator.getRandom().name();
        String tariff = faker.space().star() + "-" + faker.number().numberBetween(1, 10);
        long lastActivation = faker.date().past(365, TimeUnit.MILLISECONDS).getTime();
        Address fakeHome = faker.address();
        double latitude = Double.parseDouble(fakeHome.latitude().replace(",", "."));
        double longitude = Double.parseDouble(fakeHome.longitude().replace(",", "."));
        Point2D.Double lastLocation = new Point2D.Double(latitude, longitude);
        Double trafficForYesterday = 0.0;
        return SimCard.builder()
                .iccid(iccid)
                .defNumber(defNumber)
                .mobileOperator(mobileOperator)
                .tariff(tariff)
                .lastActivationDate(lastActivation)
                .lastLocation(lastLocation)
                .trafficForYesterday(trafficForYesterday)
                .build();
    }
}