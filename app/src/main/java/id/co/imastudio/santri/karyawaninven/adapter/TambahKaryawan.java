package id.co.imastudio.santri.karyawaninven.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.co.imastudio.santri.karyawaninven.ListData;
import id.co.imastudio.santri.karyawaninven.MainActivity;
import id.co.imastudio.santri.karyawaninven.R;

public class TambahKaryawan extends AppCompatActivity implements View.OnClickListener {
    //deklarasi variabel
    private Button submitt;
    private EditText edtnama, edtemail, edtdep, edtperusahan;
    private DBDataSource dataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahkaryawan);

        edtnama= (EditText)findViewById(R.id.namak);
        edtemail= (EditText)findViewById(R.id.email);
        edtdep=(EditText) findViewById(R.id.deplop);
        edtperusahan=(EditText)findViewById(R.id.perusahan);

        dataSource = new DBDataSource(this);
        dataSource.open();

        submitt = (Button) findViewById(R.id.submit);
        submitt.setOnClickListener(this);


    }
    //method pada button ketika di klik akan menyimpan data
    @Override
    public void onClick(View view) {
        String nama = null;
        String email = null;
        String depelop = null;
        String perusahan =  null;
        @SuppressWarnings("unused")
                User user = null;
        if (edtnama.getText()!= null && edtemail.getText()!= null && edtdep.getText()!= null && edtperusahan.getText()!= null)
        {
            nama = edtnama.getText().toString();
            email = edtemail.getText().toString();
            depelop = edtdep.getText().toString();
            perusahan = edtperusahan.getText().toString();

        }
        switch (view.getId()){
            case R.id.submit:
                user = dataSource.createUser(nama,email,depelop,perusahan);
                startActivity(new Intent(TambahKaryawan.this, ListData.class));
                Toast.makeText(this, " Berhasil Masukan "+" nama :" + user.getNama()+
                        " EMail :" + user.getEmail()+
                        "Dep :" + user.getDeplop(), Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(TambahKaryawan.this, MainActivity.class));
        finish();
    }
}


