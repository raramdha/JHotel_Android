package jhotel.jhotel_android_ramdhaidfitri;

/**
 * Created by Ramdha on 03/05/2018.
 */

public class Hotel {
    private int id;
    private String nama;
    private Lokasi lokasi;
    private int bintang;

    public Hotel(int id, String nama, Lokasi lokasi, int bintang)
    {
        this.nama = nama;
        this.lokasi = lokasi;
        this.bintang = bintang;
        this.id = id;
    }


    public int getID(){
        return id;
    }

    public int getBintang()
    {
        return bintang;
    }

    public String getNama()
    {
        return nama;
    }

    public Lokasi getLokasi()
    {
        return lokasi;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setBintang(int bintang)
    {
        this.bintang = bintang;
    }

    public void setNama(String nama)
    {
        this.nama = nama;
    }

    public void setLokasi(Lokasi lokasi)
    {
        this.lokasi = lokasi;
    }

}
