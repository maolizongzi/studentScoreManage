package com.cndym.util;



import java.net.InetAddress;

public class BaseUtils {
	private static String ip = "127.0.0.1";

	public static String getConfigFile(String file) {
    	getLocalIP();
    	if (ip.startsWith("10.1.")) {
    		file = file.replace(".properties", "-zs.properties");
    	}
    	return file;
    }

	public static void getLocalIP() { 
		if (!ip.equals("127.0.0.1")) return;
		InetAddress addr = null;
		try {  
			addr = InetAddress.getLocalHost();
			ip = addr.getHostAddress();
        } catch (Exception e) {
        	
        }
	}

}
