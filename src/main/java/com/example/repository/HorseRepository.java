package com.example.repository;

import com.example.entity.Horse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HorseRepository extends JpaRepository<Horse, Integer>, JpaSpecificationExecutor<Horse> {
    Horse findByIsWin(Boolean b);
}
