package com.delta.layouts;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by Jackie on 19/10/2016.
 */

public class HttpDeleteRequest extends AsyncTask<String, Void, String> {

    private TextView httpBody;
    private String path;
    private String queryString;

    public void setPath(String path) {
        this.path = path;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public HttpDeleteRequest(Activity myContext)
    {
        httpBody = (TextView) myContext.findViewById(R.id.responseBox);
    }

    @Override
    protected String doInBackground(String[] params) {
        String data = null;
        try {
            data = executeRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("Jackie","Output: " + result);
        httpBody.setText(result);
    }

    public String executeRequest() throws Exception {
        BufferedReader br = null;
        String responseData = null;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        try {
            URI destination = new URI("http", null, "ec2-52-64-226-30.ap-southeast-2.compute.amazonaws.com", 9000, path, queryString, null);
            HttpPost deleteRequest = new HttpPost();
            deleteRequest.setURI(destination);
            StringEntity se = new StringEntity("");
            se.setContentType("text/plain");
            deleteRequest.setEntity(se);
            HttpResponse response = httpClient.execute(deleteRequest);

            br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer buffer = new StringBuffer("");
            String l = "";
            String nl = System.getProperty("line.separator");

            while ((l = br.readLine()) != null) {
                buffer.append(l + nl);
            }

            br.close();
            httpClient.getConnectionManager().shutdown();
            responseData = buffer.toString();
            return responseData;
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                    httpClient.getConnectionManager().shutdown();
                    return responseData;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
