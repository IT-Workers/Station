package com.unitmb.station.common.http;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpUtil {

    public static final String METHOD_GET = "GET";

    public static final String METHOD_POST = "POST";

    public static byte[] getHttpByteData(String method, String url, Map<String, String> propertys) {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) (new URL(url)).openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(method);
            conn.setConnectTimeout(5000);
            if(propertys != null && propertys.size() > 0) {
                for(Map.Entry<String, String> entry : propertys.entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream input = conn.getInputStream();
                int len = 0; byte[] buff = new byte[1024];
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                while((len = input.read(buff)) != -1){
                    out.write(buff, 0, len);
                }
                input.close();
                out.close();
                return out.toByteArray();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            if(conn !=null)
                conn.disconnect();
        }
    }

}
