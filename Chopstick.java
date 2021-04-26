/**
 * CSDS 338: The Chopstick class to be used with the Simulator 2 project
 * Simulator 2: Dining Philosophers Problem
 * 
 * @author Josh Levy
 * @author Max Day
 * @author Steve Pham
 * @author Maggie Clark Fairfield
 */

public class Chopstick {
  /* if the Chopstick is used */
  private boolean used;
  /* the name of the Chopstick */
  private final int name;
  /* chopstick holder */
  private Philosopher holder;

  /**
   * @param name The name of the Chopstick
   */
  public Chopstick(int name) {
    used = false;
    this.name = name;
    setHolder(null);
  }

  /**
   * @return Whether or not the Chopstick is used
   */
  public boolean isUsed() {
    return used;
  }

  /**
   * @param used Sets whether or not the Chopstick is used
   */
  public void setUsed(boolean used) {
    this.used = used;
  }

  /**
   * @return The holder (A Philosopher) of the Chopstick
   */
  public Philosopher getHolder() {
    return holder;
  }

  /**
   * @param holder Sets the holder (A Philosopher) of the Chopstick
   */
  public void setHolder(Philosopher holder) {
    this.holder = holder;
  }

  /**
   * @return  The name of a Chopstick
   */
  public int getName() {
    return name;
  }

  /**
   * @param philosopher The Philosopher to pickup the Chopstick
   */
  public synchronized void pickup(Philosopher philosopher) {
    setUsed(true);
    setHolder(philosopher);
  }

  /**
   * Drops a Chopstick
   */
  public synchronized void drop() {
    setUsed(false);
    setHolder(null);
  }

  /**
   * @return A string representation of a Chopstick
   */
  @Override
  public String toString() {
    return "Chopstick " + name;
  }
}