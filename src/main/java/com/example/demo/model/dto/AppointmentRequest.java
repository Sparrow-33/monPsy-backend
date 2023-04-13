package com.example.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING,  pattern = "HH:mm:ss")
    private LocalTime startTime;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING,  pattern = "HH:mm:ss")
    private LocalTime endTime;
    @JsonIgnore
    private boolean isReserved = false;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    @JsonIgnore
    private LocalDateTime created_at = LocalDateTime.now();
    @JsonIgnore
    private LocalDateTime updated_at = LocalDateTime.now();


}
