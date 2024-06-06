package com.graduate.work.model.mapper;

import com.graduate.work.model.dto.SimCardDto;
import com.graduate.work.model.entity.SimCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SimCardMapper {
    SimCardMapper INSTANCE = Mappers.getMapper(SimCardMapper.class);

    @Named("formatDefNumber")
    static String formatDefNumber(String defNumber) {
        if (defNumber == null) {
            return "";
        }
        defNumber = defNumber.replaceAll("[^0-9]", "");
        if (defNumber.length() == 11 && defNumber.startsWith("7")) {
            return "+7(" + defNumber.substring(1, 4) + ")" + defNumber.substring(4, 7) + "-" + defNumber.substring(7, 9) + "-" + defNumber.substring(9, 11);
        }
        return defNumber;
    }

    @Mapping(target = "clientName", expression = "java(simCard.getClient() != null ? simCard.getClient().getName() : \"\")")
    @Mapping(target = "clientLastName", expression = "java(simCard.getClient() != null ? simCard.getClient().getLastName() : \"\")")
    @Mapping(target = "clientEmail", expression = "java(simCard.getClient() != null ? simCard.getClient().getEmail() : \"\")")
    @Mapping(target = "clientIp", expression = "java(simCard.getClient() != null ? simCard.getClient().getIp() : \"\")")
    @Mapping(target = "modemStatus", expression = "java(simCard.getModem() != null ? simCard.getModem().getStatus() : Modem.Status.INACTIVE)")
    @Mapping(target = "modemImei", expression = "java(simCard.getModem() != null ? simCard.getModem().getImei() : \"\")")
    @Mapping(target = "modemEquipmentHostname", expression = "java(simCard.getModem() != null" +
            " && simCard.getModem().getEquipment() != null ? simCard.getModem().getEquipment().getHostname() : \"\")")
    @Mapping(target = "modemEquipmentModel", expression = "java(simCard.getModem() != null " +
            "&& simCard.getModem().getEquipment() != null ? simCard.getModem().getEquipment().getModel() : \"\")")
    @Mapping(target = "modemEquipmentSerialNumber", expression = "java(simCard.getModem() != null" +
            " && simCard.getModem().getEquipment() != null ? simCard.getModem().getEquipment().getSerialNumber() : \"\")")
    @Mapping(target = "modemEquipmentFacilityAddress", expression = "java(simCard.getModem() != null" +
            " && simCard.getModem().getEquipment() != null && simCard.getModem().getEquipment().getFacility() != null" +
            " ? simCard.getModem().getEquipment().getFacility().getAddress() : \"\")")
    @Mapping(target = "modemEquipmentFacilityLocation", expression = "java(simCard.getModem() != null" +
            " && simCard.getModem().getEquipment() != null && simCard.getModem().getEquipment().getFacility() != null" +
            " ? simCard.getModem().getEquipment().getFacility().getLocation() : new java.awt.geom.Point2D.Double(0, 0))")
    @Mapping(target = "modemEquipmentFacilityStatus", expression = "java(simCard.getModem() != null" +
            " && simCard.getModem().getEquipment() != null && simCard.getModem().getEquipment().getFacility() != null" +
            " ? simCard.getModem().getEquipment().getFacility().getStatus() : Facility.Status.INACTIVE)")
    @Mapping(target = "defNumber", source = "defNumber", qualifiedByName = "formatDefNumber")
    SimCardDto simCardToSimCardDto(SimCard simCard);

}
