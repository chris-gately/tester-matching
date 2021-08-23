package gately.christopher.testermatching.controllers;

import gately.christopher.testermatching.model.*;
import gately.christopher.testermatching.repositories.DeviceRepository;
import gately.christopher.testermatching.repositories.TesterRepository;
import gately.christopher.testermatching.services.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class SearchController {

    private final SearchService searchService;
    private final TesterRepository testerRepository;
    private final DeviceRepository deviceRepository;

    public SearchController(SearchService searchService, TesterRepository testerRepository, DeviceRepository deviceRepository) {
        this.searchService = searchService;
        this.testerRepository = testerRepository;
        this.deviceRepository = deviceRepository;
    }

    @GetMapping("/")
    public String searchForm(Model model) {
        List<String> countryCodes = testerRepository.getAllCountries();
        List<Device> devices = deviceRepository.findAll();

        model.addAttribute("countryCodes", countryCodes);
        model.addAttribute("devices", devices);
        model.addAttribute("searchParams", new SearchParams());

        return "index";
    }

    @PostMapping("/search")
    public String searchSubmit(@ModelAttribute SearchParams searchParams, Model model) {
        List<SearchResult> searchResults;
        List<String> countries = searchParams.getCountries();
        List<Integer> deviceIds = searchParams.getDeviceIds();

        if (countries.isEmpty() || deviceIds.isEmpty()) {
            return searchForm(model);
        }

        if (countries.get(0).equals("All")) {
            if (deviceIds.get(0) == -1) {
                searchResults = searchService.searchAll();
            } else {
                searchResults = searchService.searchDevices(deviceIds);
            }
        } else {
            if (deviceIds.get(0) == -1) {
                searchResults = searchService.searchCountries(countries);
            } else {
                searchResults = searchService.searchDevicesAndCountries(searchParams.getCountries(), searchParams.getDeviceIds());
            }
        }

        model.addAttribute("searchResults", searchResults);
        return searchForm(model);
    }
}
