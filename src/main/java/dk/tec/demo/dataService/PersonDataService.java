package dk.tec.demo.dataService;

import dk.tec.demo.Models.Person;
import dk.tec.demo.dataSource.MariaDB;
import dk.tec.demo.dataSource.inMemDB;

import java.util.ArrayList;
import java.util.List;

public class PersonDataService {

    public List<Person> getAllPersons() {
        MariaDB dbh = new MariaDB();
        return inMemDB.getInstance().getAllPersons();
    }

    public Person getPerson(int persId){
        return inMemDB.getInstance().getPersen(persId);
    }

    public int updatePerson(int persId, Person pers) {
        return inMemDB.getInstance().updatePersList(persId, pers);
    }

    public int deletePerson(int persId) {
        return inMemDB.getInstance().deleteFromPersList(persId);
    }

    public int addPerson(Person pers){
        return inMemDB.getInstance().insertIntoPersList(pers);
    }
}
