import java.util.Collections;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    if (args[0].equals("DR")) {
      System.out.println("Deadlock Recovery Simulation\n");
      // Stores all chopsticks
      ArrayList<Chopstick> chopsticks = new ArrayList<>();
      // Store all philosophers
      ArrayList<Philosopher> philosophers = new ArrayList<>();

      // The number of chopsticks and philosophers to be used
      int n = 5;

      // initializes the chopsticks
      for (int i = 0; i < n; i++) {
        chopsticks.add(new Chopstick(i));
      }

      // initializes the philosophers with their corresponding chopstick
      for (int i = 0; i < n; i++) {
        if (i == 2) {
          Chopstick leftChopstick = chopsticks.get((i + 1) % n);
          Chopstick rightChopstick = chopsticks.get(i);
          Chopstick thirdChopstick = chopsticks.get(0);
          String id = i + " (" + leftChopstick.getName() + ", " + rightChopstick.getName() + ", "
              + thirdChopstick.getName() + ")";
          Philosopher p = new Philosopher(id, leftChopstick, rightChopstick, thirdChopstick, philosophers, "DR");
          philosophers.add(p);
        } else {
          Chopstick leftChopstick = chopsticks.get((i + 1) % n);
          Chopstick rightChopstick = chopsticks.get(i);
          String id = i + " (" + leftChopstick.getName() + ", " + rightChopstick.getName() + ")";
          Philosopher p = new Philosopher(id, leftChopstick, rightChopstick, philosophers, "DR");
          philosophers.add(p);
        }
      }

      // shuffles the philosophers order in the array for more randomness
      Collections.shuffle(philosophers);

      // creates and starts a new thread for each philosopher
      for (Philosopher philosopher : philosophers) {
        Thread t = new Thread(philosopher);
        t.start();
      }
    } else if (args[0].equals("RD")) {
      System.out.println("Random Drop Simulation\n");
      // Stores all chopsticks
      ArrayList<Chopstick> chopsticks = new ArrayList<>();
      // Store all philosophers
      ArrayList<Philosopher> philosophers = new ArrayList<>();

      // The number of chopsticks and philosophers to be used
      int n = 5;

      // initializes the chopsticks
      for (int i = 0; i < n; i++) {
        chopsticks.add(new Chopstick(i));
      }

      // initializes the philosophers with their corresponding chopstick
      for (int i = 0; i < n; i++) {
        if (i == 2) {
          Chopstick leftChopstick = chopsticks.get((i + 1) % n);
          Chopstick rightChopstick = chopsticks.get(i);
          Chopstick thirdChopstick = chopsticks.get(0);
          String id = i + " (" + leftChopstick.getName() + ", " + rightChopstick.getName() + ", "
              + thirdChopstick.getName() + ")";
          Philosopher p = new Philosopher(id, leftChopstick, rightChopstick, thirdChopstick, philosophers, "RD");
          philosophers.add(p);
        } else {
          Chopstick leftChopstick = chopsticks.get((i + 1) % n);
          Chopstick rightChopstick = chopsticks.get(i);
          String id = i + " (" + leftChopstick.getName() + ", " + rightChopstick.getName() + ")";
          Philosopher p = new Philosopher(id, leftChopstick, rightChopstick, philosophers, "RD");
          philosophers.add(p);
        }
      }

      // shuffles the philosophers order in the array for more randomness
      Collections.shuffle(philosophers);

      // creates and starts a new thread for each philosopher
      for (Philosopher philosopher : philosophers) {
        Thread t = new Thread(philosopher);
        t.start();
      }
    } else {
      System.out.println("Incorrect Arguments. Enter 'DR' or 'RD'.");
    }
  }
}
