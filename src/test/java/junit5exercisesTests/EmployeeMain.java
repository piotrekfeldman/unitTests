package junit5exercisesTests;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeMain {

    public static void main(String[] args) throws IOException {

        Path inputPath = Paths.get("C:\\Users\\piotr\\unittestingjava\\src\\test\\resources\\EmployeeData.txt");
        List<String> lines = Files.readAllLines(inputPath, Charset.defaultCharset());
      //  lines.forEach(System.out::println);

        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            if(lines.get(i).equals("EmployeeData")){
                String [] name = lines.get(i + 1).split(",");
                int age = Integer.parseInt(lines.get(i + 2));
                boolean fullTime = lines.get(i + 3).equals("y");

                Employee employee = new Employee(name[0],name[1],age,fullTime);
                employees.add(employee);

            }

        }
        employees.get(0).setLastName("Pomidor");
        employees.forEach(System.out::println);

        Path outputFile =  Paths.get("C:\\Users\\piotr\\unittestingjava\\src\\test\\resources\\EmployeeData.json");
        Files.write(outputFile, toJson(employees).getBytes(Charset.defaultCharset()));
    }

    private static String toJson(List<Employee> employees) {

        String empl = employees.stream()
                .map(Employee::toJson)
                .collect(Collectors.joining(","));
        return "{\"employees\": ["+ empl +"]}";

    }

}

