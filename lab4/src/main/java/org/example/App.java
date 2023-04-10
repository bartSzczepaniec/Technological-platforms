package org.example;
import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("rpgPu");
        EntityManager em = factory.createEntityManager();


        em.getTransaction().begin();
        em.persist(new Browar("B1",10));
        em.getTransaction().commit();

        em.getTransaction().begin();
        em.persist(new Browar("B2",15));
        em.getTransaction().commit();

        em.getTransaction().begin();
        em.persist(new Browar("B3",12));
        em.getTransaction().commit();


        Browar b1 = em.find(Browar.class, "B1");
        Piwo p1 = new Piwo("Corona", 15, b1);
        b1.addPiwo(p1);
        em.getTransaction().begin();
        em.persist(p1);
        em.getTransaction().commit();

        p1 = new Piwo("Corona2", 17, b1);
        b1.addPiwo(p1);
        em.getTransaction().begin();
        em.persist(p1);
        em.getTransaction().commit();

        p1 = new Piwo("Corona3", 12, b1);
        b1.addPiwo(p1);
        em.getTransaction().begin();
        em.persist(p1);
        em.getTransaction().commit();

        b1 = em.find(Browar.class, "B2");

        p1 = new Piwo("Lech1", 7, b1);
        b1.addPiwo(p1);
        em.getTransaction().begin();
        em.persist(p1);
        em.getTransaction().commit();

        p1 = new Piwo("Lech2", 3, b1);
        b1.addPiwo(p1);
        em.getTransaction().begin();
        em.persist(p1);
        em.getTransaction().commit();

        p1 = new Piwo("Lech3", 4, b1);
        b1.addPiwo(p1);
        em.getTransaction().begin();
        em.persist(p1);
        em.getTransaction().commit();

        b1 = em.find(Browar.class, "B3");

        p1 = new Piwo("Piwo1", 6, b1);
        b1.addPiwo(p1);
        em.getTransaction().begin();
        em.persist(p1);
        em.getTransaction().commit();

        p1 = new Piwo("Piwo2", 12, b1);
        b1.addPiwo(p1);
        em.getTransaction().begin();
        em.persist(p1);
        em.getTransaction().commit();


        boolean quit = false;
        Scanner sc = new Scanner(System.in);
        while(!quit)
        {
            String command = sc.next();
            if(command.equals("quit"))
            {
                quit = true;
            }
            else if(command.equals("addP")) {
                String name;
                long cena;
                Browar b = null;
                name = sc.next();
                cena = sc.nextLong();
                String bname = sc.next();
                b = em.find(Browar.class, bname);
                Piwo addedPiwo = new Piwo(name, cena, b);
                if(b != null) {
                    b.addPiwo(addedPiwo);
                }
                em.getTransaction().begin();
                em.persist(addedPiwo);
                em.getTransaction().commit();
                System.out.println("Piwo dodane");
            }
            else if(command.equals("addB"))
            {
                String name;
                long wartosc;
                name = sc.next();
                wartosc = sc.nextLong();
                em.getTransaction().begin();
                em.persist(new Browar(name, wartosc));
                em.getTransaction().commit();
                System.out.println("Browar dodany");
            }
            else if(command.equals("rmp"))
            {
                String name;
                name = sc.next();
                Piwo m = em.find(Piwo.class, name);
                Browar browar = m.getBrowar();
                browar.removePiwo(m);
                em.getTransaction().begin();
                em.remove(m);
                em.getTransaction().commit();
                System.out.println("Piwo usuniete");
            }
            else if(command.equals("rmb"))
            {
                String name;
                name = sc.next();
                Browar b = em.find(Browar.class, name);
                b.nullPiwa();
                em.getTransaction().begin();
                em.remove(b);
                em.getTransaction().commit();
                System.out.println("Browar usuniety");
            }
            else if(command.equals("sP"))
            {
                String queryString = "SELECT p FROM Piwo p";
                Query query = em.createQuery(queryString, Piwo.class);
                List<Piwo> products = query.getResultList();
                System.out.println(products);
            }
            else if(command.equals("sB"))
            {
                String queryString = "SELECT b FROM Browar b";
                Query query = em.createQuery(queryString, Browar.class);
                List<Browar> products = query.getResultList();
                System.out.println(products);
            }
            else if(command.equals("zapytanie1"))
            {
                long cena = sc.nextLong();
                String queryString = "SELECT p FROM Piwo p WHERE p.cena < :cena ";
                Query query = em.createQuery(queryString, Piwo.class);
                query.setParameter("cena",cena);
                List<Piwo> products = query.getResultList();
                System.out.println(products);
            }
            else if(command.equals("zapytanie2"))
            {
                long cena = sc.nextLong();
                String browar = sc.next();
                Browar b = em.find(Browar.class, browar);
                String queryString = "SELECT p FROM Piwo p WHERE p.cena > :cena AND p.browar LIKE :browar";
                Query query = em.createQuery(queryString, Piwo.class);
                query.setParameter("cena",cena);
                query.setParameter("browar",b);
                List<Piwo> products = query.getResultList();
                System.out.println(products);
            }
        }
        em.close();
    }
}
