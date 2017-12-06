package com.chinhvd.controller;

import com.chinhvd.config.AmazonProperties;
import com.chinhvd.domain.Book;
import com.chinhvd.domain.Reader;
import com.chinhvd.respository.ReadingListRepository;
import com.chinhvd.service.CounterService;
import com.chinhvd.service.GaugeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Created by ChinhVD on 11/30/17.
 */
@Controller
@RequestMapping("/")
//@ConfigurationProperties(prefix="amazon")
class ReadingListController {

    private ReadingListRepository readingListRepository;
    private AmazonProperties amazonConfig;
    private CounterService counterService;
    private GaugeService gaugeService;

    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository,
                                 AmazonProperties amazonConfig,CounterService counterService,
                                 GaugeService gaugeService) {
        this.readingListRepository = readingListRepository;
        this.amazonConfig = amazonConfig;
        this.counterService = counterService;
        this.gaugeService = gaugeService;
    }

    @RequestMapping(method=RequestMethod.GET, value="/fail")
    public void fail() {
        throw new RuntimeException();
    }

    @ExceptionHandler(value=RuntimeException.class)
    @ResponseStatus(value= HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
    public String error() {
        return "error";
    }


    @RequestMapping(method=RequestMethod.GET)
    public String readersBooks(Reader reader, Model model) {
        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
            model.addAttribute("amazonID", amazonConfig.getAssociateId());
        }
        return "readingList";
    }

    @RequestMapping(method=RequestMethod.POST)
    public String addToReadingList(Reader reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);
        counterService.increment("books.saved");
        gaugeService.submit(
                "books.last.saved", System.currentTimeMillis());
        return "redirect:/{reader}";
    }

}
