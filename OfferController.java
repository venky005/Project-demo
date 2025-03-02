package com.offer.management.controller;

import com.offer.management.model.Offer;
import com.offer.management.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @PostMapping("/add")
    public ResponseEntity<Offer> addOffer(@RequestBody Offer offer, @RequestParam String adminUsername) {
        // Verify if the user is a valid admin
        if (!isValidAdmin(adminUsername)) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(offerService.addOffer(offer));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Offer>> getAllOffers() {
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    private boolean isValidAdmin(String adminUsername) {
        return "admin".equals(adminUsername); // Example logic
    }
}
