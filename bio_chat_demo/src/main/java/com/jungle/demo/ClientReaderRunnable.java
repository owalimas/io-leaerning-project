package com.jungle.demo;

import javafx.scene.effect.Bloom;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @description: 客户端收到服务器的转发 信息 进行处理 runnable实现

 * @author: Jungle
 * @createDate: 2022/5/1 21:00
 */
@Slf4j
public class ClientReaderRunnable implements Runnable {
    private Socket socket;

    public ClientReaderRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg;
            while ((msg = bufferedReader.readLine()) != null) {
                log.info("收到服务器的转发：" + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
