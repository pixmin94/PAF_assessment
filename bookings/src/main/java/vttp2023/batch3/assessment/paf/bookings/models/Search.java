package vttp2023.batch3.assessment.paf.bookings.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Search {
    @NotEmpty(message = "Please choose a country")
    private String country;

    @Min(value = 1, message = "Number of persons must be 1-10")
    @Max(value = 1, message = "Number of persons must be 1-10")
    private Integer pax;

    @Min(value = 1, message = "Price range must be 1-10,000")
    private Integer min;

    @Max(value = 10000, message = "Price range must be 1-10,000")
    private Integer max;
}
