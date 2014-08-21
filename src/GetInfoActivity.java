package com.GetInfo;

import java.net.InetAddress;
import android.app.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class MyAndroidAppActivity extends Activity {


//found script on: http://stackoverflow.com/questions/11777676/how-can-i-get-my-dynamic-ip-address-of-my-android-device

  Button button;

  @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button b=(Button)findViewById(R.id.button1);
        TextField t=(TextField)findViewById(R.id.ip_address);

        b.setOnClickListener(new View.OnClickListener() {

                 public static String getLocalIpAddressString() {
            try {
                for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                    NetworkInterface intf = en.nextElement();
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            return inetAddress.getHostAddress().toString();
                        }

                        t.setText(inetAddress.getHostAddress().toString())
                    }
                }
            }catch (SocketException ex) {
            }

            return null;
        }
        });
    }

  }
