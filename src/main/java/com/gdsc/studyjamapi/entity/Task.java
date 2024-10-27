package com.gdsc.studyjamapi.entity;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String title; // Nhiem vu tuan 3 ...

  @Column(columnDefinition = "TEXT", nullable = false)
  private String description; // De bai : .......

  @Column(columnDefinition = "TEXT", nullable = false)
  private String requirement; // Yeu cau ........

  @Column(columnDefinition = "TEXT")
  private String note; // ghi chu

  @Column(columnDefinition = "TEXT", nullable = false)
  private String submission; // nop bai: .....

  @Column(nullable = false)
  private UUID eventId;
}
