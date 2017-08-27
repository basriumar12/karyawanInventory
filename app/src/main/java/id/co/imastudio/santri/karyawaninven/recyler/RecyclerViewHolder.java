package id.co.imastudio.santri.karyawaninven.recyler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import id.co.imastudio.santri.karyawaninven.R;

/**
 * Created by Server on 06/08/2017.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        // ViewHolder akan mendeskripisikan item view .
        TextView tv1,tv2,tv3,tv4; //deklarasi textview
        ImageView imageView;  //deklarasi imageview
        RelativeLayout list_item;
        ImageButton del;

public RecyclerViewHolder(View itemView) {
        super(itemView);

        tv1= (TextView) itemView.findViewById(R.id.nama);
        tv2= (TextView) itemView.findViewById(R.id.email);
        tv3=(TextView)itemView.findViewById(R.id.depee);
        tv4=(TextView)itemView.findViewById(R.id.peree);
        list_item = (RelativeLayout) itemView.findViewById(R.id.list_item);
        //imageView = (ImageView) itemView.findViewById(R.id.daftar_icon);
       del = (ImageButton)itemView.findViewById(R.id.imgBtnDe);

        }
        }