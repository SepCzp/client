package com.code.miduo.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.code.miduo.service.Book;
import com.code.miduo.service.IMyAidlInterface;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CLIENT";

    private IMyAidlInterface asInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bind(View view) {
        //通过Intent指定服务端的服务名称和所在包，与远程Service进行绑定
        //参数与服务器端的action要一致,即"服务器包名.aidl接口文件名"
        Intent intent = new Intent("com.code.miduo.service.IMyAidlInterface");
        //Android5.0后无法只通过隐式Intent绑定远程Service
        //需要通过setPackage()方法指定包名
        intent.setPackage("com.code.miduo.service");
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            asInterface = IMyAidlInterface.Stub.asInterface(service);
            try {
                asInterface.connect();
                Book book = new Book(1, "牛逼树");
                asInterface.addBook(book);
                Log.d(TAG, "onServiceConnected: " + asInterface.getBooks().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


}
