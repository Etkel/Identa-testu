package com.demo.identa.models;

import com.demo.identa.models.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ordes")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss a")
    private LocalDateTime created;
    private Status status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

