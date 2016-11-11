package com.sudichina.shipperclient.bean;

/**
 * Created by SudiChina-105 on 2016/10/12.
 */

public class SourchCity {
    private String name;
    private String city_code;

    public SourchCity(String name, String city_code) {
        this.name = name;
        this.city_code = city_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }
}
