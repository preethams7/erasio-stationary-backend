package com.hospital.base.homepageconfig;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HomePageSectionRepository
        extends JpaRepository<HomePageSection, Long> {

    List<HomePageSection> findAllByOrderByDisplayOrderAsc();
}
