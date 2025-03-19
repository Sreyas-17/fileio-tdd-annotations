package com.bridgelabz.tdd;

import java.util.*;

//Ride class to initialize a type of ride
class Ride {

    private String userId;
    private String rideType;
    private int distance;
    private int time;

    public Ride(String userId,String rideType,int distance,int minutes){

        this.rideType=rideType;
        this.userId=userId;
        this.distance= distance;
        this.time=minutes;
    }

    public String getUserId(){
        return userId;
    }
    public String getRideType(){
        return rideType;
    }

    //Calculate fare for each ride
    public int calculateFare() {
        int fare;
        if (rideType.equals("Normal")) {
            fare = (distance * 10) + (time);
            return (fare < 5) ? 5 : fare;
        }
        else if (rideType.equals("Premium")) {
            fare = (distance * 15) + (time * 2);
            return (fare < 20) ? 20 : fare;

        }
        else {
            throw new IllegalArgumentException("Invalid ride type: " + rideType);
        }
    }
}

//Invoice class to generate invoice
class Invoice{

    static ArrayList <Ride> rideRepository= new ArrayList<>();
    public static void addRide(Ride ride){
        rideRepository.add(ride);
    }

    //To generate an invoice for all rides of a particular user
    public static int[] generateInvoice(String userId) {
        int totalFare = 0;
        int totalRides = 0;

        for (Ride ride : rideRepository) {
            if (ride.getUserId().equals(userId)) {
                totalFare += ride.calculateFare();
                totalRides++;
            }
        }

        int averageFarePerRide = (totalRides == 0) ? 0 : totalFare / totalRides;
        return new int[]{totalFare, totalRides, averageFarePerRide};

    }
    //Calculate total fare of all rides
    public static int totalFare(){
        int totalFare = 0;

        for (Ride ride : rideRepository) {
            totalFare += ride.calculateFare();
        }

        return totalFare;
    }
    //To generate an invoice for all rides
    public static int[] enhancedInvoice(){

        int totalRides = rideRepository.size();
        int averageFarePerRide = (totalRides == 0) ? 0 : totalFare() / totalRides;

        return new int[]{totalFare(), totalRides, averageFarePerRide};

    }
}


public class CabInvoiceGenerator {
    public static void main(String[] args) {

        //Objects of ride
        Ride ride1 = new Ride("User1", "Normal", 10, 5);
        Ride ride2 = new Ride("User1", "Premium", 15, 10);
        Ride ride3 = new Ride("User2", "Normal", 5, 3);

        //Adding rides to  ride repository
        Invoice.addRide(ride1);
        Invoice.addRide(ride2);
        Invoice.addRide(ride3);

        int[] invoiceUser1 = Invoice.generateInvoice("User1");
        System.out.println("Invoice for User1: Total Fare = " + invoiceUser1[0] + ", Total Rides = " + invoiceUser1[1] + ", Average Fare = " + invoiceUser1[2]);

        int[] invoiceUser2 = Invoice.generateInvoice("User2");
        System.out.println("Invoice for User2: Total Fare = " + invoiceUser2[0] + ", Total Rides = " + invoiceUser2[1] + ", Average Fare = " + invoiceUser2[2]);

        int[] completeInvoice = Invoice.enhancedInvoice();
        System.out.println("Complete Invoice:\nTotal Fare = " + completeInvoice[0] + ", Total Rides = " + completeInvoice[1] + ", Average Fare = " + completeInvoice[2]);
    }
}
