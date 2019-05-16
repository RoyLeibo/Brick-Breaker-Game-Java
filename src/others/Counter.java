package others;
/**
 * The type Counter.
 *
 * @author Roy Leibovitz
 */
public class Counter {
    private int counter;

    /**
     * Instantiates a new Counter.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * Increase.
     *
     * @param number the number
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * Decrease.
     *
     * @param number the number
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return counter;
    }

    /**
     * Sets counter.
     *
     * @param value the value
     */
    public void setCounter(int value) {
        this.counter = value;
    }
}