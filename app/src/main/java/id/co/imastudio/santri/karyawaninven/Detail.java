package id.co.imastudio.santri.karyawaninven;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import id.co.imastudio.santri.karyawaninven.adapter.DBDataSource;
import id.co.imastudio.santri.karyawaninven.adapter.User;

public class Detail extends AppCompatActivity {

    private TextView nama,email,dep,per,idd;
    private Button btnEdit,btnHps;
    private DBDataSource dataSource;
    User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dataSource = new DBDataSource(this);
        dataSource.open();
        try{
            dataSource.open();
        }catch (SQLException e){
            e.printStackTrace();
        }

        initViews();
        //menangkap data yang telah di intent dari cardview
       nama.setText(getIntent().getStringExtra("usNama"));
         email.setText(getIntent().getStringExtra("usEmail"));
        dep.setText(getIntent().getStringExtra("usDe"));
        per.setText(getIntent().getStringExtra("usPe"));




            Log.w("INFOO", getIntent().getStringExtra("userId"));
        btnHps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = new User();
                user.setId(Long.parseLong(""+getIntent().getStringExtra("userId")));
                AlertDialog.Builder dialog = new AlertDialog.Builder(Detail.this);
                dialog.setTitle("Konfirmasi");
                dialog.setMessage("Anda akan menghapus data ini ?");
                dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dataSource.deteleUser(user);
                        Toast.makeText(Detail.this, "Berhasil dihapus !", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Detail.this, ListData.class));
                    }
                });
                dialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();

            }
        });
        //memunculkan dialog edit data
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(Detail.this);
                dialog.setContentView(R.layout.ubah_data);
                final EditText edtNama = (EditText) dialog.findViewById(R.id.editNamaaa);
                final EditText edtEmail = (EditText) dialog.findViewById(R.id.editEmaill);
                final EditText edtDeplop = (EditText) dialog.findViewById(R.id.editDeppp);
                final EditText perus = (EditText) dialog.findViewById(R.id.editPerus);
                final Button btnSimpan = (Button) dialog.findViewById(R.id.btnEditLagi);


                edtNama.setText(nama.getText());
                edtEmail.setText(email.getText());
                edtDeplop.setText(dep.getText());
                perus.setText(per.getText());
                dialog.setTitle("Ubah User");
                dialog.show();

                //menyimpadn data yang telah di edit
                btnSimpan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        nama.setText(edtNama.getText());
                        email.setText(edtEmail.getText());
                        dep.setText(edtDeplop.getText());
                        per.setText(perus.getText());
                        user = new User();
                        user.setId(Long.parseLong(""+getIntent().getStringExtra("userId")));
                        user.setNama(""+edtNama.getText());
                        user.setEmail(""+edtEmail.getText());
                        user.setDeplop(""+edtDeplop.getText());
                        user.setPerusahan(""+perus.getText());
                        dataSource.editUser(user);
                        Detail.this.finish();
                        dialog.dismiss();
                        startActivity(new Intent(Detail.this, ListData.class));

                    }
                });

            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Detail.this, ListData.class));
        finish();
    }
    //deklarasi id pada file xml
    private void initViews() {

        nama = (TextView) findViewById(R.id.Nama);
        email = (TextView) findViewById(R.id.emailll);
        dep= (TextView) findViewById(R.id.dep);
        per= (TextView)findViewById(R.id.perusahannn);
        btnEdit =(Button)findViewById(R.id.btnEditData);
        btnHps = (Button)findViewById(R.id.btnHapusData);

    }
}
