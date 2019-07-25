package com.advancedTheme.eventListenerTest;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by wangxiaodi1 on 2019/3/8.
 */
@Component
public class MailSendListener implements ApplicationListener<MailSendEvent> {
    @Override
    public void onApplicationEvent(MailSendEvent event) {
        System.out.println("向"+event.getTo()+"发送Email" );
    }
}
