package com.ctb.model;

public class OilPriceCity {
    private Integer priceCityId;

    private String priceCityName;

    private String priceCityNameFirstLetter;

    public Integer getPriceCityId() {
        return priceCityId;
    }

    public void setPriceCityId(Integer priceCityId) {
        this.priceCityId = priceCityId;
    }

    public String getPriceCityName() {
        return priceCityName;
    }

    public void setPriceCityName(String priceCityName) {
        this.priceCityName = priceCityName == null ? null : priceCityName.trim();
    }

    public String getPriceCityNameFirstLetter() {
        return priceCityNameFirstLetter;
    }

    public void setPriceCityNameFirstLetter(String priceCityNameFirstLetter) {
        this.priceCityNameFirstLetter = priceCityNameFirstLetter == null ? null : priceCityNameFirstLetter.trim();
    }

}