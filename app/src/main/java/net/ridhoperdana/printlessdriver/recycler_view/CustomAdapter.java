package net.ridhoperdana.printlessdriver.recycler_view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import net.ridhoperdana.printlessdriver.kelas_penampung.Client;
import net.ridhoperdana.printlessdriver.activity.DetailPrintActivity;
import net.ridhoperdana.printlessdriver.R;
import net.ridhoperdana.printlessdriver.activity.MainActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by RIDHO on 11/29/2016.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder>{
    List<Client> list = Collections.emptyList();
    Context context;
//    public Tempat_sementara tempat;
    private int status=0;

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list, parent, false);
        CustomViewHolder holder = new CustomViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {

        final Client results = list.get(position);
        List<Address> daftar = null;
        Geocoder g = new Geocoder(context);
        try {
            daftar = g.getFromLocation(results.getLat(), results.getLongt(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert daftar != null;
        final Address alamat = daftar.get(0);

        holder.textViewNamaClient.setText(results.getNama().toLowerCase());
        holder.textViewNamaFile.setText(alamat.getAddressLine(0).toLowerCase());
        if(results.getStatus_pesanan()==1)
        {
            holder.buttonTerima.setVisibility(View.GONE);
        }
        holder.buttonDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pindah ke detail client
                Intent intent = new Intent(context, DetailPrintActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("nama", results.getNama());
                bundle.putString("alamat", alamat.getAddressLine(0));
                bundle.putString("nama_file", results.getNamaFile());
//                bundle.putString("harga", String.valueOf(results.getHarga()));
                bundle.putString("lat", results.getLat()+"");
                bundle.putString("longt", results.getLongt()+"");
                bundle.putString("keterangan", results.getKeteranganFile());
                intent.putExtra("bundle", bundle);
                context.startActivity(intent);
            }
        });
        holder.buttonTerima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(context);
                View customInputDialog = inflater.inflate(R.layout.custom_alert_dialog, null);
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

                alertDialog.setView(customInputDialog);
                final EditText inputHarga = (EditText)customInputDialog.findViewById(R.id.input_harga);
                Button tombolKirim = (Button)customInputDialog.findViewById(R.id.tombol_kirim);
                tombolKirim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //kirim biaya ke server
                        int Biaya = Integer.parseInt(inputHarga.getText().toString());
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                dialog.dismiss();
                            }
                        });
                    }
                });

                alertDialog.setTitle("Print Selesai");
                alertDialog.setCancelable(true);

//                alertDialog.show();
                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public CustomAdapter(ArrayList<Client> list, Context context) {
        this.list = list;
        this.context = context;
    }
}
