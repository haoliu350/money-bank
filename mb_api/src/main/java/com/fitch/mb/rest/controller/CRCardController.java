package com.fitch.mb.rest.controller;

import com.fitch.mb.rest.dto.ApiResponse;
import com.fitch.mb.rest.dto.CRCard;
import com.fitch.mb.rest.service.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hliu on 2016/7/5.
 */
@RestController
public class CRCardController implements IndexController {

    @Autowired
    CardsService cardsService;

    @RequestMapping(value = "/insertCard", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ApiResponse insertCard(@RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "cost", required = false) String cost,
                                     @RequestParam(value = "rarity", required = false) String rarity,
                                     @RequestParam(value = "type", required = false) String type) {

        return cardsService.insertCard(new CRCard(name, Integer.valueOf(cost), rarity, type));

    }

    @RequestMapping(value = "/updateCard", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String updateCard( @RequestParam(value = "id", required = true) String id,
                                    @RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "cost", required = false) String cost,
                                    @RequestParam(value = "rarity", required = false) String rarity,
                                    @RequestParam(value = "type", required = false) String type) {
        CRCard c = new CRCard(name, Integer.valueOf(cost), rarity, type);
        c.setId(Integer.valueOf(id));
        boolean updateSuccess = cardsService.updateCard(c);
        if(updateSuccess){
            return "Update completed.";
        }else {
            return "Update failed.";
        }
    }

    @RequestMapping(value = "/getCard", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ApiResponse getAllCards(@RequestParam(value = "sort", required = false) String sort) {
        if(null == sort || sort.equals("") || sort == ""){
            return cardsService.getAllCards();
        } else {
            return cardsService.getAllCards(sort);
        }
    }

    @RequestMapping(value = "/getCard/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ApiResponse getAllCardById(@PathVariable String id) {
        return cardsService.getCardById(Integer.valueOf(id));

    }

    @RequestMapping(value = "/deleteCard/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String deleteCardById(@PathVariable String id){
        boolean deleteSuccess = cardsService.deleteCardById(id);
        if(deleteSuccess){
            return "Delete completed.";
        }else {
            return "Deletion failed.";
        }
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ApiResponse getOneRandomCard() {
        return cardsService.generateOneRandomCard();
    }

    @RequestMapping(value = "/random/{number}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ApiResponse getRandomCards(@PathVariable String number) {
        return cardsService.generateRandomCards(Integer.valueOf(number));
    }

}
