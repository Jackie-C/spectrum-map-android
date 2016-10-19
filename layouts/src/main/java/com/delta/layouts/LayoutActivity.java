package com.delta.layouts;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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

    public void postSpectrum(View view) {
        HttpGetRequest getSpectrum = new HttpGetRequest(this);
        getSpectrum.setPath("/addrecord/spectrum");
        try {
            getSpectrum.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void postLocation(View view) {
        HttpGetRequest getSpectrum = new HttpGetRequest(this);
        getSpectrum.setPath("/addrecord/location");
        try {
            getSpectrum.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getSpectrum(View view) {
        HttpGetRequest getSpectrum = new HttpGetRequest(this);
        getSpectrum.setPath("/findall/spectrum");
        try {
            getSpectrum.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getLocation(View view) {
        HttpGetRequest getSpectrum = new HttpGetRequest(this);
        getSpectrum.setPath("/findall/location");
        try {
            getSpectrum.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
