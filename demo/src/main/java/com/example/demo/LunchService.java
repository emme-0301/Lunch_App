package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class LunchService {

    private final LunchMenuRepository lunchRepository;

    public LunchService(LunchMenuRepository lunch) {
        this.lunchRepository = lunch;
    }

    public void saveLunch(LunchMenu lunch) {
        lunchRepository.save(lunch);
    }
    
    public List<LunchMenu> findAllLunch() {
        return lunchRepository.findAll();  
    }
}
