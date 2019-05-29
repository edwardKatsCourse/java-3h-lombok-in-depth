package com.telran;

import lombok.*;
import lombok.experimental.Wither;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        //Lombok (Intellij)
        //1. Lombok plugin
        //2. Enable annotation processing
        //3. Add lombok to maven project


        //Java compilation:

        //1. Phase 1 - оценка всего, что нужно превратить
        //в байт код

        //<---- lombok - дописывает код, согласно аннотациям в классах/методах

        //2. Phase 2 - Компиляция (превращение в байт код)
        //2.1. проверки(валидация): типы совпадают, что методы/классы
        //существуют

        //2.2. Сама компиляция

//        Arrays
//                .stream(Person.class.getDeclaredMethods())
//                .map(x -> x.getName())
//                .forEach(x -> System.out.println(x));

        Properties properties = new Properties();
        InputStream is = Main.class.getClassLoader()
                .getResourceAsStream("application.properties");

        properties.load(is);

//        inputStreamExample();


        Person person = Person.builder()
                .age(41)
                .email("john@site.com")
                .name("John")
                .isResident(false)
                .address(null)
                .phoneNumber("number 1")
                .phoneNumber("number 2")
                .phoneNumber("number 3")
                .phoneNumber("number 4")
                .build();

        System.out.println(person.toString());

    }


    @SneakyThrows
    private static void inputStreamExample() {
        try {
            new FileInputStream("asdasd") //IOException
                    .close();
        } catch (ArithmeticException e) {
            System.out.println("file not found");
            throw new RuntimeException(e);
        }
    }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
class Person {
    private String email;
    private String name;
    private Integer age;
    private String address;
    private Boolean isResident;
    private FamilyStatus familyStatus; //{"familyStatus" : "Single"}

    @Singular("phoneNumber")
    private List<String> phoneNumbers;
}

@AllArgsConstructor
@Getter
enum FamilyStatus {
    SINGLE(1, "Single"),
    MARRIED(2, "Married"),
    DIVORCED(3, "Divorced"),
    WIDOW(4, "Widow")
    ;

    private Integer id; //for database
    private String statusName;
}