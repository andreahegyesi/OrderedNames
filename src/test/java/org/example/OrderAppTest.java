package org.example;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class OrderAppTest {
    @Test
    void givenThreePeopleAndTargetMonthExpectedCorrectSorting() {
        OrderApp testApp = new OrderApp();
        List <Person> personTestList = new ArrayList<>();
        Person person1 =  new Person("V", "B", LocalDate.of(1988, 5, 9));
        Person person2 = new Person("D", "E", LocalDate.of(1992,8,20));
        Person person3 = new Person("P", "R", LocalDate.of(1992,5,25));
        personTestList.add(person1);
        personTestList.add(person2);
        personTestList.add(person3);
        List<Person> personTestListCopy = personTestList;
        assertEquals("P", testApp.orderNames(5,personTestList).get(0).getFirstName());


    }

    @Test
    void createPerson() {
        OrderApp testApp =  new OrderApp();
        String[] testArray = {"Andrea", "Hegyesi", "1995-07-12"};
        Person testPerson = testApp.createPerson(testArray);
        assertEquals("Andrea", testPerson.getFirstName());
        assertEquals("Hegyesi", testPerson.getLastName());
        assertEquals(LocalDate.of(1995,07,12), testPerson.getDateOfBirth());
    }
}