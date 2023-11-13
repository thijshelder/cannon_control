import org.junit.jupiter.api.Test;

import javax.usb.UsbConst;
import javax.usb.UsbControlIrp;
import javax.usb.UsbException;

public class ListUsbDevicesTest {

    @Test
    public void should_ListUsbDevices()  {
        var devices = new UsbDevices();
        devices.getUsbServices().forEach( device -> {
            System.out.println (device.getUsbDeviceDescriptor());
            UsbControlIrp irp = device.createUsbControlIrp(
                    (byte) (UsbConst.REQUESTTYPE_DIRECTION_IN
                            | UsbConst.REQUESTTYPE_TYPE_STANDARD
                            | UsbConst.REQUESTTYPE_RECIPIENT_DEVICE),
                    UsbConst.REQUEST_GET_CONFIGURATION,
                    (short) 0,
                    (short) 0
            );
            irp.setData(new byte[0]);
            try {
                device.syncSubmit(irp);
                System.out.println(irp.getData()[0]);
            }
        catch(UsbException e )
        {
            System.out.println("nope, didnt do that " +e.getMessage());
        }
        });
    }
}
