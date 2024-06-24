package com.graduate.work.entity_and_event_generator.service;

import com.graduate.work.entity_and_event_generator.random.Randomizer;
import com.graduate.work.entity_and_event_generator.random.executor.ExecutableService;
import com.graduate.work.entity_and_event_generator.repository.jpa.TaskRepository;
import com.graduate.work.entity_and_event_generator.repository.specification.TaskSpecification;
import com.graduate.work.model.dto.TaskPageDto;
import com.graduate.work.model.entity.Client;
import com.graduate.work.model.entity.SimCard;
import com.graduate.work.model.entity.Task;
import com.graduate.work.model.types.TaskStatus;
import jakarta.transaction.Transactional;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter(onMethod_ = {@Autowired})
@Slf4j
public class TaskService implements ExecutableService<Task> {

    Randomizer randomizer;
    SimCardService simCardService;
    ClientService clientService;
    TaskRepository taskRepository;

    @Transactional
    @Override
    public Task getRandom() {
        List<Long> list = taskRepository.getAllIds();
        if (list.isEmpty()) {
            return add();
        }
        Long id = list.get(randomizer.getRandomId(list.size()));
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            return add();
        }
        return task;
    }

    @Transactional
    @Override
    public Task add() {
        SimCard simCard = simCardService.getRandom();
        Client client = clientService.getRandom();
        Task task = Task.builder()
                .status(TaskStatus.AWAITING)
                .simCard(simCard)
                .taskDate(randomizer.getRandomDate())
                .build();
        taskRepository.saveAndFlush(task);
        return task;
    }

    @Transactional
    @Override
    public Task update() {
        Task task = getRandom();
        task.setStatus(TaskStatus.COMPLETED);
        taskRepository.saveAndFlush(task);
        return task;
    }

    public List<Task> getAll(TaskPageDto taskPageDto) {
        Sort sort = Sort.by(Sort.Direction.fromString(taskPageDto.sortingOrder()), taskPageDto.sortingField());
        Specification<Task> specification = TaskSpecification.BySearchCriteria(taskPageDto.searchTaskStatus());
        Pageable pageable = PageRequest.of(taskPageDto.page(), taskPageDto.size(), sort);
        return taskRepository.findAll(pageable).toList();
    }

}
