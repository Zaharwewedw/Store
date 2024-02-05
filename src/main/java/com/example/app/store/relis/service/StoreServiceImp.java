package com.example.app.store.relis.service;

import com.example.app.store.relis.model.GameStore;
import com.example.app.store.relis.repository.StoreRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImp implements StoreService {
    private final StoreRepository repositoryUser;

    @Autowired
    public StoreServiceImp(StoreRepository repositoryUser) {
        this.repositoryUser = repositoryUser;
    }

    @Override
    public boolean saveLastGameStoreDataBase(GameStore gameStore) {
        try {
            repositoryUser.save(gameStore);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    @PostConstruct
    public void registerDefault() {
        if (repositoryUser.findAll().isEmpty()) {
            GameStore gameStore = new GameStore();
            gameStore.setGenre("multer");
            gameStore.setName("Epic");
            gameStore.setPrice(1000);
            System.out.println(saveLastGameStoreDataBase(gameStore));
        }
    }

    @Transactional(readOnly = true)
    @Override
    public GameStore getLastGameStoreDataBase(Long id) {
        return Optional.of(repositoryUser.getReferenceById(id)).get();
    }

    @Transactional(readOnly = true)
    @Override
    public List<GameStore> getListGameStoreDataBase() {
        return Optional.of(repositoryUser.findAll()).get();
    }

    @Transactional
    @Override
    public void deleteGameStoreDataBase(Long id) {
        repositoryUser.deleteById(id);
    }

    @Transactional
    @Override
    public void updateGameStoreDataBase(GameStore gameStore) {
        repositoryUser.save(gameStore);
    }
}
