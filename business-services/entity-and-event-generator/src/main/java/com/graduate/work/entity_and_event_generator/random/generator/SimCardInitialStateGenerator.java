package com.graduate.work.entity_and_event_generator.random.generator;

import com.github.javafaker.Address;
import com.graduate.work.entity_and_event_generator.random.data.MobileOperator;
import com.graduate.work.model.entity.SimCard;
import com.graduate.work.model.entity.Task;
import com.graduate.work.model.types.SimCardStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class SimCardInitialStateGenerator extends InitialStateGenerator<SimCard> {

    @Override
    public SimCard create() {
        String iccid = randomizer.number().numberBetween(1000000000000000L, 9999999999999999L) + "";
        SimCardStatus simCardStatus = SimCardStatus.INACTIVE;
        String defNumber = randomizer.phoneNumber().phoneNumber().replaceAll("[^0-9]", "");
        String mobileOperator = MobileOperator.getRandom().name();
        String tariff = randomizer.space().star() + "-" + randomizer.number().numberBetween(1, 10);
        long lastActionDate = randomizer.date().past(365, TimeUnit.MILLISECONDS).getTime();
        Address fakeHome = randomizer.address();
        double latitude = Double.parseDouble(fakeHome.latitude().replace(",", "."));
        double longitude = Double.parseDouble(fakeHome.longitude().replace(",", "."));
        Point2D.Double lastLocation = new Point2D.Double(latitude, longitude);
        Double trafficForYesterday = randomizer.number().randomDouble(2, 0, 10000);
        Map<Long, Task> tasksMap = new HashMap<>();
        return SimCard.builder()
                .iccid(iccid)
                .simCardStatus(simCardStatus)
                .defNumber(defNumber)
                .mobileOperator(mobileOperator)
                .tariff(tariff)
                .lastActionDate(lastActionDate)
                .lastLocation(lastLocation)
                .trafficForYesterday(trafficForYesterday)
                .tasks(tasksMap)
                .build();
    }

}