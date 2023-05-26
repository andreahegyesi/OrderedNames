package org.example;
import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
/**
 * Small application which loads from a file a structure like first name, last name, date of birth and
 * writes back another file containing only first name,last name ordered alphabetically for all of the matches
 * which have the birthday on an indicated month.
 * Application takes 3 parameters: input filename, target month(1-12), output file name
 */
public class OrderApp {
    static List<Person> orderedList = new ArrayList<>();
    static List<Person> people = new ArrayList<>();
    String inputFileName;
    int targetMonth;
    String outputFileName;

    public static List<Person> readFromCSV(String inputFileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            String line = "";
            while ((line = reader.readLine()) != null) {
                people.add(createPerson(line.split(",")));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return people;
    }

    public static void writeToCSV(String outputFileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
            for (Person p : orderedList) {
                writer.write(p.getFirstName() + "," + p.getLastName() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Person createPerson(String[] stringArray) {
        String firstName = (stringArray[0]);
        String lastName = (stringArray[1]);
        LocalDate dateOfBirth = LocalDate.parse(stringArray[2]);
        return new Person(firstName, lastName, dateOfBirth);
    }

    public static void main(String[] args) {
        OrderApp orderApp = new OrderApp();
        if (args.length == 0) {
            orderApp.inputFileName = "inputnames.csv";
            orderApp.targetMonth = 1;
            orderApp.outputFileName = "outputnames.csv";
        } else {
            orderApp.inputFileName = args[0];
            orderApp.targetMonth = Integer.parseInt(args[1]);
            orderApp.outputFileName = args[2];
        }
        orderApp.readFromCSV(orderApp.inputFileName);
        orderedList = orderApp.orderNames(orderApp.targetMonth, people);
        orderApp.writeToCSV(orderApp.outputFileName);
        System.out.println("Data file " + orderApp.outputFileName + " generated for " + Month.of(orderApp.targetMonth) + " from " + orderApp.inputFileName);
    }

    public List<Person> orderNames(int targetMonth, List<Person> personList) {
        List<Person> results = personList.stream()
                .filter(elem -> elem.getDateOfBirth().getMonthValue() == targetMonth)
                .sorted((firstP, secondP) -> firstP.getFirstName().compareTo(secondP.getFirstName()))
                .collect(Collectors.toList());
        return results;
    }
}
