package be.waveit.java8.optional;

import be.waveit.java8.Computer;
import be.waveit.java8.Soundcard;
import be.waveit.java8.Usb;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class OptionalTest {

    @Test
    public void testVersionExists() {
        Usb usb = new Usb();
        Soundcard soundcard = new Soundcard(Optional.of(usb));
        Computer computer = new Computer(Optional.of(soundcard));
        Optional<Computer> oc = Optional.of(computer);

        Optional<String> version  = oc
                .flatMap(Computer::getSoundcard)
                .flatMap(Soundcard::getUsb)
                .map(Usb::getVersion);

        Assert.assertEquals(version.get(), "3.0");
    }
}
