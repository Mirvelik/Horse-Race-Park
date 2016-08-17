package com.example.repository;

import com.example.entity.Money;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MoneyRepository extends CrudRepository<Money, Integer> {

    List<Money> findAllByOrderByNominalDesc();
}
