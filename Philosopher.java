
/**
 * CSDS 338: The Philosopher class to be used with the Simulator 2 project
 * Simulator 2: Dining Philosophers Problem
 * @author Josh Levy
 * @author Max Day
 * @author Steve Pham
 * @author Maggie Clark Fairfield
 */

import java.lang.Math;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Philosopher extends Thread {
    /* Probability used for dropping */
    private double droppingProbability;
    /* The name of the philosopher */
    private final String philosopherName;
    /* The philosophers right chopstick */
    private final Chopstick rightChopstick;
    /* The philosophers left chopstick */
    private final Chopstick leftChopstick;
    /* The philosophers third chopstick (optional) */
    private Chopstick thirdChopstick;
    /* The philosophers at the table */
    private final ArrayList<Philosopher> philosophers;
    /* Determines if the current philosopher has recently eaten */
    private boolean isFull;
    /* Keeps track if there is a philosopher at the table currently eating */
    private boolean isEating;
    /*
     * Doesn't allow a philosopher to pick up a chopstick if they have just put one
     * down
     */
    private boolean pickupCooldown;

    private final String implementation;

    /**
     * Constructor for the Philosopher class
     * 
     * @param name           is the name of the philosopher
     * 
     * @param rightChopstick is the right chopstick to the philosopher
     * 
     * @param leftChopstick  is the left chopstick to the philosopher
     */
    public Philosopher(String philosopherName, Chopstick leftChopstick, Chopstick rightChopstick,
            ArrayList<Philosopher> philosophers, String implementation) {
        this.philosopherName = philosopherName;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        this.thirdChopstick = null;
        /* Probability that a chopstick will be dropped */
        this.droppingProbability = Math.random();
        this.philosophers = philosophers;
        this.pickupCooldown = true;
        this.implementation = implementation;
    }

    /**
     * Constructor for the Philosopher class
     * 
     * @param _name          is the name of the philosopher as an integer value
     * 
     * @param rightChopstick is the right chopstick to the philosopher
     * 
     * @param leftChopstick  is the left chopstick to the philosopher
     * 
     * @param leftChopstick  is the left chopstick to the philosopher
     * 
     * @param thirdChopstick is the third chopstick to the philosopher
     */
    public Philosopher(String philosopherName, Chopstick leftChopstick, Chopstick rightChopstick,
            Chopstick thirdChopstick, ArrayList<Philosopher> philosophers, String implementation) {
        this(philosopherName, leftChopstick, rightChopstick, philosophers, implementation);
        this.thirdChopstick = thirdChopstick;
    }

    /**
     * @return The probability of dropping a Chopstick for a Philosopher
     */
    public double getDroppingProbability() {
        return droppingProbability;
    }

    /**
     * @param droppingProbability Sets the probability of dropping a Chopstick for a
     *                            Philosopher
     */
    public void setDroppingProbability(double droppingProbability) {
        this.droppingProbability = droppingProbability;
    }

    /**
     * @return The left Chopstick of a Philosopher
     */
    public Chopstick getLeftChopstick() {
        return leftChopstick;
    }

    /**
     * @return The right Chopstick of a Philosopher
     */
    public Chopstick getRightChopstick() {
        return rightChopstick;
    }

    /**
     * @return The third Chopstick of a Philosopher
     */
    public Chopstick getThirdChopstick() {
        return thirdChopstick;
    }

    /**
     * @return The ArrayList of all Philosophers at the table
     */
    public ArrayList<Philosopher> getPhilosophers() {
        return philosophers;
    }

    public String getPhilosopherName() {
        return philosopherName;
    }

    public String getImplementation() {
        return implementation;
    }

    /**
     * @param prob The probability of picking up the left Chopstick
     */
    public void pickupLeftChopstick() throws InterruptedException {
        if (!leftChopstick.isUsed() && pickupCooldown) {
            leftChopstick.pickup(this);
            // System.out.println(this + " picked up " + leftChopstick + " | TIME: "
            // + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);
            System.out.println(this + " | " + "picked up" + " | " + leftChopstick + " | "
                    + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);
            if (!rightChopstick.isUsed()) {
                Thread.sleep((long) (getDroppingProbability() * 1000));
            }
        }
        setDroppingProbability(Math.random());
    }

    /**
     * @param prob The probability of picking up the right Chopstick
     */
    public void pickupRightChopstick() throws InterruptedException {
        if (!rightChopstick.isUsed() && pickupCooldown) {
            rightChopstick.pickup(this);
            // System.out.println(this + " picked up " + rightChopstick + " | TIME: "
            // + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);
            System.out.println(this + " | " + "picked up" + " | " + rightChopstick + " | "
                    + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);
            if (!leftChopstick.isUsed()) {
                Thread.sleep((long) (getDroppingProbability() * 1000));
            }
        }
        setDroppingProbability(Math.random());
    }

    /**
     * @param prob The probability of picking up the third Chopstick
     */
    public void pickupThirdChopstick() throws InterruptedException {
        if (thirdChopstick != null && !thirdChopstick.isUsed() && pickupCooldown) {
            thirdChopstick.pickup(this);
            // System.out.println(this + " picked up " + thirdChopstick + " | TIME: "
            // + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);
            System.out.println(this + " | " + "picked up" + " | " + thirdChopstick + " | "
                    + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);
            Thread.sleep((long) (getDroppingProbability() * 1000));
        }
        setDroppingProbability(Math.random());
    }

    /**
     * checks if left and right chopsticks are currently in use by the current
     * philosopher and that the holders are correct will set the current status to
     * 'eating' if The Philosopher is eating
     * 
     * @throws InterruptedException
     */
    public void eat() throws InterruptedException {
        if (rightChopstick.isUsed() && leftChopstick.isUsed() && thirdChopstick == null) {
            if (rightChopstick.getHolder().getPhilosopherName().equals(this.getPhilosopherName())
                    && leftChopstick.getHolder().getPhilosopherName().equals(getPhilosopherName())) {

                // System.out.println(this + " is eating ... | TIME: "
                // + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);
                System.out.println(this + " | " + "is eating" + " | "
                        + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);

                isEating = true;
                Thread.sleep((long) (getDroppingProbability() * 5000));

                // System.out.println(this + " finished eating... | TIME: "
                // + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);
                System.out.println(this + " | " + "finished eating" + " | "
                        + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);

                setDroppingProbability(Math.random());
                dropChopsticks(1.0);
                isFull = false;
                isEating = false;
            }
        }
        if (thirdChopstick != null && thirdChopstick.isUsed() && rightChopstick.isUsed() && leftChopstick.isUsed()) {
            if (thirdChopstick.getHolder().getPhilosopherName().equals(this.getPhilosopherName())
                    && rightChopstick.getHolder().getPhilosopherName().equals(this.getPhilosopherName())
                    && leftChopstick.getHolder().getPhilosopherName().equals(getPhilosopherName())) {
                // System.out.println(this + " is eating ... | TIME: "
                // + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);
                System.out.println(this + " | " + "is eating" + " | "
                        + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);

                isEating = true;
                Thread.sleep((long) (getDroppingProbability() * 5000));

                // System.out.println(this + " finished eating | TIME: "
                // + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);
                System.out.println(this + " | " + "finished eating" + " | "
                        + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);

                setDroppingProbability(Math.random());
                dropChopsticks(1.0);
                isFull = false;
                isEating = false;
            }
        }
    }

    /**
     * checks if left and right chopsticks are currently in use by the current
     * philosopher will set the current status to 'thinking' if The Philosopher is
     * thinking
     * 
     * @throws InterruptedException
     */
    public void think() throws InterruptedException {
        if ((!rightChopstick.isUsed() && !leftChopstick.isUsed() && thirdChopstick == null)) {

            // System.out.println(this + " is thinking... | TIME: "
            // + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);
            System.out.println(this + " | " + "is thinking" + " | "
                    + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);

            Thread.sleep((long) (getDroppingProbability() * 2000));
            // System.out.println(this + " is hungry... | TIME: "
            // + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);
            System.out.println(this + " | " + "is hungry" + " | "
                    + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);
        }
        if (!rightChopstick.isUsed() && !leftChopstick.isUsed() && thirdChopstick != null && !thirdChopstick.isUsed()) {

            // System.out.println(this + " is thinking... | TIME: "
            // + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);
            System.out.println(this + " | " + "is thinking" + " | "
                    + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);

            Thread.sleep((long) (getDroppingProbability() * 2000));

            // System.out.println(this + " is hungry... | TIME: "
            // + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);
            System.out.println(this + " | " + "is hungry" + " | "
                    + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);
        }
        setDroppingProbability(Math.random());
    }

    /**
     * functions to drop the chopsticks conditionally
     * 
     * @param prob The probability that a Chopstick will be dropped
     */
    public void dropChopsticks(double prob) throws InterruptedException {
        if (leftChopstick.isUsed() && leftChopstick.getHolder() == this && (0.3 <= prob || 1.0 == prob)) {
            // System.out.println(this + " dropped " + leftChopstick + " | TIME: "
            // + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);
            System.out.println(this + " | " + "dropped" + " | " + leftChopstick + " | "
                    + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);
            leftChopstick.drop();
        }
        if (rightChopstick.isUsed() && rightChopstick.getHolder() == this && (0.3 <= prob || 1.0 == prob)) {
            // System.out.println(this + " dropped " + rightChopstick + " | TIME: "
            // + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);
            System.out.println(this + " | " + "dropped" + " | " + rightChopstick + " | "
                    + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);
            rightChopstick.drop();
        }
        if (thirdChopstick != null && thirdChopstick.isUsed() && thirdChopstick.getHolder() == this
                && (0.3 <= prob || 1.0 == prob)) {
            // System.out.println(this + " dropped " + thirdChopstick + " | TIME: "
            // + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);
            System.out.println(this + " | " + "dropped" + " | " + thirdChopstick + " | "
                    + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);
            thirdChopstick.drop();
        }
        pickupCooldown = false;
        Thread.sleep((long) (3000));
        pickupCooldown = true;
    }

    /**
     * @return A string representation of a Philosopher
     */
    @Override
    public String toString() {
        return "Philosopher " + philosopherName;
    }

    /*
     * function to check if the current state of the table is in a deadlock, if no
     * philosophers are able to eat and are all just holding only one chopstick -
     * they then drop their chopsticks
     */
    public synchronized void checkDeadlock() throws InterruptedException {
        boolean drop = true;
        boolean someoneEating = false;
        for (Philosopher philosopher : getPhilosophers()) {
            for (Philosopher philosopherEating : getPhilosophers()) {
                if (philosopherEating.isEating) {
                    someoneEating = true;
                }
            }
            if (((philosopher.getRightChopstick().getHolder() != this
                    && philosopher.getLeftChopstick().getHolder() != this)) || someoneEating) {
                drop = false;
                break;
            }
            if (drop) {
                // System.out.println("\nPhilosopher " + philosopher.getPhilosopherName()
                // + "\'s ********** DEADLOCK ********** | TIME: "
                // + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1] + "\n");
                System.out.println(philosopher + " | " + "DEADLOCK" + " | "
                        + new Timestamp(System.currentTimeMillis()).toString().split(" ")[1]);

                Thread.sleep((long) (4000));
                for (Philosopher philosopherToDrop : getPhilosophers()) {
                    if (!philosopherToDrop.isEating) {
                        philosopherToDrop.dropChopsticks(1.0);
                    }
                }
            }
        }
    }

    /**
     * runs the thread
     */
    public void run() {
        while (!this.isFull) {
            try {
                think();
                pickupLeftChopstick();
                pickupRightChopstick();
                if (this.getThirdChopstick() != null) {
                    pickupThirdChopstick();
                }

                eat();

                Thread.sleep((long) (getDroppingProbability() * 1000));

                if (getImplementation().equals("DR")) {
                    checkDeadlock();
                } else {
                    dropChopsticks(Math.random());
                }

            } catch (InterruptedException e) {
                System.out.println(this.getPhilosopherName() + " is the current philosopher causing a problem");
                Thread.currentThread().interrupt();
            }
        }
    }
}
