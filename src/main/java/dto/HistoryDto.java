package dto;

public class HistoryDto {

    private final String LAT;
    private final String LNT;
    private final String Date;

    public HistoryDto(String LAT, String LNT, String Date) {
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
