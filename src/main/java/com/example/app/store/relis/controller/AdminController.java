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
@RequestMapping("/auth")
public class AdminController {

    final StoreServiceImp storeService;

    @Autowired
    public AdminController(StoreServiceImp storeService) {
        this.storeService = storeService;
    }


    @GetMapping("/login")
    public ModelAndView allUsers() {
        return new ModelAndView("auth/login")
                .addObject("store", storeService.getListGameStoreDataBase());
    }

    @GetMapping("/add")
    public String addStoreGameGet(@ModelAttribute("Store") GameStore gameStore) {
        return "auth/addGameStore";
    }

    @GetMapping("/update/{id}")
    public ModelAndView upDateStoreGame(@ModelAttribute("Store") GameStore gameStore,
                                  @PathVariable("id") Long id) {
        return new ModelAndView("/auth/updateGameStore")
                .addObject(storeService.getLastGameStoreDataBase(id));

    }

    @PutMapping("/update")
    public String upDateStoreGamePatch(@ModelAttribute("Store") GameStore gameStore) {
        storeService.updateGameStoreDataBase(gameStore);

        return "redirect:/auth/login";
    }

    @PostMapping("/add")
    public String addStoreGame(@ModelAttribute("Store") GameStore gameStore) {
        return storeService.saveLastGameStoreDataBase(gameStore) ? "redirect:/auth/login" : "error messege";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStoreGame(@PathVariable("id") Long id) {
      storeService.deleteGameStoreDataBase(id);
      return "redirect:/auth/login";
    }
}
