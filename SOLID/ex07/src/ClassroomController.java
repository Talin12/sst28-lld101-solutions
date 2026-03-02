public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        InputConnectable pj = reg.getDevice(InputConnectable.class);
        if (pj instanceof Powerable) {
            ((Powerable) pj).powerOn();
        }
        pj.connectInput("HDMI-1");

        BrightnessControllable lights = reg.getDevice(BrightnessControllable.class);
        lights.setBrightness(60);

        TemperatureControllable ac = reg.getDevice(TemperatureControllable.class);
        ac.setTemperatureC(24);

        Scannable scan = reg.getDevice(Scannable.class);
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        // Safely turn off all devices that have power control capabilities
        for (Powerable p : reg.getAllDevices(Powerable.class)) {
            p.powerOff();
        }
    }
}