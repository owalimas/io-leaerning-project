package com.jungle.two;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description 服务端的处理封装成Runnable，然后找线程池的线程来处理
 * @Author Jungle
 * @DATE 2022/4/30
 **/
@Slf4j
    public class ServerTaskRunnable implements Runnable {
        private Socket socket;

    public ServerTaskRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        log.info("====服务端启动====");
        try {
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String msg;
            while ((msg = bufferedReader.readLine()) != null) {
                log.info("服务端收到：" + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
