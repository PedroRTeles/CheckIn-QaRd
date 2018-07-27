package br.com.stampstudio.checkinqard.Model;

public class Day {
    private String Date;
    private String Time[];
    private int Type[];

    public Day() {
        this.Date = "";
        this.Time = new String[4];
        this.Type = new int[4];
    }


    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String[] getTime() {
        return Time;
    }

    public void setTime(String[] time) {
        Time = time;
    }

    public int[] getType() {
        return Type;
    }

    public void setType(int[] type) {
        Type = type;
    }
}
