package com.yeqy.sso.util;

import com.yeqy.sso.biz.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by yeqy on 2017/9/13.
 */
public class HttpUtil {

    public static User authToken(String link) throws IOException, ClassNotFoundException {
        URL url = new URL(link);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        ObjectInputStream ois = new ObjectInputStream(connection.getInputStream());
        Object object = ois.readObject();
        if (object instanceof User)
            return (User) object;
        return null;
    }
}
