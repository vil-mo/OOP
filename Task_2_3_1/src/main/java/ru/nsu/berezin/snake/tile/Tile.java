package ru.nsu.berezin.snake.tile;

import ru.nsu.berezin.snake.tile.EmptyTileTracker;
import ru.nsu.berezin.snake.tile.TileEntity;

/**
 * A tile on the map. Can contain an entity.
 */
public class Tile {
    private TileEntity entity = null;
    private int posX;
    private int posY;
    private EmptyTileTracker emptyTileTracker;

    /**
     * Create a new tile.
     * 
     * @param posX X coordinate of the tile.
     * @param posY Y coordinate of the tile.
     */
    public Tile(int posX, int posY, EmptyTileTracker emptyTileTracker) {
        this.posX = posX;
        this.posY = posY;
        this.emptyTileTracker = emptyTileTracker;
    }

    /**
     * Get the X coordinate of the tile.
     * 
     * @return X coordinate of the tile.
     */
    public int getX() {
        return posX;
    }

    /**
     * Get the Y coordinate of the tile.
     * 
     * @return Y coordinate of the tile.
     */
    public int getY() {
        return posY;
    }

    /**
     * Wether the underlying entity is solid. False if there is no entity.
     * 
     * @return True if the underlying entity is solid.
     */
    public boolean isSolid() {
        if (entity == null) {
            return false;
        }
        return entity.isSolid();
    }

    /**
     * Whether the tile is empty (does not contain an entity).
     * 
     * @return True if the tile is empty.
     */
    public boolean isEmpty() {
        return entity == null;
    }

    /**
     * Update the state of the tile.
     * Either replace the underlying entity with a new one or remove the underling entity from the tile.
     * On attempt of replacing the solid underlying entity, the tile will not be updated and false will be returned.
     * 
     * @param entity New entity to replace the old one with. Null to remove underling entity from the tile.
     * @return False if attempted to replace solid entity.
     */
    public boolean update(TileEntity entity) {
        if (this.entity != null) {
            if (this.entity.isSolid()) {
                return false;
            }
            this.entity.replaced(posX, posY, entity);
        }
        this.entity = entity;
        emptyTileTracker.update(this);
        return true;
    }
}
