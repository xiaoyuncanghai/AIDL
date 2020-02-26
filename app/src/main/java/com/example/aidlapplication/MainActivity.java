package com.example.aidlapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.aidlapplication.IMyAidlInterface.Stub;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private IMyAidlInterface iMyAidlInterface;
    private Button tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (Button) findViewById(R.id.tv);
        Intent intent = new Intent();
        intent.setAction("com.example.aidlapplication.IMyAidlInterface");
        intent.setPackage("com.example.remoteservice");
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                iMyAidlInterface = Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    Log.d("yuchao_Act", iMyAidlInterface.getName());
                    Toast.makeText(MainActivity.this, iMyAidlInterface.getName(), Toast.LENGTH_SHORT).show();
                }
                catch (RemoteException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

}
