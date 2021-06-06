package com.RESTInPeace.cleanAPI.schedulers;

import com.RESTInPeace.cleanAPI.constants.CoffinAPIConstants;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
@Log4j2
public class CleanScheduler {

    private final RestTemplate restTemplate;

    @Autowired
    public CleanScheduler(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedRate = 5000, initialDelay = 5000)
    public void clean() {
        try {
            // !!!!!!!!!!!!!!!! 5*60*60*1000 is 5 hours !!!!!!!!!!!!!!!!!!!
            final long latestTime = System.currentTimeMillis() - 5*60*60*1000;
            log.info((String.format("Performing clean for <{%d}>", latestTime)));

            final Boolean cleanResult = restTemplate.getForObject(
                    String.format("%s?time=%d",
                            CoffinAPIConstants.COFFIN_HOST,
                            latestTime),
                    boolean.class);

            if (cleanResult) {
                log.info("Clean SUCCESS");
            } else {
                log.info("Clean FAIL");
            }
        } catch (final Exception e) {
            log.error(e);
        }
    }
}
