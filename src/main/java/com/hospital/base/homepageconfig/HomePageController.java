package com.hospital.base.homepageconfig;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/home")
@CrossOrigin
public class HomePageController {

    private final HomePageService service;

    public HomePageController(HomePageService service) {
        this.service = service;
    }

   
    @GetMapping("/config")
    public ResponseEntity<List<HomePageSectionDTO>> getConfig() {
        return ResponseEntity.ok(service.getHomePageConfig());
    }

  
    @PostMapping("/config")
    public ResponseEntity<Void> saveConfig(
            @RequestBody List<HomePageSectionDTO> sections
    ) {
        service.saveConfig(sections);
        return ResponseEntity.ok().build();
    }
}
