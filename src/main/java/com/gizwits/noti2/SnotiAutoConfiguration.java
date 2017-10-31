package com.gizwits.noti2;

import com.gizwits.noti2.client.Events;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by feel on 2017/10/11.
 */
@ConfigurationProperties(prefix = "snoti.boot")
public class SnotiAutoConfiguration {

    private String host = "snoti.gizwits.com";
    private int port = 2017;
    private int maxFrameLength = 8192;
    private String[] productKeys;
    private String[] authId;
    private String[] authSecret;
    private String[] subkey;
    private SubscribeMessageEvents[] events;

    /**
     * 多个productKey直接的事件使用&分隔
     */
    private String productKeyEventSplit = "&";

    /**
     * 每个事件直接使用逗号分隔。
     */
    private String eventSplit = ",";

    public SnotiAutoConfiguration() {
    }

    public SnotiAutoConfiguration(String host, int port, int maxFrameLength, String[] productKeys, String[] authId, String[] authSecret, String[] subkey) {
        this.host = host;
        this.port = port;
        this.maxFrameLength = maxFrameLength;
        this.productKeys = productKeys;
        this.authId = authId;
        this.authSecret = authSecret;
        this.subkey = subkey;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMaxFrameLength() {
        return maxFrameLength;
    }

    public void setMaxFrameLength(int maxFrameLength) {
        this.maxFrameLength = maxFrameLength;
    }

    public String[] getProductKeys() {
        return productKeys;
    }

    public void setProductKeys(String productKeys) {

        this.productKeys = productKeys.split(",");
    }

    public String[] getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId.split(",");
    }

    public String[] getAuthSecret() {
        return authSecret;
    }

    public void setAuthSecret(String authSecret) {
        this.authSecret = authSecret.split(",");
    }

    public String[] getSubkey() {
        return subkey;
    }

    public void setSubkey(String subkey) {
        this.subkey = subkey.split(",");
    }

    public boolean isEmpty() {
        return this.getProductKeys().length == 0 || this.authId.length == 0 || this.authSecret.length == 0 || this.subkey.length == 0;
    }

    public SubscribeMessageEvents[] getEvents() {
        return events;
    }

    /**
     * 每个productKey直接以&分隔,events直接以逗号分隔
     *
     * @param events
     */
    public void setEvents(String events) {

        String[] splitEvents = events.split(productKeyEventSplit);

        SubscribeMessageEvents[] list = new SubscribeMessageEvents[splitEvents.length];

        for (int i = 0; i < splitEvents.length; i++) {

            String[] split = splitEvents[i].split(eventSplit);
            List<Events> e = new ArrayList<Events>();
            for (String event : split) {
                Events ev = Events.getEvent(event);
                if (ev != null) {
                    e.add(ev);
                }
            }
            list[i] = new SubscribeMessageEvents(e);

        }

        this.events = list;
    }

    @Override
    public String toString() {
        return "SnotiAutoConfiguration{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", maxFrameLength=" + maxFrameLength +
                ", productKeys=" + Arrays.toString(productKeys) +
                ", authId=" + Arrays.toString(authId) +
                ", authSecret=" + Arrays.toString(authSecret) +
                ", subkey=" + Arrays.toString(subkey) +
                ", events=" + events +
                '}';
    }
}
