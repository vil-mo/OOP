package ru.nsu.berezin.snake.tile;

/**
 * Direction of movement on tiled map.
 */
public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    /**
     * Get change in X coordinate that corresponds to this direction.
     * @return X coordinate change.
     */
    public int getX() {
        return switch (this) {
            case UP -> 0;
            case DOWN -> 0;
            case LEFT -> -1;
            case RIGHT -> 1;
        };
    }
    
    /**
     * Get change in Y coordinate that corresponds to this direction.
     * @return Y coordinate change.
     */
    public int getY() {
        return switch (this) {
            case UP -> -1;
            case DOWN -> 1;
            case LEFT -> 0;
            case RIGHT -> 0;
        };
    }
}
