package com.fitch.mb.rest.service;

import com.fitch.mb.rest.dto.ApiResponse;
import com.fitch.mb.rest.dto.CRCard;

/**
 * Created by hliu on 2016/7/6.
 */
public interface CardsService {
    public ApiResponse getAllCards();
    public ApiResponse getAllCards(String sort);
    public ApiResponse getCardById(int id);
    public ApiResponse getCardByName(String name);
    public boolean deleteCardById(String id);
    public ApiResponse insertCard(CRCard c);
    public boolean updateCard(CRCard c);
    public ApiResponse generateOneRandomCard();
    public ApiResponse generateRandomCards(int number);
}
