package com.telegram.citybot.repo;
import com.telegram.citybot.entity.CityInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CityInfoRepo extends JpaRepository<CityInfo, Integer> {

    Optional<CityInfo> findByCity(String city);
}