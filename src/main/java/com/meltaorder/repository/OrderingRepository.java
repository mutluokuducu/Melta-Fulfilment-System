package com.meltaorder.repository;

import com.meltaorder.repository.entity.PersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderingRepository extends JpaRepository<PersonalDetails, Long> {

}
