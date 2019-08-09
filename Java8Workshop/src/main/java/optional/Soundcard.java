package optional;

import java.util.Optional;

public class Soundcard {
    private Optional<Usb> usb;

    public Soundcard(Optional<Usb> usb) {
        this.usb = usb;
    }

    public Optional<Usb> getUsb() {
        return usb;
    }
}
