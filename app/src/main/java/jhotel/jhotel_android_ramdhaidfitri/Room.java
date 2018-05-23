package jhotel.jhotel_android_ramdhaidfitri;

/**
 * Created by Ramdha on 03/05/2018.
 */
public class Room {
    private String roomNumber;
    private String statusKamar;
    private double dailyTariff;
    private String tipeKamar;

    public Room(String roomNumber, String statusKamar, double dailyTariff, String tipeKamar) {
        this.roomNumber = roomNumber;
        this.statusKamar = statusKamar;
        this.dailyTariff = dailyTariff;
        this.tipeKamar = tipeKamar;
    }

    public String getRoomNumber()
    {
        return roomNumber;
    }

    public double getDailyTariff(){
        return dailyTariff;
    }

    public String getStatusKamar(){
        return statusKamar;
    }

    public String getTipeKamar(){ return tipeKamar;}

    public void setRoomnumber(String roomNumber)
    {
        this.roomNumber = roomNumber;
    }
    public void setTipeKamar(String tipeKamar)
    {
        this.tipeKamar = tipeKamar;
    }
    public void setDailyTariff(double dailytariff){
        dailyTariff = dailytariff;
    }
    public void setStatusKamar(String statusKamar){ this.statusKamar = statusKamar; }

}
