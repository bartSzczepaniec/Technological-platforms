package org.example;

import java.util.Objects;

public class Piwo {
    private String nazwa;

    private float procenty;

    public Piwo(String nazwa, float procenty) {
        this.nazwa = nazwa;
        this.procenty = procenty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piwo piwo = (Piwo) o;
        return nazwa.equals(piwo.nazwa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazwa);
    }

    @Override
    public String toString() {
        return "Piwo{" +
                "nazwa='" + nazwa + '\'' +
                ", procenty=" + procenty +
                '}';
    }

    public String getNazwa() {
        return nazwa;
    }
}
