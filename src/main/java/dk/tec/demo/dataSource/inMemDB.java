package dk.tec.demo.dataSource;

import dk.tec.demo.Models.Person;

import java.util.ArrayList;
import java.util.List;

public class inMemDB {
    private static int nextId = 1000;
    private static List<Person> persList;
    private static inMemDB instance;

    private int getNextId() {
        return nextId++;
    }

    private inMemDB() {
        persList = new ArrayList<>();
        persList.add(new Person(getNextId(), "salazar", "Zal@email.com", "Cool he is i guess"));
    }

    public static synchronized inMemDB getInstance() {
        if(instance == null){
            instance = new inMemDB();
        }
        return instance;
    }

    public List<Person> getAllPersons() {
        return persList;
    }
    public Person getPersen(int persId) {
        for(Person p: persList){
            if(p.getPersId() == persId) {
                return p;
            }
        }
        return null;
    }
    public int updatePersList(int persId, Person pers) {
        for (Person p: persList) {
            if(p.getPersId() == persId) {
                pers.setPersId(persId);
                persList.set(persList.indexOf(p), pers);
                return 1;
            }
        }
        return 0;
    }
    public int deleteFromPersList(int persId) {
        for(Person p: persList) {
            if(p.getPersId() == persId) {
                persList.remove(p);
                return 1;
            }
        }
        return 0;
    }
    public int insertIntoPersList(Person pers) {
        pers.setPersId(getNextId());
        persList.add(pers);
        return 1;
    }
}
