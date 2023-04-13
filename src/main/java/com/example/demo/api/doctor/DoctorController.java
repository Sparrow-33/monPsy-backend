package com.example.demo.api.doctor;


import com.example.demo.exception.InvalidAppointmentSubmit;
import com.example.demo.exception.InvalidCredentialsException;
import com.example.demo.model.dto.AppointmentRequest;
import com.example.demo.model.entities.Appointment;
import com.example.demo.model.entities.Doctor;
import com.example.demo.model.service.AppointmentService;
import com.example.demo.model.service.DoctorServiceImp;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final AppointmentService appointmentService;
    private final DoctorServiceImp doctorService;
    private static final ModelMapper mapper = new ModelMapper();

    @PostMapping("/docTest")
    public ResponseEntity docTest() {

        return ResponseEntity.ok("Good");
    }

    @PostMapping("/saveAppointment")
    public ResponseEntity newAppoint(@RequestBody AppointmentRequest appointmentRequest) throws InvalidAppointmentSubmit {

        if (appointmentRequest == null) {
            throw new InvalidAppointmentSubmit("Invalid appointment data");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        Doctor currentDoctor =  doctorService.findDoctorByEmail(currentUserName);

        if (currentDoctor == null) {
            throw new InvalidCredentialsException("user not found");
        }

        Appointment appointment = mapper.map(appointmentRequest, Appointment.class);
        appointment.setDoctor(currentDoctor);
        appointmentService.newAppointment(appointment);
        return ResponseEntity.ok("appointment reated");
    }
}
