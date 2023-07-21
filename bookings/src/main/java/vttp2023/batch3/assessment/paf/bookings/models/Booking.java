package vttp2023.batch3.assessment.paf.bookings.models;

import java.sql.Date;

public record Booking(
    Integer accId, String custName, 
    String email, Date arrival, Integer duration ){
}
