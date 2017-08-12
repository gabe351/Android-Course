package br.com.dfn.exemplohttp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.dfn.exemplohttp.model.Article;

public class Util {


    public static boolean isConnected(Context ctx) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)
                ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo =
                connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            Toast.makeText(ctx, "Sem conexão !", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    public static InputStream getStream(String pUrl) {
        try {
            URL url = new URL(pUrl);
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setDoInput(true);
            connection.connect();

            if (connection.getResponseCode() ==
                    HttpURLConnection.HTTP_OK) {
                return connection.getInputStream();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String streamToString(InputStream stream) {
        String retorno = "";
        if (stream != null) {
            byte[] bytes = new byte[1024];
            ByteArrayOutputStream baos =
                    new ByteArrayOutputStream();
            int read = 0;
            try {
                while ((read = stream.read(bytes)) > 0) {
                    baos.write(bytes, 0, read);
                }
                baos.close();
                retorno = new String(baos.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return retorno;
    }

    public static List<Article> parse(String body) {
        List<Article> articleList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(body);
            JSONArray jsonArray =
                    jsonObject.getJSONArray("articles");

            for (int x = 0; x < jsonArray.length(); x++) {
                JSONObject obj =
                        jsonArray.getJSONObject(x);

                Article article = new Article(
                        obj.getString("author"),
                        obj.getString("description"),
                        obj.getString("title"),
                        obj.getString("urlToImage"));

                articleList.add(article);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return articleList;
    }
}
