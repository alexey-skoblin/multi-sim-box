package com.graduate.work.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.awt.geom.Point2D;

// Конвертер для Point2D.Double
@Converter(autoApply = true)
public class Point2DConverter  implements AttributeConverter<Point2D.Double, String> {
    @Override
    public String convertToDatabaseColumn(Point2D.Double attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getX() + "," + attribute.getY();
    }

    @Override
    public Point2D.Double convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        String[] pieces = dbData.split(",");
        double x = Double.parseDouble(pieces[0]);
        double y = Double.parseDouble(pieces[1]);
        return new Point2D.Double(x, y);
    }
}
