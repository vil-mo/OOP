package ru.nsu.berezin.snake.tile;

import ru.nsu.berezin.snake.tile.Direction;
import ru.nsu.berezin.snake.tile.EmptyTileTracker;
import ru.nsu.berezin.snake.tile.Tile;
import ru.nsu.berezin.snake.tile.TileEntity;
import java.util.random.RandomGenerator;

/**
 * A map of all tiles in play.
 */
public class TileMap {
    private int width;
    private int height;
    private Tile[][] tiles;

    private EmptyTileTracker emptyTileTracker;

    /**
     * Create a new map.
     * 
     * @param width Width of the map.
     * @param height Height of the map.
     */
    public TileMap(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        emptyTileTracker = new EmptyTileTracker(width, height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Tile tile = new Tile(x, y, emptyTileTracker);
                tiles[x][y] = tile;
                emptyTileTracker.update(tiles[x][y]);
            }
        }

    }

    /**
     * Whether the given coordinates are within the bounds of the map.
     * Valid coordinates are in the range [0, width) and [0, height).
     * 
     * @param x X coordinate.
     * @param y Y coordinate.
     * @return True if the coordinates are within the bounds of the map.
     */
    public boolean withinBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    /**
     * Update the state of the tile at the given coordinates.
     * Either replace the underlying entity with a new one or remove the underling entity from the tile.
     * On attempt of replacing the solid underlying entity, the tile will not be updated and false will be returned.
     * 
     * @param x X coordinate of the tile.
     * @param y Y coordinate of the tile.
     * @param entity New entity to replace the old one with. Null to remove underling entity from the tile.
     * @return False if attempted to replace solid entity.
     */
    public boolean update(int x, int y, TileEntity entity) {
        if (!withinBounds(x, y)) {
            return false;
        }
        return tiles[x][y].update(entity);
    }

    /**
     * Get a random empty tile.
     * 
     * @param random Random generator to use.
     * @return A random empty tile.
     */
    public Tile getRandomEmptyTile(RandomGenerator random) {
        return emptyTileTracker.getRandom(random);
    }

    /**
     * Get the resulting tile of moving from the given coordinates in the given direction.
     * 
     * @param x X coordinate of the tile to move from.
     * @param y Y coordinate of the tile to move from.
     * @param direction Direction to move in.
     * @return The resulting tile of moving from the given coordinates in the given direction.
     */
    public Tile moveResult(int x, int y, Direction direction) {
        return new Pair<>(x + direction.getX(), y + direction.getY());
    }
}
