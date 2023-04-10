package org.example;

import java.util.Optional;

public class ZarzadzaniePiwami {
    private BazaPiw baza;

    public ZarzadzaniePiwami(BazaPiw baza) {
        this.baza = baza;
    }

    public String znajdz(String nazwa) {
        Optional<Piwo> found = baza.znajdz(nazwa);
        if(found.isPresent())
        {
            Piwo piwofound = found.get();
            return piwofound.toString();
        }
        return "not found";
    }

    public String usun(String nazwa) {
        try {
            baza.usun(nazwa);
            return "done";
        } catch (IllegalArgumentException e) {
            return "not found";
        }
    }

    public String dodaj(String nazwa, float procenty) {
        try {
            baza.dodaj(new Piwo(nazwa,procenty));
            return "done";
        } catch (IllegalArgumentException e) {
            return "bad request";
        }
    }
}
