package id.co.imastudio.santri.karyawaninven;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sessionManager = new SessionManager(getApplicationContext());

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sessionManager.checkLogin();
               // startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        },2000);
    }
    }

