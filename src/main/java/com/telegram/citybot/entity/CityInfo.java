package com.telegram.citybot.entity;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "city_info")
@ToString(of = {"id", "city", "info"})
@EqualsAndHashCode(of = {"id"})
public class CityInfo implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "city")
    private String city;
    @Column(name = "info")
    private String info;

    public CityInfo() {
    }

    public CityInfo(String city, String info) {
        this.city = city;
        this.info = info;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Идентификационный номер города: " + id + " (справочно),\n" +
                "Название: " + city +
                ",\nПерсональная заметка для Вас:\n" + info + "" +
                "\n\nЗамечательного отдыха!";
    }
}
