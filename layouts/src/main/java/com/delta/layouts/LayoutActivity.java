package com.delta.layouts;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LayoutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getSpectrum(View view) {
        HttpGetRequest getRequestSpectrum = new HttpGetRequest(this);
        getRequestSpectrum.setPath("/findall/spectrum");
        try {
            getRequestSpectrum.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getLocation(View view) {
        HttpGetRequest getRequestLocation = new HttpGetRequest(this);
        getRequestLocation.setPath("/findall/location");
        try {
            getRequestLocation.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void postSpectrum(View view) {
        HttpPostRequest postRequestSpectrum = new HttpPostRequest(this);
        postRequestSpectrum.setPath("/addrecord/spectrum");
        postRequestSpectrum.setBatchID(getDate());
        try {
            postRequestSpectrum.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void postLocation(View view) {
        HttpPostRequest postRequestLocation = new HttpPostRequest(this);
        postRequestLocation.setPath("/addrecord/location");
        postRequestLocation.setBatchID(getDate());
        try {
            postRequestLocation.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteSpectrum(View view) {
        showNoticeDialog();
    }

    public void deleteLocation(View view) {
        showNoticeDialog();
    }

    private String getDate() {
        Date date = new Date();
        date.getTime();
        return new SimpleDateFormat("yyyyMMdd-HHmmss").format(date);
    }

    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new BatchIdDialogFragment();
        FragmentManager fragmentManager = this.getFragmentManager();
        dialog.show(fragmentManager, "BatchIdDialogFragment");
    }
}
