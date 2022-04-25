package com.trader.repository;

import com.trader.domain.Trader;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraderRepository extends MongoRepository<Trader, String> {}
