package com.isakis.lenoraisakis.config;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;


@Configuration
public class OIDGenerator implements IdentifierGenerator {
    private static final int ENCODING_BASE = 36;

    private static final int TIME_LENGTH = 8;

    private static final long IP_RESET = getPower(ENCODING_BASE, 2);

    private static final long COUNTER_RESET = getPower(ENCODING_BASE, 3);

    private static String ip = getIP();
    private static String hexTime = getHexTime();
    private static long counter = 0;

    /* calculate p'th power of n */
    private static long getPower(int n, int p) {
        long result = 1;
        for (int i = 0; i < p; i++) {
            result *= n;
        }
        return result;
    }

    private static String getIP() {
        long ip = 0;
        try {
            byte[] b = InetAddress.getLocalHost().getAddress();
            ip = ((b[3] & 0xFF) << 0)
                    & 0xFFFFFFFFL;
        } catch (UnknownHostException e) {
        }
        return Long.toString(ip + IP_RESET, ENCODING_BASE).substring(1);
    }

    private static String getHexTime() {
        String s = Long.toString(System.currentTimeMillis(), ENCODING_BASE);
        int l = s.length();
        if (l > TIME_LENGTH) {
            return s.substring(l - TIME_LENGTH);
        } else {
            return s;
        }
    }

    public static synchronized String getOID() {
        String oid = ip
                + hexTime
                + Long.toString(counter + COUNTER_RESET, ENCODING_BASE);

        counter = (counter + 1) % COUNTER_RESET;

        if (counter == 0) {
            String tempTime = getHexTime();
            while (hexTime.equalsIgnoreCase(tempTime)) {
                tempTime = getHexTime();
            }
            hexTime = tempTime;
        }

        return oid;
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return getOID();
    }
}