package others;

/**
 * The type Menu item.
 *
 * @param <T> the type parameter
 * @author Roy Leibovitz <royleibo212@gmail.com> Roy Leibovitz
 */
public class MenuItem<T> {
    private String key;
    private String message;
    private T returnValue;

    /**
     * Instantiates a new Menu item.
     *
     * @param key       the key
     * @param message   the message
     * @param returnVal the return val
     */
    public MenuItem(String key, String message, T returnVal) {
        this.key = key;
        this.message = message;
        this.returnValue = returnVal;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets return value.
     *
     * @return the return value
     */
    public T getReturnValue() {
        return returnValue;
    }
}