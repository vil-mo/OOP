package ru.nsu.berezin.snake.tile;

/**
 * An entity that can be placed on a tile.
 */
public interface TileEntity {
    /**
     * Whether this entity is solid. Solid entities cannot be replaced by other entities.
     * 
     * @return True if this entity is solid.
     */
    boolean isSolid();

    /**
     * Called when this entity is either replaced by another entity or removed from the tile.
     * 
     * @param onX X coordinate of the tile where this entity is replaced.
     * @param onY Y coordinate of the tile where this entity is replaced.
     * @param otherEntity Entity that is replacing this entity. Null if this entity is being removed from the tile. Always null for solid entities.
     */
    void replaced(int onX, int onY, TileEntity otherEntity);
}
