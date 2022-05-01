package com.jungle.two;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description 伪异步io实现，关键细节：需要实现线程池，提供阻塞队列，防止大量客户端访问down机
 * 服务端没接收到一个客户端socket请求对象之后都交给一个独立的线程来处理客户端的数据交互需求
 * @Author Jungle
 * @DATE 2022/4/30
 **/
@Slf4j
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            log.info("服务器主程序开启！");
            //需要不断的接受不同客户端的请求，为每个客户端的分配一个线程去处理
            //需要创建线程池，这样从线程池中拿线程给不同的客户端使用
            HandleRequestPool handleRequestPool = new HandleRequestPool(2,4,3);
            while(true){
                Socket accept = serverSocket.accept();
                //需要把服务端的处理逻辑封装成任务对象，交给线程池处理
                ServerTaskRunnable serverTaskRunnable = new ServerTaskRunnable(accept);
                handleRequestPool.execute(serverTaskRunnable);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
