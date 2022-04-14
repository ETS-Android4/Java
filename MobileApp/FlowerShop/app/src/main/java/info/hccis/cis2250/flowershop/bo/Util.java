package info.hccis.cis2250.flowershop.bo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Utility Class to assist in app management
 * @since 20180116
 * @author BJM/CIS2250
 * @modified KF
 * @since 27-FEB-2021
 *
 */
public class Util {

    public static final String BASE_SERVER = "http://10.0.2.2:8081";   //Local host
    public static final String CUSTOMER_BASE_API = Util.BASE_SERVER+"/api/CustomerService/";

    /**
     * This method can be used to show a dialog in your activity.  This will allow the user to
     * enter a positive or negative response.
     * @since 20180116
     * @author BJM/CIS2250
     * @param activity
     * @param title
     * @param message
     *
     */

    //not currently used
    public static void showDialog(final Activity activity, String title, CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        if (title != null) builder.setTitle(title);

        builder.setMessage(message);
        builder.setPositiveButton("Sure", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(activity, "selected-positive", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("No Thanks", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(activity, "selected-negative", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

    /**
     * This function allows the string to be encoded which can be used when
     * starting an intent accessing the web.
     *
     * @author BJM
     * @since 20200131
     */
    public static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("URLEncoder.encode() failed for " + s);
        }
    }

}
