package id.co.imastudio.santri.karyawaninven.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Server on 06/08/2017.
 */

public class DBDataSource {
    private SQLiteDatabase database;
    //dijalankan apabilia ingin updgrade database
    //inisialisasi kelas dbhelper
    private  DBHelper dbHelper;
    //ambil semua kolom
    private String[] allColumns = { DBHelper.COLUMN_ID, DBHelper.COLUMN_NAME,
            DBHelper.COLUMN_EMAIL, DBHelper.COLUMN_DEPLOP, DBHelper.COLUMN_PERUSAHAN};
    public  DBDataSource(Context context){
        dbHelper = new DBHelper(context);
    }

    //membuka atau membuat sambungan baru ke database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    //menutup sambungan ke database
    public void close(){
        dbHelper.close();;
    }

    //method untuk create/insert barang ke databse
    public User createUser (String nama, String email, String depelop, String perusahan){
        //membuat sebuh contentvalues yang berfungsu
        //untuk memasangkan data dengan nama2
        //kolom pada databse
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME, nama);
        values.put(DBHelper.COLUMN_EMAIL, email);
        values.put(DBHelper.COLUMN_DEPLOP, depelop);
        values.put(DBHelper.COLUMN_PERUSAHAN, perusahan);
        //mengekseskusi perintah sql insert data
        //yang akn mengmbalikan sebuah insertId
        long insertId = database.insert(DBHelper.TABLE_NAME, null,
                values);
        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns, DBHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);

        //pindah kedata paling pertama
        cursor.moveToFirst();
        //mengubaha objek pada curso pertama
        //ke dalam objek barang
        User newUser = cursorToUser (cursor);
        //cloe cursor
        cursor.close();
        //mengembar=likan barang baru
        return newUser;
    }

    private User cursorToUser(Cursor cursor) {
        User user = new User();
        //debug logcat
        Log.v("info", "the getLong"+cursor.getLong(0));
        Log.v("info", "The setLatLng"+cursor.getString(1)+","+cursor.getString(2));
        //set atribut pada objek barang dengan
        //data kursor yang di ambil dt dtabse
        user.setId(cursor.getLong(0));
        user.setNama(cursor.getString(1));
        user.setEmail(cursor.getString(2));
        user.setDeplop(cursor.getString(3));
        user.setPerusahan(cursor.getString(4));
        return user;

    }

    //mengambil semua data barang
    public ArrayList<User> getAllUser() {
        ArrayList<User> daftarUser = new ArrayList<User>();

        // select all SQL query
        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns, null, null, null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();
        // jika masih ada data, masukkan data barang ke
        // daftar barang
        while (!cursor.isAfterLast()) {
            User user = cursorToUser(cursor);
            daftarUser.add(user);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return daftarUser;
    }

    //methoed edit
    public void editUser(User user){
        Log.w("INFO", "updateUser:" + user.getId());
        //ambil data id user
        String strFilter = "_id = " + user.getId();
        //masukan ke content values
        ContentValues args = new ContentValues();
        //masukan data sesuai dengan kolom
        args.put(DBHelper.COLUMN_NAME, user.getNama());
        args.put(DBHelper.COLUMN_EMAIL, user.getEmail());
        args.put(DBHelper.COLUMN_DEPLOP, user.getDeplop());
        args.put(DBHelper.COLUMN_PERUSAHAN, user.getPerusahan());
        //update query
        database.update(DBHelper.TABLE_NAME, args, strFilter, null);
    }

    public User getUser(long id)
    {
        User user = new User(); //inisialisasi barang
        //select query
        Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns, "_id ="+id, null, null, null, null);
        //ambil data yang pertama
        cursor.moveToFirst();
        //masukkan data cursor ke objek barang
        user = cursorToUser(cursor);
        //tutup sambungan
        cursor.close();
        //return barang
        return user;
    }

    public void deteleUser(User u){
        String strFilter =  "_id = " + u.getId();
        ContentValues args = new ContentValues();
        database.delete(DBHelper.TABLE_NAME, strFilter, null);

    }

    public static boolean delete(long id, SQLiteDatabase database) {
        //SQLiteDatabase database = new SQLiteDatabase;
        database.delete(DBHelper.TABLE_NAME, DBHelper.COLUMN_ID + " = " + id, null );
        return true;
    }

}
