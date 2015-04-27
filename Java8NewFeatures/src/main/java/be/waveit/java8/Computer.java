package be.waveit.java8;

import java.util.Optional;

public class Computer {
    private Optional<Soundcard> soundcard;

    public Computer(Optional<Soundcard> soundcard) {
        this.soundcard = soundcard;
    }

    public Optional<Soundcard> getSoundcard() {
        return soundcard;
    }
}
