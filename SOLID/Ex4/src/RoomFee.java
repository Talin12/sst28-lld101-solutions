import java.util.HashMap;
import java.util.Map;

public class RoomFee implements FeeComponent {
    private final Map<Integer, Double> rates = new HashMap<>();

    public RoomFee() {
        // Default seed data
        rates.put(LegacyRoomTypes.SINGLE, 14000.0);
        rates.put(LegacyRoomTypes.DOUBLE, 15000.0);
        rates.put(LegacyRoomTypes.TRIPLE, 12000.0);
        rates.put(LegacyRoomTypes.DELUXE, 16000.0);
    }

    public void addRate(int roomType, double rate) {
        rates.put(roomType, rate);
    }

    @Override
    public double calculate(BookingRequest req) {
        return rates.getOrDefault(req.roomType, 16000.0); // 16000.0 was the default in the original switch
    }
}