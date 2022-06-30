package dto;

public class HistoryDto {
    private final int id;
    private final String LAT;
    private final String LNT;
    private final String Date;

    public HistoryDto(int id, String lat, String lnt, String date) {
        this.id = id;
        this.LAT = lat;
        this.LNT = lnt;
        this.Date = date;
    }

    public int getId() {
        return id;
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
