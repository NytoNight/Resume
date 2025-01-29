package Project_7;
public abstract class Subscription {
    private String startDate;
    private String endDate;
    private String status;
    private String name;

    public Subscription(String startDate, String endDate, String status, String name) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.name = name;
    }

    public String getName() { return name;}
    public String getStartDate() {
        return startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public String getStatus() {
        return status;
    }

}