package com.example.remoteservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.os.IResultReceiver;

import androidx.annotation.Nullable;

import com.example.aidlapplication.IMyAidlInterface.Stub;

public class RemoteService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    private String getUserName(){
        return "Liming";
    }

    class MyBinder extends Stub {

        @Override
        public String getName() throws RemoteException {
            return getUserName();
        }
    }
}
