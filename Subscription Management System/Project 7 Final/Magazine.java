package Project_7;

//Child Class of the Subscription
public class Magazine extends Subscription {
    private String DeliveryType;
    public String getDeliveryType() {
        return DeliveryType;
    }
    public Magazine (String startDate, String endDate, String status, String name, String deliveryType) {
        super(startDate, endDate, status, name);
        this.DeliveryType = deliveryType;
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