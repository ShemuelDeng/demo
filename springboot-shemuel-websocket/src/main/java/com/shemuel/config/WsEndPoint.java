package com.shemuel.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dengshaoxiang on 2019/11/22 10:23
 * description:
 */
@Component
@ServerEndpoint(value = "/websocket/{key}/{value}")
public class WsEndPoint {
    private static final Logger logger = LoggerFactory.getLogger(WsEndPoint.class);
    // 存储当前建立的连接
    private static Map<String,WsEndPoint> wsEndPoints = new ConcurrentHashMap<>();

    // 当前在线的人数
    private static final AtomicInteger number = new AtomicInteger(0);

    // 当前连接
    private Session session;


    @OnOpen
    public void onOpen(@PathParam("key") String key,@PathParam("value") String value,Session session){
        logger.info("新建连接");
        this.session = session;
        wsEndPoints.put(key,this);
        number.incrementAndGet();
    }

    @OnMessage
    public void onMessage(String message,Session session){
        logger.info("收到消息:"+message);
        System.out.println(message);
        session.getBasicRemote();
    }

    @OnClose
    public void onClosed(){
        Iterator<String> iterator = wsEndPoints.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            if (!wsEndPoints.get(key).session.isOpen()){
                wsEndPoints.remove(key);
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error){
        logger.error("websocket出现错误",error.getMessage());
        error.printStackTrace();
    }
}
