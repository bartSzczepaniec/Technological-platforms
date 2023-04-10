package org.example;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class BazaPiw {
    private Collection<Piwo> piwa;

    public BazaPiw() {
        piwa = new HashSet<Piwo>();
    }

    public Optional<Piwo> znajdz(String nazwa) {
        Optional<Piwo> found = Optional.empty();
        for(Piwo p: piwa) {
            if(p.getNazwa().equals(nazwa))
            {
                found = Optional.of(p);
            }
        }
        return found;
    }

    public void usun(String nazwa) {
        Optional<Piwo> f = znajdz(nazwa);
        if(!f.isPresent()) {
            throw new IllegalArgumentException();
        }
        Piwo p = f.get();
        piwa.remove(p);
    }

    public void dodaj(Piwo p) {
        if(!piwa.add(p)){
            throw new IllegalArgumentException();
        }
    }
}
