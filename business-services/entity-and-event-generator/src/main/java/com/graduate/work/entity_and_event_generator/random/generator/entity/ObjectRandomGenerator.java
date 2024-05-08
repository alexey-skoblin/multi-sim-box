package com.graduate.work.entity_and_event_generator.random.generator.entity;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import com.graduate.work.entity_and_event_generator.random.Randomizer;
import com.graduate.work.entity_and_event_generator.random.generator.RandomGenerator;
import com.graduate.work.model.entity.Object;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.geom.Point2D;

@Component
public class ObjectRandomGenerator extends RandomGenerator {

    @Override
    public Object create() {
        Address fakeHome = faker.address();
        String address = fakeHome.cityName() + ", " + fakeHome.streetAddress();
        double latitude = Double.parseDouble(fakeHome.latitude().replace(",", "."));
        double longitude = Double.parseDouble(fakeHome.longitude().replace(",", "."));
        Point2D.Double point = new Point2D.Double(latitude, longitude);
        Object.Status status = Object.Status.BLOCKED;
        return Object.builder()
                .address(address)
                .location(point)
                .status(status)
                .build();
    }
}
