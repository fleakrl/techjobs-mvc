package org.launchcode.controllers;

import lombok.extern.slf4j.Slf4j;
import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Slf4j
@Controller
@RequestMapping("/search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value = "/results")
    public String results(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

        log.info("Search Type = {}, Search Term = {}", searchType, searchTerm);

        ArrayList<HashMap<String, String>> searchResults;

        if ("all".equals(searchType)) {
            searchResults = JobData.findByValue(searchTerm);
        } else {
            searchResults = JobData.findByColumnAndValue(searchType, searchTerm);
        }


        log.info("Job Data Results = {}", searchResults);

        model.addAttribute("jobs", searchResults);
        return "search";
    }

}
