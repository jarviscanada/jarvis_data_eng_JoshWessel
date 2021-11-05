package ca.jrvs.apps.practice;

public class ArrayListAPIs {
  public static void main(String[] args) {

    JList<String> animals = new ArrayJList<>();
    animals.add("Lion");
    animals.add("Tiger");
    animals.add("Bear");

    System.out.println("Number of animals: " + animals.size());

    System.out.println("Animal 1: " + animals.get(0));
    System.out.println("Animal 2: " + animals.get(1));
    System.out.println("Animal 3: " + animals.get(2));

    System.out.println("Animals contains Bear: " + animals.contains("Bear"));
    System.out.println("Animals contains Oh My: " + animals.contains("Oh My"));
  }
}
