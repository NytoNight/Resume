package Project_7;

public class Freemium extends Subscription {
    private int TrialPeriod;
    public int getTrialPeriod() {
        return TrialPeriod;
    }
    public Freemium(String startDate, String endDate, String status, String name, int TrialPeriod) {
        super(startDate, endDate, status, name);
        this.TrialPeriod = TrialPeriod;
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