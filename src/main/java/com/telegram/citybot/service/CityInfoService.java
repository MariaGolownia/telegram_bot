package com.telegram.citybot.service;
import com.telegram.citybot.entity.CityInfo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

public interface CityInfoService {

    Optional<CityInfo> findById(Integer id);

    List<CityInfo> findAll();

    List<CityInfo> findAllByCity(String cityStr);

    CityInfo save(CityInfo cityInfo);

    void delete(Integer id);
}
