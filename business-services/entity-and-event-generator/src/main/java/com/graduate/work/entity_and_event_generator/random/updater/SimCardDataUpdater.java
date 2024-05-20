package com.graduate.work.entity_and_event_generator.random.updater;

import com.github.javafaker.Address;
import com.graduate.work.entity_and_event_generator.random.data.MobileOperator;
import com.graduate.work.model.entity.SimCard;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D;
import java.util.List;

@Service
@Getter
public class SimCardDataUpdater extends DataUpdater<SimCard> {

    RandomAction<SimCard> updateStatusAndLastActionDate = (simCard) -> {
        if (simCard.getStatus() == SimCard.Status.ACTIVE) {
            simCard.setStatus(SimCard.Status.INACTIVE);
            simCard.setModem(null);
        } else if (simCard.getStatus() == SimCard.Status.INACTIVE) {
            simCard.setStatus(SimCard.Status.ACTIVE);
        }
        simCard.setLastActionDate(System.currentTimeMillis());
        return simCard;
    };
    RandomAction<SimCard> updateDefNumber = (simCard) -> {
        simCard.setDefNumber(faker.phoneNumber().phoneNumber());
        return simCard;
    };
    RandomAction<SimCard> updateMobileOperator = (simCard) -> {
        String mobileOperator = simCard.getMobileOperator();
        while (mobileOperator.equals(simCard.getMobileOperator())) {
            mobileOperator = MobileOperator.getRandom().name();
        }
        simCard.setMobileOperator(mobileOperator);
        return simCard;
    };
    RandomAction<SimCard> updateTariff = (simCard) -> {
        simCard.setTariff(faker.space().star() + "-" + faker.number().numberBetween(1, 10));
        return simCard;
    };
    RandomAction<SimCard> updateLastLocation = (simCard) -> {
        Address fakeHome = faker.address();
        double latitude = Double.parseDouble(fakeHome.latitude().replace(",", "."));
        double longitude = Double.parseDouble(fakeHome.longitude().replace(",", "."));
        Point2D.Double lastLocation = new Point2D.Double(latitude, longitude);
        simCard.setLastLocation(lastLocation);
        return simCard;
    };
    RandomAction<SimCard> updateTrafficForYesterday = (simCard) -> {
        simCard.setTrafficForYesterday(faker.number().randomDouble(2, 0, 10000));
        return simCard;
    };

    @Override
    List<RandomAction<SimCard>> getRandomActions() {
        return List.of(
                updateStatusAndLastActionDate,
                updateDefNumber,
                updateMobileOperator,
                updateTariff,
                updateLastLocation,
                updateTrafficForYesterday
        );
    }

}
