package com.telegram.citybot.controller;
import com.telegram.citybot.entity.CityInfo;
import com.telegram.citybot.service.CityInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
//http://localhost:8080/info
@RequestMapping("info")

public class CityInfoController {
    private CityInfoService cityInfoService;

    @Autowired
    public CityInfoController(CityInfoService cityInfoService) {
        this.cityInfoService = cityInfoService;
    }

    @GetMapping
    public List<CityInfo> list() {
        return cityInfoService.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CityInfo> getOneById(@PathVariable("id") Integer id, CityInfo cityInfo) {
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            Optional<CityInfo> result = cityInfoService.findById(id);
            if(result.isPresent()){
                return new ResponseEntity<>(result.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<CityInfo>> getAllByCity(@PathVariable("city") String city, CityInfo info) {
        if(city == null || city.trim().equals("")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            List<CityInfo> resultList = cityInfoService.findAllByCity(city);
            if(resultList!=null){
                return new ResponseEntity<List<CityInfo>>(resultList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
    }

    @PostMapping
    public ResponseEntity<CityInfo> saveCityInfo (@RequestBody CityInfo cityInfoNew) {
        if(cityInfoNew == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<CityInfo>(cityInfoService.save(cityInfoNew), HttpStatus.CREATED);
        }
    }

    @PutMapping("id/{id}")
    public ResponseEntity<CityInfo>  update  (@PathVariable ("id") Integer idUpdate, CityInfo infoFromDB,
                             @RequestBody CityInfo infoUpdate){
        if(idUpdate == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            BeanUtils.copyProperties(infoUpdate, infoFromDB, "id");
            CityInfo cityInfo = cityInfoService.findById(idUpdate).get();
            if (cityInfo.equals(null)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else {
                return new ResponseEntity<CityInfo>(cityInfoService.save(infoFromDB), HttpStatus.CREATED);
            }
        }
    }


    @DeleteMapping("id/{id}")
    public ResponseEntity<Integer> delete (@PathVariable ("id") Integer idDel) {
        if (idDel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            Optional<CityInfo> cityInfoDel = cityInfoService.findById(idDel);
            if (cityInfoDel.equals(null)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                cityInfoService.delete(cityInfoDel.get().getId());
                return new ResponseEntity<>(idDel, HttpStatus.OK);
            }
        }
    }

}
