package com.iad.mom.opcua.client.momopcuaclient.util;

import com.google.common.collect.Sets;
import org.slf4j.LoggerFactory;

import java.net.*;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;

public class HostnameUtil {
    public HostnameUtil() {
    }

    public static String getHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException var1) {
            return "localhost";
        }
    }

    public static Set<String> getHostnames(String address) {
        return getHostnames(address, true);
    }

    public static Set<String> getHostnames(String address, boolean includeLoopback) {
        Set<String> hostnames = Sets.newHashSet();

        try {
            InetAddress inetAddress = InetAddress.getByName(address);
            if (inetAddress.isAnyLocalAddress()) {
                try {
                    Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
                    Iterator var5 = Collections.list(nis).iterator();

                    while(var5.hasNext()) {
                        NetworkInterface ni = (NetworkInterface)var5.next();
                        Collections.list(ni.getInetAddresses()).forEach((ia) -> {
                            if (ia instanceof Inet4Address && (includeLoopback || !ia.isLoopbackAddress())) {
                                hostnames.add(ia.getHostName());
                                hostnames.add(ia.getHostAddress());
                                hostnames.add(ia.getCanonicalHostName());
                            }

                        });
                    }
                } catch (SocketException var7) {
                    LoggerFactory.getLogger(HostnameUtil.class).warn("Failed to NetworkInterfaces for bind address: {}", address, var7);
                }
            } else if (includeLoopback || !inetAddress.isLoopbackAddress()) {
                hostnames.add(inetAddress.getHostName());
                hostnames.add(inetAddress.getHostAddress());
                hostnames.add(inetAddress.getCanonicalHostName());
            }
        } catch (UnknownHostException var8) {
            LoggerFactory.getLogger(HostnameUtil.class).warn("Failed to get InetAddress for bind address: {}", address, var8);
        }

        return hostnames;
    }
}