/**
 * The Frame of a ball.
 */
public class Frame {
    /**
     * The Height.
     */
    private int height;
    /**
     * The Width.
     */
    private int width;
    /**
     * The Start point.
     */
    private Point startPoint;

    /**
     * Instantiates a new Frame.
     *
     * @param height     the height
     * @param width      the width
     * @param startPoint the start point
     */
    public Frame(int height, int width, Point startPoint) {
        this.height = height;
        this.width = width;
        this.startPoint = startPoint;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Gets start point.
     *
     * @return the start point
     */
    public Point getStartPoint() {
        return this.startPoint;
    }

    /**
     * Sets height.
     *
     * @param height1 the height
     */
    public void setHeight(int height1) {
        this.height = height1;
    }

    /**
     * Sets start point.
     *
     * @param startPoint1 the start point
     */
    public void setStartPoint(Point startPoint1) {
        this.startPoint = startPoint1;
    }

    /**
     * Sets width.
     *
     * @param width1 the width
     */
    public void setWidth(int width1) {
        this.width = width1;
    }
}
