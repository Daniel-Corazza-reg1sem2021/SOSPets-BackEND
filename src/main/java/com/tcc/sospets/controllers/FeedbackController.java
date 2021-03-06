package com.tcc.sospets.controllers;

import com.tcc.sospets.business.models.entities.Feedback;
import com.tcc.sospets.business.models.entities.User;
import com.tcc.sospets.services.classes.FeedbackService;
import com.tcc.sospets.services.interfaces.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    IFeedbackService feedbackService;

    @GetMapping
    public List<Feedback> getFeedbacks(){
        return feedbackService.getFeedbacks();
    }

    @PostMapping
    public void saveFeedback (@RequestBody Feedback feedback, Authentication authentication){
        feedbackService.saveFeedback(feedback, (User) authentication.getPrincipal());
    }

    @GetMapping("/{feedbackId}")
    public Feedback pegaFeedback(@PathVariable("feedbackId") String feedbackId){
        return feedbackService.pegaFeedback(feedbackId);
    }

    @PutMapping("/{feedbackId}")
    public void atualizaFeedback(@PathVariable("feedbackId") String feedbackId, @RequestBody Feedback feedback){
        feedbackService.atualizaFeedback(feedback, feedbackId);
    }

    @DeleteMapping("/{feedbackId}")
    public void deletaFeedback(@PathVariable("feedbackId") String feedbackId){
        feedbackService.deletaFeedback(feedbackId);
    }
}
