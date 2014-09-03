package your.company;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.view.View;
import android.widget.Button;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.app.AlertDialog;
import android.widget.Toast;
import android.app.Dialog;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;


public class GetInfoActivity extends Activity {

    private Button b;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = (Button) findViewById(R.id.button1);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long space = getAvailableSpaceInMB();
                String theSpace = "The space available is: " + space;
                String theIP = getLocalIpAddressString();
                String theVersion = getVersionInfo();

                AlertDialog.Builder builder = new
                AlertDialog.Builder(GetInfoActivity.this);
                AlertDialog dialog = builder.create();
                dialog.setTitle("The Title");
                dialog.setMessage(theSpace +"available space \n" +"The IP" + theIP + " Version: " + theVersion);
                dialog.show();
            }
        });
    }

//found on: http://stackoverflow.com/questions/3394765/how-to-check-available-space-on-android-device-on-mini-sd-card
    public static long getAvailableSpaceInMB() {
        final long SIZE_KB = 1024L;
        final long SIZE_MB = SIZE_KB * SIZE_KB;
        long availableSpace = -1L;
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        availableSpace = (long) stat.getAvailableBlocks() * (long) stat.getBlockSize();
        return availableSpace/SIZE_MB;
    }


//found on: http://stackoverflow.com/questions/11777676/how-can-i-get-my-dynamic-ip-address-of-my-android-device

    public static String getLocalIpAddressString() {
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

// found on: http://stackoverflow.com/questions/13766803/display-version-name
    public String getVersionInfo() {
        String strVersion = "Version:";
        PackageInfo packageInfo;
        try {
            packageInfo = getApplicationContext().getPackageManager().getPackageInfo(
                    getApplicationContext().getPackageName(), 0);
            strVersion += packageInfo.versionName;
        } catch (NameNotFoundException e) {
            strVersion += "Unknown";
        }

        return strVersion;
    }

}


