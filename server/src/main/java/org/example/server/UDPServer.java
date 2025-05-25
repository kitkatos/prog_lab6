package org.example.server;

import org.example.common.ApplicationStatus;
import org.example.common.console.ConsoleOutputHandler;
import org.example.common.network.Request;
import org.example.common.network.Response;
import org.example.common.network.serialize.Serializer;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class UDPServer {
    ConsoleOutputHandler coh = new ConsoleOutputHandler();
    Serializer serializer = new Serializer();

    public void goo1(){
        ByteBuffer buf = ByteBuffer.allocate(4096);
        DatagramChannel dc;
        try {
            int port = 1112;
            SocketAddress addr = new InetSocketAddress(port);
            dc = DatagramChannel.open();
            dc.bind(addr);
            log.info("поток для получения данных сервером открыт");

            buf.clear();
            while (true) {
                Thread.sleep(1000);
                addr = dc.receive(buf);
                log.info("сервер пытается получить данные");

                if (addr != null) {
                    log.info("ненулевые данные прочитаны");
                    buf.flip();

                    byte[] deser = new byte[buf.remaining()];
                    for (int i = 0; buf.hasRemaining(); i++) {
                        deser[i] = buf.get();
                    }
                    log.info("байты успешно получены");
                    Request request = serializer.deserialize(deser);
                    log.info("запрос успешно десериализирован");

                    // сделать чтото с запросом и получить ответ

                    Response response = new Response(ApplicationStatus.RUNNING, "Все заебись");
                    log.info("получен ответ");
                    byte[] ser = serializer.serialize(response);
                    log.info("ответ сереализован");

                    buf.clear();
                    for (int i = 0; i < ser.length; i++) {
                        if (!buf.hasRemaining()) {
                            throw new BufferOverflowException();
                        }
                        buf.put(ser[i]);
                    }
                    log.info("ответ записан в буфер");
                    buf.flip();
                    dc.send(buf, addr);
                    log.info("Ответ отправлен");
                }
            }
        } catch (Exception e) {
            log.error("ошибка в обработчике запроса на сервере", e);

        }
    }

    public void goo() {
        try {
            byte arr[] = new byte[10];
            int len = arr.length;
            DatagramChannel dc;
            ByteBuffer buf;
            InetAddress host;
            int port = 1112;
            SocketAddress addr;
            addr = new InetSocketAddress(port);
            dc = DatagramChannel.open();
            dc.bind(addr);
            buf = ByteBuffer.wrap(arr);
            addr = dc.receive(buf);
            for (int j = 0; j < len; j++) {
                arr[j] *= 2;
            }
            buf.flip();
            dc.send(buf, addr);
        } catch (Exception e) {
            coh.printError(e.getClass() + e.getMessage());
        }
    }
}
