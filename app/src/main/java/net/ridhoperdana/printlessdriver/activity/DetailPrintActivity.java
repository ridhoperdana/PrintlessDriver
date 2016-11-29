package net.ridhoperdana.printlessdriver.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.ridhoperdana.printlessdriver.interface_retrofit.GetFile;
import net.ridhoperdana.printlessdriver.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailPrintActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_print);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        String nama_client = bundle.getString("nama");
        String alamat_client = bundle.getString("alamat");
        final String file_client = bundle.getString("nama_file");
        String keterangan_client = bundle.getString("keterangan");
        final Double lat = Double.parseDouble(bundle.getString("lat"));
        final Double longt = Double.parseDouble(bundle.getString("longt"));

        TextView textViewNama = (TextView)findViewById(R.id.nama_client_detail);
        TextView textViewAlamat = (TextView)findViewById(R.id.alamat_client_detail);
        TextView textViewNamaFile = (TextView)findViewById(R.id.nama_file_client_detail);
        TextView textViewKeterangan = (TextView)findViewById(R.id.detail_file_client_detail);
        Button tombolLihatPeta = (Button)findViewById(R.id.tombol_lihat_peta);
        Button tombolAmbilOrder = (Button)findViewById(R.id.tombol_ambil_kerjaan);
        Button tombolDownload = (Button)findViewById(R.id.tombol_download);

        textViewNama.setText(nama_client);
        textViewAlamat.setText(alamat_client);
        textViewNamaFile.setText(file_client);
        textViewKeterangan.setText(keterangan_client);

        tombolLihatPeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pindah ke google maps
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr="+lat+""+","+longt+""));
                startActivity(intent);
            }
        });

        tombolAmbilOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //masuk ke database, balik ke activity sebelumnya
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tombolDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://ridhoperdana.net/rk/themes/images/carousel/1.png";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }

    public void downloadFile(final String namafile)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ridhoperdana.net")
                .build();

        GetFile service = retrofit.create(GetFile.class);

        String url = "http://ridhoperdana.net/rk/themes/images/carousel/1.png";

        Call<ResponseBody> call = service.downloadFileWithDynamicUrlSync(url);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("ada file", "");

                    boolean writtenToDisk = writeResponseBodyToDisk(response.body(), namafile);

                    Log.d("download sukses?", "" + writtenToDisk);
                } else {
                    Log.d("gagal kontak server", "");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("error", "ke server");
            }
        });
    }

    private boolean writeResponseBodyToDisk(ResponseBody body, String namaFile) {
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(getExternalFilesDir(null) + File.separator + namaFile);

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d("download: ", "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

}
