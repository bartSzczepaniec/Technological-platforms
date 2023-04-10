package org.example;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.*;

public class PiwoControllerTest {

    @Mock
    BazaPiw bazaPiw;

    @InjectMocks
    ZarzadzaniePiwami zarzadzaniePiwami;

    @Before
    public void setPiwaTest(){
        bazaPiw = new BazaPiw();
        zarzadzaniePiwami = new ZarzadzaniePiwami(bazaPiw);
        zarzadzaniePiwami.dodaj("piwo1",5);
        zarzadzaniePiwami.dodaj("piwo2",4.5F);
        zarzadzaniePiwami.dodaj("piwo3",7);
        zarzadzaniePiwami.dodaj("piwo4",3);
        zarzadzaniePiwami.dodaj("piwo5", 2.5F);
    }

    @Test
    public void existingObjectDeleteTest(){
        String output = zarzadzaniePiwami.usun("piwo1");
        assertThat(output).isEqualTo("done");
    }

    @Test
    public void nonExistingObjectDeleteTest(){
        String output = zarzadzaniePiwami.usun("testowy");
        assertThat(output).isEqualTo("not found");
    }

    @Test
    public void nonExistingObjectFindTest(){
        String output = zarzadzaniePiwami.znajdz("testowy");
        assertThat(output).isEqualTo("not found");
    }

    @Test
    public void existingObjectFindTest(){
        String output = zarzadzaniePiwami.znajdz("piwo2");
        assertThat(output).isEqualTo("Piwo{" +
                "nazwa='" + "piwo2" + '\'' +
                ", procenty=" + "4.5" +
                '}');
    }

    @Test
    public void newObjectSaveTest(){
        String output = zarzadzaniePiwami.dodaj("piwo7",3);
        assertThat(output).isEqualTo("done");
    }

    @Test
    public void existingObjectSaveTest(){
        String output = zarzadzaniePiwami.dodaj("piwo1",5);
        assertThat(output).isEqualTo("bad request");
    }

}
