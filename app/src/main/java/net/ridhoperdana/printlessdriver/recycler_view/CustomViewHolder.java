package net.ridhoperdana.printlessdriver.recycler_view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.ridhoperdana.printlessdriver.R;

/**
 * Created by RIDHO on 11/29/2016.
 */

public class CustomViewHolder extends RecyclerView.ViewHolder {

    TextView textViewNamaClient, textViewNamaFile;
    Button buttonDetail, buttonTerima, buttonTolak;

    public CustomViewHolder(View itemView) {
        super(itemView);
        textViewNamaClient = (TextView)itemView.findViewById(R.id.nama_client);
        textViewNamaFile = (TextView)itemView.findViewById(R.id.nama_file_client);
        buttonDetail = (Button)itemView.findViewById(R.id.tombol_detil);
        buttonTerima = (Button)itemView.findViewById(R.id.tombol_selesai);
    }
}
