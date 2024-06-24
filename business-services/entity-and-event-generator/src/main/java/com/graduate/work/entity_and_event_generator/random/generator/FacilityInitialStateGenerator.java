package com.graduate.work.entity_and_event_generator.random.generator;

import com.github.javafaker.Address;
import com.graduate.work.model.entity.Facility;
import com.graduate.work.model.types.FacilityStatus;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D;

@Service
public class FacilityInitialStateGenerator extends InitialStateGenerator<Facility> {

    @Override
    public Facility create() {
        Address fakeHome = randomizer.address();
        String address = fakeHome.cityName() + ", " + fakeHome.streetAddress();
        double latitude = Double.parseDouble(fakeHome.latitude().replace(",", "."));
        double longitude = Double.parseDouble(fakeHome.longitude().replace(",", "."));
        Point2D.Double point = new Point2D.Double(latitude, longitude);
        FacilityStatus facilityStatus = FacilityStatus.INACTIVE;
        return Facility.builder()
                .address(address)
                .location(point)
                .facilityStatus(facilityStatus)
                .build();
    }
}
