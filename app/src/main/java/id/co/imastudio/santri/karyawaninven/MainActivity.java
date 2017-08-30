package id.co.imastudio.santri.karyawaninven;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import id.co.imastudio.santri.karyawaninven.adapter.TambahKaryawan;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //deklarasi button
    TextView txtUsername;
    ImageButton tmb, lht;
    String username;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //deklarasi id button
        tmb = (ImageButton) findViewById(R.id.tambah);
        lht = (ImageButton) findViewById(R.id.lihatData);
        lht.setOnClickListener((View.OnClickListener) this);
        tmb.setOnClickListener((View.OnClickListener) this);

        txtUsername = (TextView) findViewById(R.id.username);
        Intent getUsername = getIntent();
        username = getUsername.getStringExtra("username");


        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        username = user.get(SessionManager.kunci_email);
        txtUsername.setText(username);
        // get user data from session


    }

    //method klik button yang mengarah ke insert/tambah data dan ke activty untuk melihat semudata
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tambah:
                Intent i = new Intent(MainActivity.this, TambahKaryawan.class);
                startActivity(i);
                break;
            case R.id.lihatData:
                Intent i2 = new Intent(MainActivity.this, ListData.class);
                startActivity(i2);
                Toast.makeText(this,"Pindah ke",  Toast.LENGTH_LONG).show();
                break;
        }
    }
    //method agar tidak kemabli ke login
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Apa kalian ingin Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        session.logout();
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

}

