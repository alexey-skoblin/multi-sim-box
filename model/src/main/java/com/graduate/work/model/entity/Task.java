package com.graduate.work.model.entity;

import com.graduate.work.model.types.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
@ToString
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ToString.Exclude
    @ManyToOne
    private SimCard simCard;

    @Setter
    private long taskDate;
}
