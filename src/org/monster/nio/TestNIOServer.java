package org.monster.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestNIOServer {

    private final static Logger logger = Logger.getLogger(TestNIOServer.class.getName());

    private static Selector selector;

    static {
        int port = 9090;
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            ssc.bind(new InetSocketAddress(port));
            selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            if (logger.isLoggable(Level.INFO))
                logger.info(">>>>>服务器启动 >>>>>端口：" + port);
        } catch (IOException e) {
            logger.info(">>>>>服务器启动异常！");
        }
    }


    private static void start() throws IOException {
        while (true) {
            //如果ServerSocketChannel阻塞没有接收事件会一直阻塞
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            //迭代
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //如果选择器中的通道有接收能力
                if (key.isAcceptable()) {
                    //接收
                    SocketChannel channel = ((ServerSocketChannel) key.channel()).accept();
                    if (channel != null) {
                        //非阻塞
                        channel.configureBlocking(false);
                        //注册通道有读取能力
                        channel.register(selector, SelectionKey.OP_READ);
                        if (logger.isLoggable(Level.INFO)) {
                            InetSocketAddress address = (InetSocketAddress) channel.getRemoteAddress();
                            logger.info(">>>>>新的连接，主机：" + address.getHostName() +
                                    " >>>>>端口：" + address.getPort());
                        }
                    }
                }
                //如果选择器中的通道有读取能力
                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(32);
                    channel.read(buffer);
                    System.out.println(new String(buffer.array(), 0, buffer.position()));
                }
                iterator.remove();
            }
        }
    }

    public static void main(String[] args) {
        try {
            start();
        } catch (IOException e) {
            //Ignore
        }
    }

}