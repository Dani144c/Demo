package dk.tec.demo.dataService;

import dk.tec.demo.Models.Person;
import dk.tec.demo.dataSource.MariaDB;
import dk.tec.demo.dataSource.inMemDB;

import java.util.ArrayList;
import java.util.List;

public class PersonDataService {

    public List<Person> getAllPersons() {
        MariaDB db = new MariaDB();
        return db.getAllPersons();
    }

    public Person getPerson(int persId){
        MariaDB db = new MariaDB();
        return db.getPerson(persId);
    }

    public int updatePerson(int persId, Person pers) {
        MariaDB db = new MariaDB();
        return db.updatePersList(persId, pers);
    }

    public int deletePerson(int persId) {
        MariaDB db = new MariaDB();
        return db.deletePerson(persId);
    }

    public int addPerson(Person pers){
        MariaDB db = new MariaDB();
        return db.addPerson(pers);
    }
}
