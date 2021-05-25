package sam.frampton.bakingapp;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class NetworkUtils {

    private static final OkHttpClient sOkHttpClient = new OkHttpClient();
    private static final Gson sGson = new Gson();

    private static final String TAG = NetworkUtils.class.getSimpleName();

    public static List<Recipe> loadRecipes(Context context) {
        String recipesUrl = context.getString(R.string.recipes_url);
        Request request = new Request.Builder().url(recipesUrl).build();
        Response response = null;
        try {
            response = sOkHttpClient.newCall(request).execute();
            ResponseBody body = response.body();
            if (body != null) {
                Type listType = new TypeToken<List<Recipe>>(){}.getType();
                return sGson.fromJson(body.charStream(), listType);
            }
        } catch (IOException | JsonParseException e) {
            Log.e(TAG, Log.getStackTraceString(e));
        } finally {
            if (response!=null) response.close();
        }
        return Collections.emptyList();
    }

}
