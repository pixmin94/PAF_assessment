package vttp2023.batch3.assessment;

import org.bson.Document;
import vttp2023.batch3.assessment.paf.bookings.models.Accoms;
import vttp2023.batch3.assessment.paf.bookings.models.Details;

public class Utils {
    public static Accoms toAccomsObject(Document doc) {
        Accoms accoms = new Accoms();
		accoms.setId(doc.getString("_id"));
        accoms.setStreet(doc.getString("street"));
        accoms.setPrice(doc.getDouble("price"));
        accoms.setImage(doc.getString("picture_url"));
        return accoms;
    }

    public static Details toDetailsObject(Document doc) {
        Details details = new Details();
        details.setId(doc.getString("_id"));
        details.setDescription(doc.getString("description"));
        details.setStreet(doc.getString("street"));
        details.setSuburb(doc.getString("suburb"));
        details.setCountry(doc.getString("country"));
        details.setImage(doc.getString("picture_url"));
        details.setPrice(doc.getDouble("price"));
        details.setAmenities(doc.getList("amenities", String.class));
        return details;
    }
}
