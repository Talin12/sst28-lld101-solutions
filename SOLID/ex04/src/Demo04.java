import java.util.*;

public class Demo04 {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");
        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));
        
        // Assemble the pricing components
        List<FeeComponent> components = new ArrayList<>();
        components.add(new RoomFee());
        components.add(new AddOnFee());

        HostelFeeCalculator calc = new HostelFeeCalculator(new FakeBookingRepo(), components);
        calc.process(req);
    }
}