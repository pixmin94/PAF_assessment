package vttp2023.batch3.assessment.paf.bookings.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Accoms {

    private String id;

    private String street;

    private Double price;

    private String image;

}
