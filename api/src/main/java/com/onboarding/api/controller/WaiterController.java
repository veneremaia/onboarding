package com.onboarding.api.controller;

import com.onboarding.api.service.WaiterService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/waiter")
@RequiredArgsConstructor
public class WaiterController {

    private static final Logger LOG = LoggerFactory.getLogger(WaiterController.class);

    private final WaiterService waiterService;

    @PostMapping
    public ResponseEntity<Boolean> setWaiter(@RequestParam(name = "id") Integer id, @RequestParam(name = "name") String name) {
        LOG.debug("Set waiter -> id {}, name {}", id, name);
        waiterService.setWaiter(id,name);
        return ResponseEntity.ok().body(Boolean.TRUE);
    }

}
