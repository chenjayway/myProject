package com.ctb.model;

import java.util.Date;

public class OilPrice {
    private Integer priceId;

    private String priceCity;

    private String priceNinetyGasoline;

    private String priceNinetyThreeGasoline;

    private String priceNinetySevenGasoline;

    private String priceZeroDiesel;

    private String priceSource;

    private Date priceInsertTime;

    private String priceNinetyFiveGasoline;

    private String priceNinetyTwoGasoline;

    private String priceEightyNineGasoline;

    private Date siteUpTime;

    private String priceTrend;

    public Integer getPriceId() {
        return priceId;
    }

    public void setPriceId(Integer priceId) {
        this.priceId = priceId;
    }

    public String getPriceCity() {
        return priceCity;
    }

    public void setPriceCity(String priceCity) {
        this.priceCity = priceCity == null ? null : priceCity.trim();
    }

    public String getPriceNinetyGasoline() {
        return priceNinetyGasoline;
    }

    public void setPriceNinetyGasoline(String priceNinetyGasoline) {
        this.priceNinetyGasoline = priceNinetyGasoline == null ? null : priceNinetyGasoline.trim();
    }

    public String getPriceNinetyThreeGasoline() {
        return priceNinetyThreeGasoline;
    }

    public void setPriceNinetyThreeGasoline(String priceNinetyThreeGasoline) {
        this.priceNinetyThreeGasoline = priceNinetyThreeGasoline == null ? null : priceNinetyThreeGasoline.trim();
    }

    public String getPriceNinetySevenGasoline() {
        return priceNinetySevenGasoline;
    }

    public void setPriceNinetySevenGasoline(String priceNinetySevenGasoline) {
        this.priceNinetySevenGasoline = priceNinetySevenGasoline == null ? null : priceNinetySevenGasoline.trim();
    }

    public String getPriceZeroDiesel() {
        return priceZeroDiesel;
    }

    public void setPriceZeroDiesel(String priceZeroDiesel) {
        this.priceZeroDiesel = priceZeroDiesel == null ? null : priceZeroDiesel.trim();
    }

    public String getPriceSource() {
        return priceSource;
    }

    public void setPriceSource(String priceSource) {
        this.priceSource = priceSource == null ? null : priceSource.trim();
    }

    public Date getPriceInsertTime() {
        return priceInsertTime;
    }

    public void setPriceInsertTime(Date priceInsertTime) {
        this.priceInsertTime = priceInsertTime;
    }

    public String getPriceNinetyFiveGasoline() {
        return priceNinetyFiveGasoline;
    }

    public void setPriceNinetyFiveGasoline(String priceNinetyFiveGasoline) {
        this.priceNinetyFiveGasoline = priceNinetyFiveGasoline == null ? null : priceNinetyFiveGasoline.trim();
    }

    public String getPriceNinetyTwoGasoline() {
        return priceNinetyTwoGasoline;
    }

    public void setPriceNinetyTwoGasoline(String priceNinetyTwoGasoline) {
        this.priceNinetyTwoGasoline = priceNinetyTwoGasoline == null ? null : priceNinetyTwoGasoline.trim();
    }

    public String getPriceEightyNineGasoline() {
        return priceEightyNineGasoline;
    }

    public void setPriceEightyNineGasoline(String priceEightyNineGasoline) {
        this.priceEightyNineGasoline = priceEightyNineGasoline == null ? null : priceEightyNineGasoline.trim();
    }

    public Date getSiteUpTime() {
        return siteUpTime;
    }

    public void setSiteUpTime(Date siteUpTime) {
        this.siteUpTime = siteUpTime;
    }

    public String getPriceTrend() {
        return priceTrend;
    }

    public void setPriceTrend(String priceTrend) {
        this.priceTrend = priceTrend == null ? null : priceTrend.trim();
    }
}