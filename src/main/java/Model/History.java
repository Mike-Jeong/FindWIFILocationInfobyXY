package Model;

public class History {

    private String LAT;
    private String LNT;
    private String Date;

    public History(String LAT, String LNT, String Date) {
        this.LAT = LAT;
        this.LNT = LNT;
        this.Date = Date;
    }

    public String getLAT() {
        return LAT;
    }

    public String getLNT() {
        return LNT;
    }

    public String getDate() {
        return Date;
    }
}
