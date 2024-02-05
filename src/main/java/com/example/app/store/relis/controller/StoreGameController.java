package com.example.app.store.relis.controller;

import com.example.app.store.relis.service.StoreServiceImp;
import com.example.app.store.relis.model.GameStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/store")
public class StoreGameController {

    final StoreServiceImp storeService;

    @Autowired
    public StoreGameController(StoreServiceImp storeService) {
        this.storeService = storeService;
    }


    @GetMapping("/login")
    public ModelAndView allUsers() {
        return new ModelAndView("/store/login")
                .addObject("store", storeService.getListGameStoreDataBase());
    }

    @GetMapping("/add")
    public String addStoreGameGet(@ModelAttribute("Store") GameStore gameStore) {
        return "/store/addGameStore";
    }

    @GetMapping("/update/{id}")
    public ModelAndView upDateStoreGame(@ModelAttribute("Store") GameStore gameStore,
                                  @PathVariable("id") Long id) {
        return new ModelAndView("/store/updateGameStore")
                .addObject(storeService.getLastGameStoreDataBase(id));

    }

    @PutMapping("/update")
    public String upDateStoreGamePatch(@ModelAttribute("Store") GameStore gameStore) {
        storeService.updateGameStoreDataBase(gameStore);

        return "redirect:/store/login";
    }

    @PostMapping("/add")
    public String addStoreGame(@ModelAttribute("Store") GameStore gameStore) {
        return storeService.saveLastGameStoreDataBase(gameStore) ? "redirect:/store/login" : "error messege";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStoreGame(@PathVariable("id") Long id) {
      storeService.deleteGameStoreDataBase(id);
      return "redirect:/store/login";
    }
}
