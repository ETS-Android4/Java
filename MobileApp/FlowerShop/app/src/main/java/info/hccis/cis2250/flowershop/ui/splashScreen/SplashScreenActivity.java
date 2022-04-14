package info.hccis.cis2250.flowershop.ui.splashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;
import info.hccis.cis2250.flowershop.MainActivity;
import info.hccis.cis2250.flowershop.R;

/**
 * Class to control the splash screen
 * Code sourced from https://www.youtube.com/watch?v=gt1WYT0Qpfk&t=84s
 * @author Kendall Fowler
 * @since 29-MAR-2021
 */

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EasySplashScreen config = new EasySplashScreen(SplashScreenActivity.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(4000)
                .withBackgroundColor(Color.parseColor("#c9d082"))
                .withHeaderText("")
                .withFooterText("")
                .withBeforeLogoText("CIS Flower Shop")
                .withAfterLogoText("CIS 2250 Kendall Fowler")
                .withLogo(R.mipmap.ic_splash_launcher);
        config.getHeaderTextView().setTextColor(Color.WHITE);
        config.getFooterTextView().setTextColor(Color.WHITE);
        config.getBeforeLogoTextView().setTextColor(Color.WHITE);
        config.getAfterLogoTextView().setTextColor(Color.WHITE);
        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }
}