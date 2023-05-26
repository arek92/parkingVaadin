package org.galus.parking.repository;

import org.galus.parking.entity.Samochod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ParkingRepository extends JpaRepository<Samochod,Long> {

        Samochod findByRegistrationNumber(String registrationNumber);

}
