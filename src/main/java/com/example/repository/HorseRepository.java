package com.example.repository;

import com.example.entity.Horse;
import org.springframework.data.repository.CrudRepository;

public interface HorseRepository extends CrudRepository<Horse, Integer> {
    Horse findByIsWin(Boolean b);
}
