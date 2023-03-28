package com.example.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
@Getter
@Setter
public class AppointmentRequest {
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;
    @JsonIgnore
    private boolean isReserved = false;
    @NotNull
    private LocalDate date;
    @JsonIgnore
    private LocalDateTime created_at = LocalDateTime.now();
    @JsonIgnore
    private LocalDateTime updated_at = LocalDateTime.now();


}
