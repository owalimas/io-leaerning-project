package com.jungle.demo;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @Description NIO非阻塞通信下，客户端事件
 * @Author Jungle
 * @DATE 2022/5/3
 **/
@Slf4j
public class ClientNio {
    public static void main(String[] args) {
        try {
            SocketChannel writeChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
            writeChannel.configureBlocking(false);
            ByteBuffer buf = ByteBuffer.allocate(1024);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                log.info("客户端，请求开始！");
                String msg = scanner.nextLine();
                buf.put(("客户端：" + msg).getBytes(StandardCharsets.UTF_8));
                buf.flip();
                writeChannel.write(buf);
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
