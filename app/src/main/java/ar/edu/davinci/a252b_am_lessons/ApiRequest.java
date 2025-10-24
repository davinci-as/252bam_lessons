package ar.edu.davinci.a252b_am_lessons;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiRequest extends AsyncTask<String, Integer, String> {
    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        String url = strings[0];
        try {
            String objectJson = run(url);
            Log.i("json-object", objectJson);
            return objectJson;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject alienRick = new JSONObject(s);
            //JSONObject info = jsonObject.getJSONObject("info");
            //JSONArray results = jsonObject.getJSONArray("results");
            //JSONObject alienRick = results.getJSONObject(14);
            String alienRickName = "default";
            String alienRickUrl = "https://placeholder.com/20x20";
            if(alienRick.has("name")) {
                alienRickName = alienRick.getString("name");
            }
            if(alienRick.has("image")) {
                alienRickUrl = alienRick.getString("image");
            }


            Log.i("alien-rick", String.format("%s %s", alienRickName, alienRickUrl));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}
