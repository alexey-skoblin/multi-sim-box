package com.graduate.work.entity_and_event_generator.random.generator;

import com.github.javafaker.Address;
import com.graduate.work.entity_and_event_generator.random.data.MobileOperator;
import com.graduate.work.model.entity.SimCard;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D;
import java.util.concurrent.TimeUnit;

@Service
public class SimCardInitialStateGenerator extends InitialStateGenerator {

    @Override
    public SimCard create() {
        String iccid = faker.number().numberBetween(100000000000000L, 9999999999999999L) + "";
        SimCard.Status status = SimCard.Status.INACTIVE;
        String defNumber = faker.phoneNumber().phoneNumber();
        String mobileOperator = MobileOperator.getRandom().name();
        String tariff = faker.space().star() + "-" + faker.number().numberBetween(1, 10);
        long lastActionDate = faker.date().past(365, TimeUnit.MILLISECONDS).getTime();
        Address fakeHome = faker.address();
        double latitude = Double.parseDouble(fakeHome.latitude().replace(",", "."));
        double longitude = Double.parseDouble(fakeHome.longitude().replace(",", "."));
        Point2D.Double lastLocation = new Point2D.Double(latitude, longitude);
        Double trafficForYesterday = 0.0;
        return SimCard.builder()
                .iccid(iccid)
                .status(status)
                .defNumber(defNumber)
                .mobileOperator(mobileOperator)
                .tariff(tariff)
                .lastActionDate(lastActionDate)
                .lastLocation(lastLocation)
                .trafficForYesterday(trafficForYesterday)
                .build();
    }

}