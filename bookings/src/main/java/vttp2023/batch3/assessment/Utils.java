package vttp2023.batch3.assessment;

import org.bson.Document;
import vttp2023.batch3.assessment.paf.bookings.models.Accoms;
import vttp2023.batch3.assessment.paf.bookings.models.Search;

public class Utils {
    public static Accoms toAccomsObject(Document doc) {
        Accoms accoms = new Accoms();
		accoms.setId(doc.getString("_id"));
        accoms.setStreet(doc.getString("street"));
        accoms.setPrice(doc.getDouble("price"));
        accoms.setImage(doc.getString("picture_url"));
        return accoms;
    }
}
