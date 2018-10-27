package com.fitch.mb.rest.service;

import com.fitch.mb.rest.dto.CRCard;
import com.fitch.mb.rest.dto.CRCards;
import com.fitch.mb.rest.dto.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2016/7/18.
 */

@Service
public class DeckServiceImpl implements DeckService {

    @Autowired
    CardsService cardsService;

    @Override
    public Deck getRandomDeck() {
        Deck deck = new Deck();
        CRCards crCards = (CRCards) cardsService.generateRandomCards(8);
        List<CRCard> carlist = crCards.getCrCardList();
        deck.setCardlist(crCards.getCrCardList());
        double totalcost = 0.0;
        for(CRCard c : carlist) {
            totalcost += c.getCost();
        }
        deck.setCost(totalcost/8);
        deck.setId(new Random().nextInt());  //temperaly give a random number as a ID will use database generate one finally
        return deck;
    }
}
