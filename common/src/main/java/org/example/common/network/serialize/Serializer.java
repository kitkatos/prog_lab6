package org.example.common.network.serialize;

import java.io.*;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Serializer {
    public byte[] serialize(Serializable object) throws IOException{
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(object);
            byte[] ans = byteArrayOutputStream.toByteArray();
            log.info("Данные успешно сериализованы");
            return ans;
        } catch (IOException e) {
            log.error("Ошибка сериализации данных", e);
            throw new IOException();
        }
    }

    public <T extends Serializable> T deserialize(byte[] bytes) throws IOException, SerializeException{
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            Object ans = objectInputStream.readObject();
            if (!(ans instanceof Serializable)){
                log.error("Получен объект типа " + ans.getClass() + ", ожидался NetworkObject");
                throw new SerializeException("");
            }
            log.info("Данные успешно десериализованы");
            return (T) ans;
        } catch (IOException e) {
            log.error("Ошибка десериализации данных: ", e);
            throw new IOException();
        } catch (ClassNotFoundException e) {
            log.error("Невозможно преобразовать поток байт в объект", e);
            throw new SerializeException("");
        }

    }
}
