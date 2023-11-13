import javax.usb.UsbDevice;
import javax.usb.UsbException;
import javax.usb.UsbHostManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsbDevices {

    public List<UsbDevice> getUsbServices() {
        try {
            var services = UsbHostManager.getUsbServices();
            var root = services.getRootUsbHub();
            var peripherals = (List<UsbDevice>) root.getAttachedUsbDevices();
            return new ArrayList<>(peripherals);
        } catch (UsbException e) {
            throw new RuntimeException(e);
        }
    }
}
