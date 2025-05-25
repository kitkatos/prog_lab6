package org.example.server;

import org.example.common.ApplicationStatus;
import org.example.common.console.ConsoleOutputHandler;
import org.example.common.network.Request;
import org.example.common.network.Response;
import org.example.common.network.serialize.Serializer;

public class TestSer {
    public static void go(){
        ConsoleOutputHandler coh = new ConsoleOutputHandler();
        try {
            Request request = new Request("add");
            Serializer serializer = new Serializer();
            byte[] arr = serializer.serialize(request);
            coh.printLine(request.toString());
            coh.printLine("запрос сериализован");
            coh.printLine(arr.toString());

            Serializer serializer1 = new Serializer();
            Request getRequest = serializer1.deserialize(arr);
            coh.printLine("запрос десериализован");
            coh.printLine(getRequest.toString());
        } catch (Exception e) {
            coh.printError(e.toString());
        }
    }

    public static void main(String[] args) {
        go();
    }
}
