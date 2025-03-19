package com.bridgelabz.tdd;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class CabInvoiceGeneratorTest{

    @BeforeEach
    void setUp() {
        Invoice.rideRepository.clear();
    }

    // Test case for step1 calculate fare

    @Test
    void testCalculateFareForNormalRide() {
        Ride ride = new Ride("User1", "Normal", 10, 5);
        int expectedFare = (10 * 10) + 5;
        assertEquals(expectedFare, ride.calculateFare(), "Normal Ride fare calculation failed");
    }

    @Test
    void testCalculateMinimumFareForNormalRide() {
        Ride ride = new Ride("User1", "Normal", 0, 1);
        assertEquals(5, ride.calculateFare(), "Minimum fare should be Rs.5 for Normal Ride");
    }

    @Test
    void testCalculateFareForPremiumRide() {
        Ride ride = new Ride("User1", "Premium", 15, 10);
        int expectedFare = (15 * 15) + (10 * 2);
        assertEquals(expectedFare, ride.calculateFare(), "Premium Ride fare calculation failed");
    }

    @Test
    void testCalculateMinimumFareForPremiumRide() {
        Ride ride = new Ride("User1", "Premium", 0, 1);
        assertEquals(20, ride.calculateFare(), "Minimum fare should be Rs.20 for Premium Ride");
    }

    //Multiple rides 2nd Step test case

    @Test
    void testTotalFareForMultipleNormalRides() {

        Invoice.addRide(new Ride("User1", "Normal", 10, 5));
        Invoice.addRide(new Ride("User1", "Normal", 5, 3));

        int expectedTotalFare = 105 + 53;
        assertEquals(expectedTotalFare, Invoice.totalFare(), "Total fare for normal rides failed");
    }

    @Test
    void testTotalFareForMultiplePremiumRides() {

        Invoice.addRide(new Ride("User1", "Premium", 10, 5));
        Invoice.addRide(new Ride("User1", "Premium", 5, 3));

        int expectedTotalFare = 160 + 81;
        assertEquals(expectedTotalFare, Invoice.totalFare(), "Total fare for premium rides failed");
    }

    @Test
    void testTotalFareForMixedRides() {
        Invoice.addRide(new Ride("User1", "Normal", 10, 5));
        Invoice.addRide(new Ride("User1", "Premium", 5, 3));
        int expectedTotalFare = 105 + 81;
        assertEquals(expectedTotalFare, Invoice.totalFare(), "Total fare for mixed rides failed");
    }

    //Test case for step 3 enhanced invoice
    @Test
    void testEnhancedInvoice() {
        Invoice.addRide(new Ride("User1", "Normal", 10, 5));
        Invoice.addRide(new Ride("User1", "Premium", 5, 3));

        int expectedTotalFare = 105 + 81;
        int expectedTotalRides = 2;
        int expectedAverageFare =expectedTotalFare/expectedTotalRides;

        assertArrayEquals(new int[]{expectedTotalFare,expectedTotalRides,expectedAverageFare},
                Invoice.enhancedInvoice(), "Enhanced Invoice for multiple rides failed");
    }

    //Test case for step 4 invoice service
    @Test
    void testGenerateInvoice(){
        Invoice.addRide(new Ride("User1", "Normal", 10, 5));
        Invoice.addRide(new Ride("User1", "Normal", 12, 6));

        int expectedTotalFare = 231;
        int expectedTotalRides = 2;
        int expectedAverageFare =expectedTotalFare/expectedTotalRides;

        assertArrayEquals(new int[]{expectedTotalFare,expectedTotalRides,expectedAverageFare},
                Invoice.generateInvoice("User1"), "Invoice service for user failed");

    }
}