package org.example;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.assertj.core.api.Assertions.*;
public class BazaPiwTest {

    BazaPiw bazaPiw;

    @Before
    public void setPiwaTest(){
        bazaPiw = new BazaPiw();
        bazaPiw.dodaj(new Piwo("piwo1",4));
        bazaPiw.dodaj(new Piwo("piwo2",3.5F));
        bazaPiw.dodaj(new Piwo("piwo3",2));
        bazaPiw.dodaj(new Piwo("piwo4",5.5F));
        bazaPiw.dodaj(new Piwo("piwo5",7));
    }

    @Test
    public void notFoundFindTest(){
        Optional<Piwo> mfound = bazaPiw.znajdz("test");
        assertThat(mfound).isEqualTo(Optional.empty());
    }

    @Test
    public void foundFindTest(){
        Optional<Piwo> mfound = bazaPiw.znajdz("piwo4");
        Piwo m = mfound.get();
        Piwo tester = new Piwo("piwo4",5.5F);
        assertThat(m).isEqualTo(tester);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeTest(){
        bazaPiw.usun("testowy");
    }

    @Test(expected = IllegalArgumentException.class)
    public void theSameSaveTest(){
        Piwo tester = new Piwo("piwo4",5.5F);
        bazaPiw.dodaj(tester);
    }

}
