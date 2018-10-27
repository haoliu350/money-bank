package com.fitch.mb.rest.dao;

import com.fitch.mb.rest.dto.ApiResponse;
import com.fitch.mb.rest.dto.CRCard;

import java.util.List;

/**
 * Created by hliu on 2016/7/6.
 */
public interface CardsOracleDao {

    public ApiResponse getAllCards();
    public ApiResponse getCardById(int id);
    public ApiResponse getCardByName(String name);
    public boolean insertCard(String name, int cost, String rarity, String type);
    public boolean deleteCardById(int id);
    //public int[] getIds();
    public List<Integer> getIds();
    public boolean updateCard(CRCard c);
}
