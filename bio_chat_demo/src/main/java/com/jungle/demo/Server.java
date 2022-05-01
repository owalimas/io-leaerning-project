package com.jungle.demo;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: BIO模式下的 端口转发 服务端实现
 * 1. 注册端口
 * 2. 把客户端的socket连接，交给独立的线程来处理
 * 3. 把当前连接的饿虎端socket存入到在线的集合中
 * 4. 接手客户端的消息，推送给所有的在线socket客户端
 * @author: Jungle
 * @createDate: 2022/5/1 20:43
 */
@Slf4j
public class Server {
     public static List<Socket> allOnlineSocket = new ArrayList<>();

    public static void main(String[] args) {
        try {
            log.info("启动服务端！");
            ServerSocket serverSocket = new ServerSocket(9999);
            ServerClientThreadPool serverThreadPool = new ServerClientThreadPool();
            while(true){
                Socket acceptSocket = serverSocket.accept();
                allOnlineSocket.add(acceptSocket);
                ServerReaderRunnable serverReaderRunnable = new ServerReaderRunnable(acceptSocket);
                serverThreadPool.execute(serverReaderRunnable);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
