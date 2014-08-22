package com.GetInfo;

import java.net.InetAddress;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GetInfoActivity extends Activity {

    private Button b;
//found script on: http://stackoverflow.com/questions/11777676/how-can-i-get-my-dynamic-ip-address-of-my-android-device

  @Override
  public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        b=(Button)findViewById(R.id.button1);


        b.setOnClickListener(new View.OnClickListener() {


        void onClick(View v) {

        Builder builder = new AlertDialog.Builder(DialogsActivity.this);
        AlertDialog dialog = builder.create();
        dialog.setTitle("Your device info:");

         getLocalIpAddressString();{
            try {
                for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                    NetworkInterface intf = en.nextElement();
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            return inetAddress.getHostAddress().toString();
                        }
                    }
                }
            }catch (SocketException ex) {
            }

            return null;
        }

// http://stackoverflow.com/questions/3394765/how-to-check-available-space-on-android-device-on-mini-sd-card

        getAvailableSpaceInMB();{
            final long SIZE_KB = 1024L;
            final long SIZE_MB = SIZE_KB * SIZE_KB;
            long availableSpace = -1L;
            StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
            availableSpace = (long) stat.getAvailableBlocks() * (long) stat.getBlockSize();
            return availableSpace/SIZE_MB;
        }
                // dialog.setMessage("You cannout use this app unless you accept.");

    dialog.show();
}


        });
}
}


