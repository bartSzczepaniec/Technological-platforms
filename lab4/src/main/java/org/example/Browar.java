package org.example;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Browar {
    @Id
    private String name;

    private long wartosc;

    @OneToMany(mappedBy = "browar")
    private List<Piwo> piwa;

    public Browar(){
        this.name=null;
        this.wartosc =0;
        this.piwa = new ArrayList<>();
    }
    public Browar(String name, long wartosc)
    {
        this.name = name;
        this.wartosc = wartosc;
        this.piwa = new ArrayList<>();
    }
    public void addPiwo(Piwo m)
    {
        this.piwa.add(m);
        if(m.getBrowar() != this) {
            m.setBrowar(this);
        }
    }

    public void removePiwo(Piwo m){
        piwa.remove(m);
    }

    public void nullPiwa(){
        for (int i = 0; i< piwa.size(); i++)
        {
            piwa.get(i).setBrowar(null);
        }
    }

    @Override
    public String toString() {
        return "Browar{" +
                "name='" + name + '\'' +
                ", wartosc=" + wartosc +
                ", piwa=" + piwa +
                '}';
    }

    public String getName() {
        return name;
    }
}
