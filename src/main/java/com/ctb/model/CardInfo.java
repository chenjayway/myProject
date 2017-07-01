package com.ctb.model;

import java.io.Serializable;

public class CardInfo implements Serializable {
    private Long id;

    private String mobile;

    private String oilCardNumber;

    private Integer oilCardStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getOilCardNumber() {
        return oilCardNumber;
    }

    public void setOilCardNumber(String oilCardNumber) {
        this.oilCardNumber = oilCardNumber == null ? null : oilCardNumber.trim();
    }

    public Integer getOilCardStatus() {
        return oilCardStatus;
    }

    public void setOilCardStatus(Integer oilCardStatus) {
        this.oilCardStatus = oilCardStatus;
    }
}