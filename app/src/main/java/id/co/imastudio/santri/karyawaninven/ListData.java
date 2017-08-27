package id.co.imastudio.santri.karyawaninven;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import id.co.imastudio.santri.karyawaninven.adapter.DBDataSource;
import id.co.imastudio.santri.karyawaninven.adapter.DBHelper;
import id.co.imastudio.santri.karyawaninven.adapter.User;
import id.co.imastudio.santri.karyawaninven.recyler.RecyclerAdapter;

public class ListData extends AppCompatActivity  {
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    private DBHelper dbHelper;
    public static Activity activity;


    //inisialisasi kontroller
    private DBDataSource dataSource;

    //inisialisasi arraylist
    private ArrayList<User> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        activity = this;
        dataSource = new DBDataSource(this);
        dataSource.open();
        try {
            dataSource.open();
        } catch (android.database.SQLException e) {
            e.printStackTrace();
        }

        values = (ArrayList<User>) dataSource.getAllUser();
        ArrayAdapter<User> arrayAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, values);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //  initObjects();

        //menampilkan reyclerview yang ada pada file layout dengan id reycler view

        RecyclerAdapter adapter = new RecyclerAdapter(this, values);
        //membuat adapter baru untuk reyclerview
        recyclerView.setAdapter(adapter);
        //menset nilai dari adapter
        recyclerView.setHasFixedSize(true);
        //menset setukuran
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //menset layoutmanager dan menampilkan daftar/list
        //dalam bentuk linearlayoutmanager pada class saat ini

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ListData.this, MainActivity.class));
        finish();
    }



}