package karimabdelmalek.plickersproject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;

import karimabdelmalek.plickersproject.Helpers.NetworkThread;
import karimabdelmalek.plickersproject.Helpers.Utilities;
import karimabdelmalek.plickersproject.Helpers.WebDataCallBack;

/**
 * Created by karimtalaat on 9/12/15.
 */
public class ActivitySplash extends Activity {
    protected int _splashTime = 3000;
    Thread splashTread;
    protected boolean _active = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (_active && (waited < _splashTime)) {
                        sleep(100);
                        if (_active) {
                            waited += 100;
                        }
                    }
                } catch (InterruptedException e) {
                } finally {

                    Utilities.goToPage(ActivitySplash.this,MainActivity.class);
                }

                finish();
            }

        };

        splashTread.start();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            _active = false;
        }
        return true;
    }
}






