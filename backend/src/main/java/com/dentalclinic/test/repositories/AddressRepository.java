package com.dentalclinic.test.repositories;


import com.dentalclinic.test.entities.Address;
import com.dentalclinic.test.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

   @Query("SELECT DISTINCT obj FROM Address obj WHERE "
            +":patient IS NULL OR obj.patient IN :patient")
    List<Address> findbyPatientId(Patient patient);
}
