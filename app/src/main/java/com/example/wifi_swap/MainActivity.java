package com.example.wifi_swap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private static final String CUSTOM_BROADCAST = "com.example.wifi_swap.broadcastreceiver.CUSTOM_BROADCAST";
    private MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Oppdaterer Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Registrer BroadcastReceiver
        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter(CUSTOM_BROADCAST);
        registerReceiver(receiver, filter);

        // Send Broadcast
        Intent i = new Intent(CUSTOM_BROADCAST);
        sendBroadcast(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Viktig: Avregistrer receiver for å unngå minnelekkasje
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Oppdatert BroadcastReceiver
    public static class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("BroadcastReceiver", "Mottok tilpasset kringkasting!");
        }
    }
}
