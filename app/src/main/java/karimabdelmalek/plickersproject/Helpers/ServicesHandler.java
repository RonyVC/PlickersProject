package karimabdelmalek.plickersproject.Helpers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import karimabdelmalek.plickersproject.DataClasses.PollResponse;

/**
 * Created by karimtalaat on 9/12/15.
 */
public class ServicesHandler {
    public static String baseUrl = "http://plickers-interview.herokuapp.com/polls";


    public static ArrayList<PollResponse> GetPollResponse() {
        try {

            InputStream source = retrieveStreamGET(baseUrl);

            Gson gson = new Gson();
            Reader reader = new InputStreamReader(source);

            return gson.fromJson(reader,
                    new TypeToken<ArrayList<PollResponse>>() {
                    }.getType());

        } catch (Exception e) {
            return null;
        }
    }

    private static InputStream retrieveStreamGET(String url) {

        DefaultHttpClient client = new DefaultHttpClient();

        HttpGet getRequest = new HttpGet(url);

        try {

            HttpResponse getResponse = client.execute(getRequest);
            final int statusCode = getResponse.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }

            HttpEntity getResponseEntity = getResponse.getEntity();
            return getResponseEntity.getContent();

        } catch (IOException e) {
            getRequest.abort();
        }

        return null;

    }
}
