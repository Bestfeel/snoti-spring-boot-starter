package com.gizwits.noti2;

import com.gizwits.noti2.client.LoginData;
import com.gizwits.noti2.client.NotiClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by feel on 2017/10/11.
 */
@Configuration
@ConditionalOnClass(NotiClient.class)
@ConditionalOnProperty(prefix = "snoti.boot", value = "enable", matchIfMissing = true)
@EnableConfigurationProperties(SnotiAutoConfiguration.class)
public class SnotiBeanAutoConfiguration {

    @Resource
    private SnotiAutoConfiguration properties;


    /**
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    protected NotiClient notiClient() {

        List<LoginData> loginData = new ArrayList<LoginData>();

        String[] productKeys = properties.getProductKeys();

        for (int i = 0; i < productKeys.length; i++) {

            loginData.add(new LoginData(productKeys[i], properties.getAuthId()[i], properties.getAuthSecret()[i], properties.getSubkey()[i], properties.getEvents()[i].getEvents()));
        }

        NotiClient notiClient = NotiClient
                .build()
                .setHost(properties.getHost())
                .setPort(properties.getPort())
                .setMaxFrameLength(properties.getMaxFrameLength())
                .login(loginData);
        notiClient.doStart();

        return notiClient;

    }
}
