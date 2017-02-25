package com.florianwoelki.commu.protocol.packet;

import com.florianwoelki.commu.protocol.Packet;
import com.florianwoelki.commu.protocol.PacketType;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Florian Woelki on 24.02.17.
 */
public class PacketDictionary {

    private static final Map<PacketType, Class<? extends Packet>> PACKET_DICTIONARY = new HashMap<>();

    static {
        PACKET_DICTIONARY.put(PacketType.CONNECT, ConnectPacket.class);
        PACKET_DICTIONARY.put(PacketType.DISCONNECT, DisconnectPacket.class);
    }

    public static Packet translatePacketType(PacketType packetType, String[] data) {
        Class clazz = PACKET_DICTIONARY.get(packetType);
        if(clazz != null) {
            try {
                return (Packet) clazz.getConstructor(String[].class).newInstance((Object) data);
            } catch(InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
