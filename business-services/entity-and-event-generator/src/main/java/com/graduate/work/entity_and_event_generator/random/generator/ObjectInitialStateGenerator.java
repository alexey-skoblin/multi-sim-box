package com.graduate.work.entity_and_event_generator.random.generator;

import com.github.javafaker.Address;
import com.graduate.work.model.entity.Object;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D;

@Service
public class ObjectInitialStateGenerator extends InitialStateGenerator {

    @Override
    public Object create() {
        Address fakeHome = faker.address();
        String address = fakeHome.cityName() + ", " + fakeHome.streetAddress();
        double latitude = Double.parseDouble(fakeHome.latitude().replace(",", "."));
        double longitude = Double.parseDouble(fakeHome.longitude().replace(",", "."));
        Point2D.Double point = new Point2D.Double(latitude, longitude);
        Object.Status status = Object.Status.INACTIVE;
        return Object.builder()
                .address(address)
                .location(point)
                .status(status)
                .build();
    }
}
