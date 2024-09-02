package TripHistory;

public class TripRecord {
    String DE_ID;
    char restaurant;
    char destination;
    int orderCount;
    double pickupTime;
    double deliveryTime;
    double deliveryCharges;
    public TripRecord(String DE_ID, char restaurant, char destination, int orderCount, double pickupTime, double deliveryTime, double deliveryCharges){
        this.DE_ID=DE_ID;
        this.restaurant=restaurant;
        this.destination=destination;
        this.orderCount=orderCount;
        this.pickupTime=pickupTime;
        this.deliveryTime =deliveryTime;
        this.deliveryCharges=deliveryCharges;

    }
    @Override
    public String toString(){
        return  "          "+this.DE_ID+"         "+this.restaurant+"                 "+this.destination+"         "+this.orderCount+"          "+this.pickupTime+"            "+this.deliveryTime+"           "+this.deliveryCharges;
    }
}
