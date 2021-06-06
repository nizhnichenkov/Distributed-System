package com.RESTInPeace.ui.utils;

import com.RESTInPeace.models.ProductRecord;
import com.RESTInPeace.ui.constants.RIPAPIConstants;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

@Log4j2
public class RIPAPIUtils {

    private final RestTemplate restTemplate;

    @Autowired
    public RIPAPIUtils(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ProductRecord[] getRecords(@NonNull final String request) {

        return restTemplate.getForObject(
                String.format("%s?searchString=%s",
                        RIPAPIConstants.RIP_API_URL_GET_RECORD,
                        request),
                ProductRecord[].class);
    }

    public void setRecord(@NonNull final ProductRecord record) throws Exception {
        final Boolean result = restTemplate.postForObject(RIPAPIConstants.RIP_API_URL_SET_RECORD,
                new HttpEntity<>(record),
                boolean.class);
        if (!result){
            throw new Exception("ooops somethign went wrong, please try again");
        }
    }
}
