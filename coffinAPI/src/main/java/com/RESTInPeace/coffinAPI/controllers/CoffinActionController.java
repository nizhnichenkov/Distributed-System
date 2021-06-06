package com.RESTInPeace.coffinAPI.controllers;

import com.RESTInPeace.coffinAPI.utils.MongoDbTalkUtils;
import com.RESTInPeace.models.Coffin;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@Log4j2
public class CoffinActionController {

    private final MongoDbTalkUtils mongoDbTalkUtils;

    @Autowired
    public CoffinActionController(MongoDbTalkUtils mongoDbTalkUtils) {
        this.mongoDbTalkUtils = mongoDbTalkUtils;
    }


    @RequestMapping(value = "/createCoffin", method = RequestMethod.POST)
    public Boolean createCoffin(@RequestBody final Coffin coffin, final HttpServletRequest httpServletRequest) {
        log.info(String.format("%s invoked createCoffin()", httpServletRequest.getRemoteAddr()));

        return mongoDbTalkUtils.createCoffin(coffin);
    }

    @RequestMapping(value = "/getCoffins", method = RequestMethod.GET)
    public ArrayList<Coffin> getCoffins(final HttpServletRequest httpServletRequest,
                                        @RequestParam(value = "search", defaultValue = "") final String search) {
        log.info(String.format("%s invoked getCoffin()", httpServletRequest.getRemoteAddr()));

        if (search.equals("")) {
            return mongoDbTalkUtils.getCoffins();

        } else {
            return mongoDbTalkUtils.getCoffins(search);
        }
    }

    @RequestMapping(value = "/removeCoffins", method = RequestMethod.GET)
    public boolean removeCoffins(@RequestParam(value = "time") final long time,
                                 final HttpServletRequest httpServletRequest) {

        log.info(String.format("<{%s}> invoked getCoffin()", httpServletRequest.getRemoteAddr()));
        log.info(String.format("Time: <{%d}>", time));

        final boolean result = mongoDbTalkUtils.removeCoffins(time);
        if (result) {
            log.info("Cleanup is SUCCESS");
        } else {
            log.info("Cleanup is FAIL");
        }

        return result;
    }

}
