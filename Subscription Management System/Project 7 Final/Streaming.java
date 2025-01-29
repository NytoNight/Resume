package Project_7;

public class Streaming extends Subscription {
    private int devicelimit;

    public int getDevicelimit() {return devicelimit; }

    public Streaming(String startDate, String endDate, String status, String name, int devicelimit) {
        super(startDate, endDate, status, name);
        this.devicelimit = devicelimit;
    }

    public String getName() {
        return super.getName();
    }

    public String getStartDate() {
        return super.getStartDate();
    }


    public String getEndDate() {
        return super.getEndDate();
    }


    public String getStatus() {
        return super.getStatus();
    }


}