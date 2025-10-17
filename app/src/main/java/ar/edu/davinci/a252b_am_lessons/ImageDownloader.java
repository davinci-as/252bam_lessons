package ar.edu.davinci.a252b_am_lessons;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

public class ImageDownloader extends AsyncTask<String, Integer, Bitmap> {
    @Override
    protected Bitmap doInBackground(String... strings) {
        Log.i("async-task", "working doInBackground");
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        Log.i("async-task", "working onPostExecute");
    }
}
