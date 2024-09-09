package com.faceitteam.rentapp.repository;

import com.faceitteam.rentapp.model.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {

}
