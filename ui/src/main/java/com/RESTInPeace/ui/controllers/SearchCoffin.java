package com.RESTInPeace.ui.controllers;

import com.RESTInPeace.models.ProductRecord;
import com.RESTInPeace.ui.utils.RIPAPIUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Log4j2
public class SearchCoffin {

        private final RIPAPIUtils ripapiUtils;

        @Autowired
        public SearchCoffin(final RIPAPIUtils ripapiUtils) {
                this.ripapiUtils = ripapiUtils;
        }

        @GetMapping({ "/searchcoffin", "/search" })
        public ModelAndView searchCoffin(final HttpServletRequest httpServletRequest,
                        @RequestParam(value = "searchString", required = false, defaultValue = "") final String searchString) {
                final ModelAndView view = new ModelAndView("searchCoffin");
                view.addObject("searchString", searchString);
                
                final ProductRecord[] recordList = ripapiUtils.getRecords(searchString);
                
                log.info(String.format("%s called a SearchCoffin page", httpServletRequest.getRemoteAddr()));
                view.addObject("records", recordList);
                return view;
        }
}
