package DeliveryExecutive;

public class Executive {
    public char currentPoint;
    public double totalEarning;

    public String DE_ID;
    public int orderCount;
    public boolean isAssigned;
    public Executive(String DE_ID){
        this.DE_ID=DE_ID;
        this.currentPoint = ' ';
        this.totalEarning=0.0;
        this.orderCount=0;
        this.isAssigned=false;
    }

}
