package com.fitch.mb.rest.dto;

import java.util.List;

/**
 * Created by hliu on 2016/7/6.
 */
public class CRCards implements  ApiResponse{
    private List<CRCard> crCardList;

    public List<CRCard> getCrCardList() {
        return crCardList;
    }

    public void setCrCardList(List<CRCard> crCardList) {
        this.crCardList = crCardList;
    }
}
