package com.fitch.mb.rest.service;

import com.fitch.mb.rest.dao.CardsOracleDao;
import com.fitch.mb.rest.dto.ApiResponse;
import com.fitch.mb.rest.dto.CRCard;
import com.fitch.mb.rest.dto.CRCards;
import com.fitch.mb.rest.exception.IdNotFoundCustomException;
import com.fitch.mb.rest.exception.InsertFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by hliu on 2016/7/6.
 */

@Service
public class CardsServiceImpl implements CardsService {

    @Autowired
    CardsOracleDao cardsOracleDao;

    @Override
    public ApiResponse getAllCards() {
        CRCards cardsList = (CRCards) cardsOracleDao.getAllCards();
        //cardsList.getCrCardList().sort(CRCard.CardNameComparator);
        cardsList.getCrCardList().sort(CRCard.CardCostComparator);
        return cardsList;
    }

    @Override
    public ApiResponse getAllCards(String sort) {
        CRCards cardsList = (CRCards) cardsOracleDao.getAllCards();
        Set<CRCard> temp = cardsList.getCrCardList().stream().collect(Collectors.toSet());
        cardsList.setCrCardList(temp.stream().collect(Collectors.toList()));
        if("name".equalsIgnoreCase(sort)){
            cardsList.getCrCardList().sort(CRCard.CardNameComparator); //sort with name
        } else if ("cost".equalsIgnoreCase(sort)){
            cardsList.getCrCardList().sort(CRCard.CardCostComparator); //sort with cost
        } else if ("rarity".equalsIgnoreCase(sort)){
            cardsList.getCrCardList().sort(CRCard.CardRarityComparator); //sort with rarity, if same than sort with cost
        } else {
            Collections.sort(cardsList.getCrCardList()); //sort with id
        }
        return cardsList;
    }

    @Override
    public ApiResponse getCardById(int id) {
        return cardsOracleDao.getCardById(id);
    }

    @Override
    public ApiResponse getCardByName(String name) {
        return cardsOracleDao.getCardByName(name);
    }

    @Override
    public boolean deleteCardById(String id) {
        if(null == id || id.equalsIgnoreCase("") || id.equals(null)) {
            throw new IdNotFoundCustomException("Delete Card: Id not found Exception");
        } else{
            return cardsOracleDao.deleteCardById(Integer.valueOf(id));
        }

    }

    @Override
    public ApiResponse insertCard(CRCard c) {
        boolean insertSuccess =  cardsOracleDao.insertCard(c.getName(),c.getCost(),c.getRarity(), c.getType());
        if(insertSuccess){
            CRCards cardsList = (CRCards) cardsOracleDao.getCardByName(c.getName());
            return cardsList.getCrCardList().get(0);
        } else{
            throw new InsertFailedException("ServiceImpl: Insert failed.");
        }
    }

    @Override
    public boolean updateCard(CRCard c) {
        return cardsOracleDao.updateCard(c);
    }

    @Override
    public ApiResponse generateOneRandomCard() {
        List<Integer> cardIds = cardsOracleDao.getIds();
        int index =  new Random().nextInt(cardIds.size());
        CRCards cardsList = (CRCards) cardsOracleDao.getCardById(cardIds.get(index));
        return cardsList.getCrCardList().get(0);
    }

    @Override
    public ApiResponse generateRandomCards(int number) {
        CRCards allCards = (CRCards) cardsOracleDao.getAllCards();
        List<CRCard> cardList = allCards.getCrCardList();
        List<CRCard> result = new ArrayList<CRCard>();
        int renderingNumber = cardList.size() < number ? cardList.size() : number;
        for(int i = 0 ; i < renderingNumber ; i++ ){
            int index =  new Random().nextInt(cardList.size());
            result.add(cardList.get(index));
            cardList.remove(index);
        }
        Collections.sort(result, CRCard.CardCostComparator);
        CRCards object = new CRCards();
        object.setCrCardList(result);
        return object;
    }
}
