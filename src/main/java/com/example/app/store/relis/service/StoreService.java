package com.example.app.store.relis.service;

import com.example.app.store.relis.model.GameStore;

import java.util.List;

public interface StoreService {
    boolean saveLastGameStoreDataBase(GameStore gameStore);

    GameStore getLastGameStoreDataBase(Long id);

    List<GameStore> getListGameStoreDataBase();

    void deleteGameStoreDataBase(Long id);

    void updateGameStoreDataBase(GameStore gameStore);

}
