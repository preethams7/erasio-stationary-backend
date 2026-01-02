package com.hospital.base.homepageconfig;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class HomePageService {

    private final HomePageSectionRepository repo;

    public HomePageService(HomePageSectionRepository repo) {
        this.repo = repo;
    }

  
    public List<HomePageSectionDTO> getHomePageConfig() {
        return repo.findAllByOrderByDisplayOrderAsc()
                .stream()
                .map(this::toDto)
                .toList();
    }

   
    public void saveConfig(List<HomePageSectionDTO> dtos) {
        repo.deleteAll();

        List<HomePageSection> entities = dtos.stream()
                .map(this::toEntity)
                .toList();

        repo.saveAll(entities);
    }

    private HomePageSectionDTO toDto(HomePageSection e) {
        HomePageSectionDTO d = new HomePageSectionDTO();
        d.setId(e.getSectionKey());
        d.setName(e.getName());
        d.setEnabled(e.isEnabled());
        d.setOrder(e.getDisplayOrder());
        return d;
    }

    private HomePageSection toEntity(HomePageSectionDTO d) {
        HomePageSection e = new HomePageSection();
        e.setSectionKey(d.getId());
        e.setName(d.getName());
        e.setEnabled(d.isEnabled());
        e.setDisplayOrder(d.getOrder());
        return e;
    }
}
