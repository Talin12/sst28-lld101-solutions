import java.util.*;

public class DeviceRegistry {
    private final List<SmartClassroomDevice> devices = new ArrayList<>();

    public void add(SmartClassroomDevice d) { devices.add(d); }

    // Finds the first device that supports a specific capability
    public <T> T getDevice(Class<T> capability) {
        for (SmartClassroomDevice d : devices) {
            if (capability.isInstance(d)) {
                return capability.cast(d);
            }
        }
        throw new IllegalStateException("Missing device with capability: " + capability.getSimpleName());
    }

    // Finds ALL devices that support a specific capability (useful for shutting down all powerable devices)
    public <T> List<T> getAllDevices(Class<T> capability) {
        List<T> result = new ArrayList<>();
        for (SmartClassroomDevice d : devices) {
            if (capability.isInstance(d)) {
                result.add(capability.cast(d));
            }
        }
        return result;
    }
}