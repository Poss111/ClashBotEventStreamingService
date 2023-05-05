package com.poss.clash.bot.ws.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

@Component
@Slf4j
public class UnsubscribeEventListener implements ApplicationListener<SessionUnsubscribeEvent> {

    @Override
    public void onApplicationEvent(SessionUnsubscribeEvent event) {
        GenericMessage message = (GenericMessage) event.getMessage();
        MessageHeaders headers = message.getHeaders();

        log.trace("Unsubscribed {}", headers);
    }

}
