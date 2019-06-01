package tasks;

import interfaces.Task;

public class QuitTask<T> implements Task<T> {

    public T run() {
        System.exit(0);
        return null;
    }
}
