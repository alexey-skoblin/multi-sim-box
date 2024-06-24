package com.graduate.work.entity_and_event_generator.input;

import com.graduate.work.entity_and_event_generator.service.TaskService;
import com.graduate.work.model.dto.TaskDto;
import com.graduate.work.model.dto.TaskPageDto;
import com.graduate.work.model.entity.Task;
import com.graduate.work.model.mapper.TaskMapper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
@Setter(onMethod_ = {@Autowired})
@Slf4j
public class TaskController {

    private final TaskMapper taskMapper = TaskMapper.INSTANCE;
    private TaskService taskService;

    @GetMapping
    public Iterable<TaskDto> getAll(
            int page,
            int size,
            String sortingField,
            String sortingOrder,
            String searchTaskStatus
    ) {
        TaskPageDto taskPageDto = new TaskPageDto(page, size, sortingField, sortingOrder, searchTaskStatus);
        List<Task> taskList = taskService.getAll(taskPageDto);
        return taskList.stream().map(taskMapper::toDto).toList();
    }

}
