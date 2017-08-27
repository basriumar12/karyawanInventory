package id.co.imastudio.santri.karyawaninven;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import id.co.imastudio.santri.karyawaninven.adapter.TambahKaryawan;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //deklarasi button
    ImageButton tmb, lht;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //deklarasi id button
        tmb = (ImageButton) findViewById(R.id.tambah);
        lht = (ImageButton) findViewById(R.id.lihatData);
        lht.setOnClickListener((View.OnClickListener) this);
        tmb.setOnClickListener((View.OnClickListener) this);

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
        startActivity(new Intent(MainActivity.this, MainActivity.class));
        finish();
    }
}
