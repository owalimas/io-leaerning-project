package com.jungle.demo;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description NIO非阻塞通信下的入门案例
 * @Author Jungle
 * @DATE 2022/5/3
 **/
@Slf4j
public class ServerNio {

    public static void main(String[] args) {
        try {
            //1.获取通道,目的是为了接受客户端的连接请求
            ServerSocketChannel openChannel = ServerSocketChannel.open();
            //设置成非阻塞模式
            openChannel.configureBlocking(false);
            //绑定连接端口
            openChannel.bind(new InetSocketAddress(9999));
            //获取selector
            Selector openSelector = Selector.open();
            //将通道注册到选择器上，开始监听 接受客户端连接事件
            openChannel.register(openSelector, SelectionKey.OP_ACCEPT);
            //轮询就绪的 channel
            while (openSelector.select() > 0) {
                //获取选择器中注册过且就绪了的通道channel
                Iterator<SelectionKey> selectorIterator = openSelector.selectedKeys().iterator();
                //遍历准好的 通道
                while (selectorIterator.hasNext()) {
                    SelectionKey selectionKey = selectorIterator.next();
                    //该通道是 接受客户端连接请求的通道
                    if (selectionKey.isAcceptable()) {
                        SocketChannel acceptChannel = openChannel.accept();
                        acceptChannel.configureBlocking(false);
                        acceptChannel.register(openSelector, SelectionKey.OP_READ);
                    } else if (selectionKey.isReadable()) {
                        //该通道是 客户端数据连接后，读取数据的通道
                        SocketChannel readChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int len = 0;
                        while ((len = readChannel.read(buffer)) > 0) {
                            buffer.flip();
                            log.info(new String(buffer.array(), 0, len));
                            buffer.clear();
                        }
                    }
                    selectorIterator.remove();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
