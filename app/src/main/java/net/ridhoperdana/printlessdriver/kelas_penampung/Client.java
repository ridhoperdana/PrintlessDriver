package net.ridhoperdana.printlessdriver.kelas_penampung;

/**
 * Created by RIDHO on 11/29/2016.
 */

public class Client {
    private String nama;
    private String alamat;
    private String namaFile;
    private int status_pesanan;

    private String keteranganFile;
    private Double lat, longt;
    private int harga;

    public Client(String nama, String alamat, String namaFile, Double lat, Double longt, String keteranganFile, int status_pesanan) {
        this.nama = nama;
        this.alamat = alamat;
        this.namaFile = namaFile;
        this.lat = lat;
        this.longt = longt;
        this.keteranganFile = keteranganFile;
        this.status_pesanan = status_pesanan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNamaFile() {
        return namaFile;
    }

    public void setNamaFile(String namaFile) {
        this.namaFile = namaFile;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLongt() {
        return longt;
    }

    public void setLongt(Double longt) {
        this.longt = longt;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getKeteranganFile() {
        return keteranganFile;
    }

    public void setKeteranganFile(String keteranganFile) {
        this.keteranganFile = keteranganFile;
    }

    public int getStatus_pesanan() {
        return status_pesanan;
    }

    public void setStatus_pesanan(int status_pesanan) {
        this.status_pesanan = status_pesanan;
    }
}
