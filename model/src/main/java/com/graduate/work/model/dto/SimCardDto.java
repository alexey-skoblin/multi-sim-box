package com.graduate.work.model.dto;

import com.graduate.work.model.entity.SimCard;
import com.graduate.work.model.types.FacilityStatus;
import com.graduate.work.model.types.ModemStatus;
import com.graduate.work.model.types.SimCardStatus;

import java.awt.geom.Point2D;
import java.io.Serializable;

/**
 * DTO for {@link SimCard}
 */
public record SimCardDto(
        String iccid,
        SimCardStatus simCardStatus,
        String defNumber,
        String mobileOperator,
        String tariff,
        long lastActionDate,
        Point2D.Double lastLocation,
        Double trafficForYesterday,
        String clientName,
        String clientLastName,
        String clientEmail,
        String clientIp,
        ModemStatus modemStatus,
        String modemImei,
        String modemEquipmentHostname,
        String modemEquipmentModel,
        String modemEquipmentSerialNumber,
        String modemEquipmentFacilityAddress,
        Point2D.Double modemEquipmentFacilityLocation,
        FacilityStatus modemEquipmentFacilityFacilityStatus
) implements Serializable {
}