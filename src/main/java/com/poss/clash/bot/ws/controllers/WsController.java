package com.poss.clash.bot.ws.controllers;


import com.poss.clash.bot.openapi.model.Event;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.text.MessageFormat;

@RestController
@AllArgsConstructor
@Slf4j
public class WsController {

    private SimpMessagingTemplate simpMessagingTemplate;

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/invoke",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public Mono<ResponseEntity<Event>> quickCall(@RequestBody Event clashEventMono) {
        return Mono.just(clashEventMono)
                .flatMap(event -> Mono.fromCallable(() -> {
                    simpMessagingTemplate.convertAndSend(MessageFormat.format("/topic/server/{0}", event.getServerId()), event);
                    return event;
                }).thenReturn(event))
                .map(ResponseEntity::ok);
    }
}
