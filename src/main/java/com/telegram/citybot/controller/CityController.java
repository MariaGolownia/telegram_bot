package com.telegram.citybot.controller;
import com.telegram.citybot.entity.CityInfo;
import com.telegram.citybot.repo.CityInfoRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
//http://localhost:8080/info
@RequestMapping("info")
public class CityController {
    @Autowired
    private final CityInfoRepo cityInfoRepo;

    public CityController() {
        cityInfoRepo = null;
    }

    @GetMapping
    public List<CityInfo> list() {
        return cityInfoRepo.findAll();
    }

    @GetMapping("/id/{id}")
    public Optional<CityInfo> getOneID(@PathVariable("id") String id, CityInfo cityInfo) {
        return cityInfoRepo.findById(Integer.parseInt(id));
    }

    @GetMapping("/city/{city}")
    public Optional<CityInfo> getOneCity(@PathVariable("city") String city, CityInfo info) {
        return cityInfoRepo.findByCity(city);
    }

    @PostMapping
    public CityInfoRepo create (@RequestBody CityInfo info) {
        cityInfoRepo.save(info);
        return cityInfoRepo;
    }

    @PutMapping("{city}")
    public CityInfo update  (@PathVariable ("city") CityInfo infoFromDB,
                             @RequestBody CityInfo info){
        BeanUtils.copyProperties(info, infoFromDB, "city");
        return cityInfoRepo.save(infoFromDB);
    }

    @DeleteMapping("{city}")
    public void delete (@PathVariable ("city") CityInfo infoDel) {
        cityInfoRepo.delete(infoDel);
    }



// Справочно
// Формат JSON: {"Рим":"Все дороги ведут туда","Москва":"Красная площадь","Минск":"Национальная библиотека"}
//    private List<Map<String, String>> cityInfoList = new ArrayList(){{
//        add(new HashMap<String, String>(){{
//            put("city", "Москва");
//            put("info", "Красная площадь");
//        }});
//        add(new HashMap<String, String>(){{
//            put("city", "Минск");
//            put("info", "Национальная библиотека");}});
//        add(new HashMap<String, String>(){{
//            put("city", "Рим");
//            put("info", "Все дороги ведут туда");}});
//    }};

    // Справочно
    // Формат: [{"Москва":"Красная площадь"},{"Минск":"Национальная библиотека"},{"Рим":"Все дороги ведут туда"}]
    //    Map <String, String> cityInfoMap = new HashMap(){{
    //    put("Москва", "Красная площадь");
    //    put("Минск", "Национальная библиотека");
    //    put("Рим", "Все дороги ведут туда");
    //}};

//    @GetMapping
//    public List<Map<String, String>> list() {
//        return cityInfoList;
//
//    }
//
//    @GetMapping("{city}")
//    public Map<String, String> getOne(@PathVariable String city) {
//        return getInfo(city);
//    }
//
//    @PostMapping
//    public Map<String, String> create (@RequestBody Map <String, String> cityInfoNew) {
//        cityInfoList.add(cityInfoNew);
//        return cityInfoNew;
//    }
//
//    @PutMapping("{city}")
//    public Map<String, String> update  (@PathVariable String city, @RequestBody Map <String, String> cityInfoUpdate){
//        Map<String, String> infoFromDB = getInfo(city);
//        infoFromDB.putAll(cityInfoUpdate);
//        infoFromDB.put("city", city);
//        return infoFromDB;
//    };
//
//    @DeleteMapping("{city}")
//    public void delete (@PathVariable String city) {
//        Map<String, String> cityDel = getInfo(city);
//        cityInfoList.remove(cityDel);
//    }
//
//
//
//    public Map<String, String> getInfo(@PathVariable String city) {
//        return cityInfoList.stream()
//                .filter(cityInfoList -> cityInfoList.get("city").equals(city))
//                .findFirst()
//                .orElseThrow(NotFoundException::new);
//    }
}
