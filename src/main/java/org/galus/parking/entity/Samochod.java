package org.galus.parking.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter

public class Samochod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String registrationNumber;
    private LocalDateTime entryTime;

    public Samochod(String registrationNumber, LocalDateTime entryTime) {
        this.registrationNumber = registrationNumber;
        this.entryTime = entryTime;

    }
}
