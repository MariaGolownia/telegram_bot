package com.telegram.citybot.service.impl;
import com.telegram.citybot.entity.CityInfo;
import com.telegram.citybot.repo.CityInfoRepo;
import com.telegram.citybot.service.CityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CityInfoServiceImpl implements CityInfoService {

    private CityInfoRepo cityInfoRepo;

    @Autowired
    public CityInfoServiceImpl(CityInfoRepo cityInfoRepo) {
        this.cityInfoRepo = cityInfoRepo;
    }

    @Override
    public Optional<CityInfo> findById(Integer id) {
            return cityInfoRepo.getById(id);
    }

    @Override
    public List<CityInfo> findAll() {
        return cityInfoRepo.findAll();
    }

    @Override
    public List<CityInfo> findAllByCity(String cityStr) {
        return cityInfoRepo.findByCity(cityStr);
    }

    @Override
    public CityInfo save(CityInfo cityInfo) {
        return cityInfoRepo.save(cityInfo);
    }

    @Override
    public void delete(Integer id) {
        cityInfoRepo.deleteById(id);
    }
}
