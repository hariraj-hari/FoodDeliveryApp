import DeliveryExecutive.Executive;
import TripHistory.TripRecord;

import java.util.*;

public class Booker {
    static double currentTime;
    Map<String, Executive> executors;
    Map<String, Double> waitingList;
    List<TripRecord> waitingOrderRecords;
    List<TripRecord> tripRecords;

    public Booker(){
        this.executors= new LinkedHashMap<>();
        this.waitingList=new LinkedHashMap<>();
        this.tripRecords = new LinkedList<>();
        this.waitingOrderRecords = new LinkedList<>();
        currentTime=9.00;
    }

    public void initializeExecutor(int count){
        int id = 1;
        for(int i=0; i<count; i++){
            executors.put("DE"+id, new Executive("DE"+id++));
        }
    }
    public Executive getExecutive(){
        Executive deliveryExecutive = null;
        double min = Double.MAX_VALUE;
//        if(time<=0.15){
//            for(Map.Entry<String, Double> executor:waitingList.entrySet()){
//                Executive executive = executors.get(executor.getKey());
//                if(executive!=null && executive.isAssigned && min>executor.getValue()){
//                    min = executor.getValue();
//                    deliveryExecutive = executive;
//                }
//            }
//            if(deliveryExecutive!=null){
//                return deliveryExecutive;
//            }else{
//                System.out.println("Waiting List is Empty");
//                return null;
//            }
//        }


        for(Map.Entry<String, Executive> executiveEntry:executors.entrySet()){
            Executive executive = executiveEntry.getValue();
            double totalEarning = executive.totalEarning;
            if(totalEarning==0.0 && !executive.isAssigned){
                deliveryExecutive = executiveEntry.getValue();
                return deliveryExecutive;
            }
            if(totalEarning<min){
                deliveryExecutive = executiveEntry.getValue();
                min = totalEarning;
            }
        }
        return deliveryExecutive;
    }
    public void book(char restaurant, char destination, double time){
        System.out.println("Size of List "+waitingOrderRecords.size());
        System.out.println("Size of List "+tripRecords.size());
        double absoluteTime = Math.abs(currentTime-time);
        if(absoluteTime<=0.15 && time!=currentTime){
            if(!waitingList.isEmpty()){
                double min = Double.MAX_VALUE;
                String id=null;
                for(Map.Entry<String, Double> executor:waitingList.entrySet()){
                    if(min>executor.getValue()){
                        id = executor.getKey();
                        min = executor.getValue();
                    }
                }
                if(id!=null){
                    double initialAmount = waitingList.get(id);
                    waitingList.replace(id, initialAmount+5);
                    System.out.println("Assigned to "+id+" Waiting upto "+Math.ceil(absoluteTime)+" minutes...");
                    return;
                }else{
                    System.out.println("Id not Found Error");
                }
            }else{
                System.out.println("WaitingList is Empy");
            }
        }
        Executive availableExecutive = getExecutive();
        if(availableExecutive==null){
            System.out.println("Executives are not available");
            return;
        }
        if(waitingList.isEmpty()){
            availableExecutive.isAssigned=true;
            availableExecutive.orderCount++;
            waitingList.put(availableExecutive.DE_ID, 50.00);
            currentTime=time;
            System.out.println("Alloted Delivery Executive: "+availableExecutive.DE_ID);
            waitingOrderRecords.add(new TripRecord(availableExecutive.DE_ID, restaurant, destination, 1, time, (time+0.30), 50.00));
            return;
        }


        if(absoluteTime >= 0.15 && time!=currentTime){
            System.out.println("-------->Updating Process<----------------");
            for(Map.Entry<String, Double> entry:waitingList.entrySet()){

                Executive executive =  executors.get(entry.getKey());
                executive.orderCount++;
                executive.totalEarning=entry.getValue();
                executive.isAssigned=false;

                System.out.println("------->Updated Delivery Executive: "+executive.DE_ID);

            }
            tripRecords.addAll(waitingOrderRecords);
            waitingList.clear();
            waitingOrderRecords.clear();
            if(waitingList.isEmpty()){
                availableExecutive.isAssigned=true;
                waitingList.put(availableExecutive.DE_ID, 50.00);
                waitingOrderRecords.add(new TripRecord(availableExecutive.DE_ID, restaurant, destination, ++availableExecutive.orderCount, time, (time+0.30), 50.00));
                currentTime=time;
                System.out.println("\n---->Alloted Delivery Executive: "+availableExecutive.DE_ID);
                return;
            }
        }else{
                System.out.println("Alloted Delivery Executive: "+availableExecutive.DE_ID);
                availableExecutive.totalEarning+=5;
                waitingOrderRecords.add(new TripRecord(availableExecutive.DE_ID, restaurant, destination, ++availableExecutive.orderCount, time, (time+0.30), 50.00));
        }
        currentTime=time;


    }
    public void displayTripRecord(){
        if(tripRecords.isEmpty()){
            System.out.println("----------->Records are Empty Wait Orders will be Assign!!");
            return;
        }
        int tripCount = 1;
        System.out.println(" TRIP   EXECUTIVE   RESTAURANT   DESTINATION POINT   ORDERS   PICKUP-TIME  DELIVERY-TIME  DELIVERY-CHARGE");
        for(TripRecord tripRecord:tripRecords){
            System.out.println("   "+tripCount+ ""+ tripRecord);
            tripCount++;
        }
    }
    public void printAvailableExecutive(){
        System.out.println("Executive     Delivery Charge Earned");
        for(Map.Entry<String, Executive> executiveEntry :executors.entrySet()){
            System.out.println("   "+executiveEntry.getKey()+"               "+executiveEntry.getValue().totalEarning);
        }
    }
}
