package id.co.imastudio.santri.karyawaninven.recyler;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import id.co.imastudio.santri.karyawaninven.Detail;
import id.co.imastudio.santri.karyawaninven.ListData;
import id.co.imastudio.santri.karyawaninven.R;
import id.co.imastudio.santri.karyawaninven.adapter.DBDataSource;
import id.co.imastudio.santri.karyawaninven.adapter.DBHelper;
import id.co.imastudio.santri.karyawaninven.adapter.User;

/**
 * Created by Server on 06/08/2017.
 */

public class RecyclerAdapter extends  RecyclerView.Adapter<RecyclerViewHolder> {
    //deklarasi variable context
    private SQLiteDatabase database;
    private final Context context;
    private DBHelper dbHelper;
    ArrayList<User> values;

    LayoutInflater inflater;
    public RecyclerAdapter(Context context, ArrayList<User> values) {
        this.context=context;
        this.values = values;

        //buat dv
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        inflater=LayoutInflater.from(context);
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.item_list, parent, false);
        RecyclerViewHolder viewHolder=new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {

       final String UsId = "" +values.get(position).getId();
        final String UsNama = ""+values.get(position).getNama();
        final String UsEmail = ""+values.get(position).getEmail();
        final String UsDep = ""+values.get(position).getDeplop();
        final String UsPer = ""+values.get(position).getPerusahan();

        holder.tv1.setText(values.get(position).getNama());
        holder.tv2.setText(values.get(position).getEmail());
        holder.tv3.setText(values.get(position).getDeplop());
        holder.tv4.setText(values.get(position).getPerusahan());

        //menampilkan data pada cardview
        holder.tv1.setTag(holder);
        holder.tv2.setTag(holder);
        //event klik ketika salah satu text di cardview di klik
        holder.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenDetailUser(UsId, UsNama, UsEmail, UsDep, UsPer);

            }
        });

        //event imagebutton yang ada pada cardview
       holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem(values.get(position).getId());
            }
        });

    }

    //dialog hapus
    private void deleteItem(final long UsId) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Konfirmasi");
        dialog.setMessage("Hapus User  ?");
        dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBDataSource.delete(UsId, database);
                Intent intent = new Intent(context, ListData.class);
                Toast.makeText(context, "Dihapus !", Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });
        dialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();
        ;
    }

    //array untuk menampung intent untuk mengirim data ke activity detail
    private void OpenDetailUser (String id, String nama, String email, String depp, String per)
    {
        Intent intent = new Intent(context, Detail.class);
        intent.putExtra("userId", id);
        intent.putExtra("usNama", nama);
        intent.putExtra("usEmail", email);
        intent.putExtra("usDe", depp);
        intent.putExtra("usPe", per);
        context.startActivity(intent);
        ListData.activity.finish();
    }

   @Override
   public int getItemCount() {
       return values.size();
   }
}