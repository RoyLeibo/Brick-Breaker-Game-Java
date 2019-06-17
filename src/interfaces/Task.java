package interfaces;

/**
 * The interface Task.
 *
 * @param <T> the type parameter
 * @author Roy Leibovitz <royleibo212@gmail.com>
 */
public interface Task<T> {
    /**
     * Run t.
     *
     * @return the t
     */
    T run();
}
