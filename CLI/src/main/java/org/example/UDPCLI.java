package org.example;

import org.example.common.ApplicationStatus;
import org.example.common.network.Request;
import org.example.common.network.Response;
import org.example.common.network.serialize.Serializer;
import org.example.common.console.*;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class UDPCLI {
    ConsoleOutputHandler coh = new ConsoleOutputHandler();
    Serializer serializer = new Serializer();


    public Response go1() {
        try {
            byte[] data = getSerializeRequest();

            int port = 1112;
            InetAddress host = InetAddress.getByName("localhost");
            DatagramChannel dc = DatagramChannel.open();
            dc.configureBlocking(false);
            log.info("поток на клиенте открыт");

            ByteBuffer buf = ByteBuffer.wrap(data);
            log.info("запрос записан в буфер");
            SocketAddress addr = new InetSocketAddress(host, port);
            dc.send(buf, addr);
            log.info("данные отправлены на сервер");

            buf.clear();
            long startTime = System.currentTimeMillis();
            while (dc.receive(buf) == null) {
                if (System.currentTimeMillis() - startTime > 30000) {
                    log.error("ошибка, сервер не отвечал слишком долго");
                    return new Response(ApplicationStatus.ERROR, "ответ от сервера не был получен");
                }
                log.info("клиент ждет ответа");
                Thread.sleep(1000);
            }

            log.info("ответ от сервера получен");
            buf.flip();
            byte[] deser = new byte[buf.remaining()];
            for (int i = 0; buf.hasRemaining(); i++) {
                deser[i] = buf.get();
            }
            log.info("байты успешно получены");
            Response response = serializer.deserialize(deser);
            log.info("объект успешно десериализован");
            return response;
        } catch (Exception e){
            coh.printError(e.toString());
            return new Response(ApplicationStatus.ERROR, e.getMessage());
        }
    }

    public void go() {
        try {
            byte arr[] = {0,1,2,3,4,5,6,7,8,9};
            int len = 10;
            DatagramChannel dc; ByteBuffer buf;
            InetAddress host = InetAddress.getByName("localhost"); int port = 1112;
            SocketAddress addr;
            addr = new InetSocketAddress(host,port);
            dc = DatagramChannel.open();
            buf = ByteBuffer.wrap(arr);
            dc.send(buf, addr);
            buf.clear();
            addr = dc.receive(buf);
            for (byte j : arr) {
                coh.printLine(String.valueOf(j));
            }
        } catch (Exception e) {
            coh.printError(e.getClass() + e.getMessage());
        }


    }

    private byte[] getSerializeRequest() throws IOException{
        Request request = new Request("add");
        byte[] ans = serializer.serialize(request);
        log.info("Запрос сериализован");
        return ans;
    }

}
