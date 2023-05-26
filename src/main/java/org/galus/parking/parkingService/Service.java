package org.galus.parking.parkingService;


import com.vaadin.flow.component.notification.Notification;
import org.galus.parking.entity.Samochod;
import org.galus.parking.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDateTime;

@org.springframework.stereotype.Service
public class Service {

    private static final Duration durationGratis = Duration.ofMinutes(5);


    @Autowired
    private ParkingRepository repository;


    public void handleParkCar(String registrationNumber, LocalDateTime entryTime) {
        Samochod samochod = new Samochod(registrationNumber, entryTime);
        repository.save(samochod);
        Notification notification = new Notification("Park time started", 3000);
        notification.open();
    }


    public void handleLeftParking(String registrationNumber) {
        Samochod leftParking = repository.findByRegistrationNumber(registrationNumber);
        LocalDateTime startedTime = leftParking.getEntryTime();
        LocalDateTime leftParkingTime = LocalDateTime.now();
        Duration parkingDurationTime = Duration.between(startedTime, leftParkingTime);
        long hours = parkingDurationTime.toHours();
        long minutes = parkingDurationTime.toMinutesPart();
        long seconds = parkingDurationTime.toSecondsPart();
        String durationString = String.format("%d hours %d min %d sek", hours, minutes, seconds);

        if (parkingDurationTime.compareTo(durationGratis) <= 0) {
            Notification notification = new Notification("We have found a car with the following registration number: " +
                    leftParking.getRegistrationNumber() + ". Your time spent in the parking is: " + durationString, 6000);
            notification.open();
            Notification notification1 = new Notification("please leave parking first 5 minutes are gratis",6000);
            notification1.open();
            repository.delete(leftParking);

        } else {
            long extraMinutes = parkingDurationTime.toMinutes() - durationGratis.toMinutes();
            double additionalFee = 0.25 * extraMinutes;
            String feeString = String.format("%.2f", additionalFee);

            Notification notification = new Notification("We have found a car with the following registration number: " +
                    leftParking.getRegistrationNumber() + ". Your time spent in the parking is: " + durationString +
                    ". Additional fee for exceeding the parking time: $" + feeString, 6000);
            notification.open();
            repository.delete(leftParking);
        }


    }


}

