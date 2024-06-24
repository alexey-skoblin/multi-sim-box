package com.graduate.work.model.mapper;

import com.graduate.work.model.dto.TaskDto;
import com.graduate.work.model.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Named("formatDate")
    static String formatDate(Long date) {
        if (date == null) {
            return "";
        }
        return String.valueOf(date);
    }

    @Mapping(target = "taskDate", source = "taskDate", qualifiedByName = "formatDate")
    @Mapping(target = "simCardIccid", expression = "java(task.getSimCard() != null ? task.getSimCard().getIccid() : \"\")")
    @Mapping(target = "simCardStatus", expression = "java(task.getSimCard() != null ? task.getSimCard().getSimCardStatus() : SimCardStatus.INACTIVE)")
    @Mapping(target = "simCardDefNumber", expression = "java(task.getSimCard() != null ? task.getSimCard().getDefNumber() : \"\")")
    @Mapping(target = "simCardMobileOperator", expression = "java(task.getSimCard() != null ? task.getSimCard().getMobileOperator() : \"\")")
    @Mapping(target = "clientName", expression = "java(task.getSimCard() != null && task.getSimCard().getClient() != null ? task.getSimCard().getClient().getName() : \"\")")
    @Mapping(target = "clientLastName", expression = "java(task.getSimCard() != null && task.getSimCard().getClient() != null ? task.getSimCard().getClient().getLastName() : \"\")")
    @Mapping(target = "clientEmail", expression = "java(task.getSimCard() != null && task.getSimCard().getClient() != null ? task.getSimCard().getClient().getEmail() : \"\")")
    TaskDto toDto(Task task);

}
