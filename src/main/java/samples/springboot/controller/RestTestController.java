package samples.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import samples.springboot.controller.exception.Exception1;
import samples.springboot.controller.exception.Exception2;
import samples.springboot.controller.exception.Exception3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by izeye on 14. 12. 9..
 */
@RestController
@RequestMapping("/test/rest")
public class RestTestController {

  @RequestMapping("/persons")
  public List<Person> persons() {
    List<Person> persons = new ArrayList<>();
    persons.add(new Person("Johnny", 34));
    persons.add(new Person("John", 20));
    return persons;
  }

  @RequestMapping("/exception1")
  public List<Person> exception1() {
    throw new Exception1("Exception 1");
  }

  @RequestMapping("/exception2")
  public List<Person> exception2() {
    throw new Exception2("Exception 2");
  }

  @RequestMapping("/exception3")
  public List<Person> exception3() {
    throw new Exception3("Exception 3");
  }

  class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
      this.name = name;
      this.age = age;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }

    @Override
    public String toString() {
      return "Person{" +
          "name='" + name + '\'' +
          ", age=" + age +
          '}';
    }
  }

}
