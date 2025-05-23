package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.codec.binary.Base64;
import org.springframework.scheduling.annotation.Async;
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
    
    public List<LunchDto> convertToDTOList(List<LunchMenu> entityList) {
        List<LunchDto> dtoList = new ArrayList<>();
        for (LunchMenu lunch : entityList) {
        	LunchDto dto = new LunchDto();
            dto.setMenuName(lunch.getMenuName());
            dto.setCost(lunch.getCost());
            dto.setMenuCategory(lunch.getMenuCategory());

            if (lunch.getImage() != null) {
                String base64 = Base64.encodeBase64String(lunch.getImage());
                dto.setImageBase64(base64);
            }

            dtoList.add(dto);
        }
        return dtoList;
    }
//レコメンドメニュー表示用
    @Async
    public CompletableFuture<LunchMenu> getRandomLunchAsync() {
        List<LunchMenu> allMenus = lunchRepository.findAll();
        LunchMenu selected = allMenus.get(new Random().nextInt(allMenus.size()));
        return CompletableFuture.completedFuture(selected);
    }

}
