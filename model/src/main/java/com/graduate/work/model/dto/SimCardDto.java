package com.graduate.work.model.dto;

import com.graduate.work.model.entity.Facility;
import com.graduate.work.model.entity.Modem;
import com.graduate.work.model.entity.SimCard;

import java.awt.geom.Point2D;
import java.io.Serializable;

/**
 * DTO for {@link SimCard}
 */
public record SimCardDto(String iccid, SimCard.Status status, String defNumber, String mobileOperator, String tariff,
                         long lastActionDate, Point2D.Double lastLocation, Double trafficForYesterday,
                         String clientName, String clientLastName, String clientEmail, String clientIp,
                         Modem.Status modemStatus, String modemImei, String modemEquipmentHostname,
                         String modemEquipmentModel, String modemEquipmentSerialNumber,
                         String modemEquipmentFacilityAddress, Point2D.Double modemEquipmentFacilityLocation,
                         Facility.Status modemEquipmentFacilityStatus) implements Serializable {
}