package com.example.repository;

import com.example.entity.Bet;
import org.springframework.data.repository.CrudRepository;

public interface BetRepository extends CrudRepository<Bet, Integer> {
}
