package com.graduate.work.entity_and_event_generator.random.generator;

import com.github.javafaker.Address;
import com.graduate.work.entity_and_event_generator.random.data.MobileOperator;
import com.graduate.work.model.entity.SimCard;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D;
import java.util.concurrent.TimeUnit;

@Service
public class SimCardInitialStateGenerator extends InitialStateGenerator<SimCard> {

    @Override
    public SimCard create() {
        String iccid = randomizer.number().numberBetween(100000000000000L, 9999999999999999L) + "";
        SimCard.Status status = SimCard.Status.INACTIVE;
        String defNumber = randomizer.phoneNumber().phoneNumber();
        String mobileOperator = MobileOperator.getRandom().name();
        String tariff = randomizer.space().star() + "-" + randomizer.number().numberBetween(1, 10);
        long lastActionDate = randomizer.date().past(365, TimeUnit.MILLISECONDS).getTime();
        Address fakeHome = randomizer.address();
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