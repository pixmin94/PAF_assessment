package vttp2023.batch3.assessment.paf.bookings.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Details {
    private String id;

    private String description;

    private String street;

    private String suburb;

    private String country;

    private String image;

    private Double price;

    private List<String> amenities;

}
