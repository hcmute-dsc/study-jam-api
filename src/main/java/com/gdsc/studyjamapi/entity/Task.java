package com.gdsc.studyjamapi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    @CollectionTable(
            name = "task_requirements",
            joinColumns = @JoinColumn(name = "task_id")
    )
    @Column(name = "requirement")
    private List<String> requirement;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @Column(nullable = false)
    private String eventId;
}
