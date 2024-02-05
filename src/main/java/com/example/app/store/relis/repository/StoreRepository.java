package com.example.app.store.relis.repository;

import com.example.app.store.relis.model.GameStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<GameStore, Long> {
}
