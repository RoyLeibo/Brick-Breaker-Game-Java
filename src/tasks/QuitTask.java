package tasks;

import interfaces.Task;

/**
 * The type Quit task.
 *
 * @param <T> the type parameter
 * @author Roy Leibovitz <royleibo212@gmail.com> Roy Leibovitz
 */
public class QuitTask<T> implements Task<T> {

    /**
     * This function close the program.
     *
     * @return generic type T
     */
    public T run() {
        System.exit(0);
        return null;
    }
}
