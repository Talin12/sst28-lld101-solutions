import java.util.HashMap;
import java.util.Map;

public class AddOnFee implements FeeComponent {
    private final Map<AddOn, Double> rates = new HashMap<>();

    public AddOnFee() {
        // Default seed data
        rates.put(AddOn.MESS, 1000.0);
        rates.put(AddOn.LAUNDRY, 500.0);
        rates.put(AddOn.GYM, 300.0);
    }

    public void addRate(AddOn addOn, double rate) {
        rates.put(addOn, rate);
    }

    @Override
    public double calculate(BookingRequest req) {
        double total = 0.0;
        for (AddOn a : req.addOns) {
            total += rates.getOrDefault(a, 0.0);
        }
        return total;
    }
}