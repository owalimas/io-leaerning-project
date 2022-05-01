package com.jungle.demo;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @description: 服务器端的请求处理 runnable实现
 * 1. 从socket中拿到当前的输入流
 * 2. 将输入流转发给在线的其他客户端
 * @author: Jungle
 * @createDate: 2022/5/1 21:00
 */
@Slf4j
public class ServerReaderRunnable implements Runnable {
    private Socket socket;

    public ServerReaderRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg;
            while ((msg = bufferedReader.readLine()) != null) {
                sendMsgToOtherClient(msg);
            }
        } catch (Exception e) {
            log.info("当前有客户端下线！");
            Server.allOnlineSocket.remove(this.socket);
            e.printStackTrace();
        }
    }

    private void sendMsgToOtherClient(String msg) throws Exception{
        for (Socket sc : Server.allOnlineSocket) {
            if(sc == this.socket){
                continue;
            }
            PrintStream printStream = new PrintStream(sc.getOutputStream());
            printStream.println(msg);
            printStream.flush();
        }
    }
}
