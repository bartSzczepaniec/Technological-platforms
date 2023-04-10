package org.example;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity

public class Piwo {
    @Id
    private String name;

    private long cena;

    @ManyToOne
    private Browar browar;

    public Piwo(String name, long cena, Browar browar) {
        this.name = name;
        this.cena = cena;
        this.browar = browar;
    }
    public Piwo()
    {
        this.name="";
        this.cena = 0;
        this.browar =null;
    }

    public long getCena() {
        return cena;
    }

    public Browar getBrowar() {
        return browar;
    }

    public void setBrowar(Browar browar) {
        this.browar = browar;
    }

    @Override
    public String toString() {
        if(browar == null)
        {
            return "Piwo{" +
                "name='" + name + '\'' +
                ", cena=" + cena +
                '}';
        }
        else{
            return "Piwo{" +
                    "name='" + name + '\'' +
                    ", cena=" + cena + ' ' + "browar: " + browar.getName() +
                    '}';
        }
    }

}
