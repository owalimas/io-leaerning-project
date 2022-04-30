package com.jungle.bio.one;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description 多发多收的服务端
 * @Author Jungle
 * @DATE 2022/4/30
 **/
@Slf4j
public class Server {
    public static void main(String[] args) {
        log.info("====服务端启动====");
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            Socket socket = serverSocket.accept();
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
