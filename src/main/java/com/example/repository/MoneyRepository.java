package com.example.repository;

import com.example.entity.Money;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoneyRepository extends JpaRepository<Money, Integer>, JpaSpecificationExecutor<Money> {

    List<Money> findAllByOrderByNominalDesc();
}
