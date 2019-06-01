package others;

public class MenuItem<T> {
    private String key;
    private String message;
    private T returnValue;

    public MenuItem(String key, String message, T returnVal) {
        this.key = key;
        this.message = message;
        this.returnValue = returnVal;
    }

    public String getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }

    public T getReturnValue() {
        return returnValue;
    }
}
