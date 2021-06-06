package com.RESTInPeace.RIPAPI.controllers;

import com.RESTInPeace.RIPAPI.constants.RipConstants;
import com.RESTInPeace.models.Coffin;
import com.RESTInPeace.models.ProductRecord;
import com.RESTInPeace.models.User;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CreateContoller {
    @RequestMapping(value = "/createRecord", method = RequestMethod.POST)
    public boolean createRecord(@RequestBody ProductRecord record) {

        // step1: get create content from create application
        User newUser = record.getUserInfo();
        Coffin newCoffin = record.getCoffinInfo();

        HttpEntity<User> requestUser = new HttpEntity<>(newUser);
        HttpEntity<Coffin> requestCoffin = new HttpEntity<>(newCoffin);
        // step2: pass coffin & user into CoffinApi and UserApi
        RestTemplate restTemplate = new RestTemplate();
        String userEmail = newUser.getEmail();
        boolean responseUser = true;
        boolean responseCoffin = true;
        try {
            final User enteredUser = restTemplate.getForObject(RipConstants.USER_HOST + "/getUser?email={email}", User.class, userEmail);

            if (enteredUser != null) {
                responseCoffin = restTemplate.postForObject(RipConstants.COFFIN_HOST + "/createCoffin", requestCoffin, boolean.class);
            }
        } catch (final Exception e) {
            responseUser = restTemplate.postForObject(RipConstants.USER_HOST + "/createUser", requestUser, boolean.class);
            responseCoffin = restTemplate.postForObject(RipConstants.COFFIN_HOST + "/createCoffin", requestCoffin, boolean.class);
        }

        // step3: response a boolean
        if (!responseUser) {
            return false;
        } else if (!responseCoffin) {
            return false;
        } else
            return true;
    }
}
