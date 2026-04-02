package ru.nsu.berezin.snake.tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.random.RandomGenerator;

/**
 * Tracks empty tiles in the map. Uses index map.
 */
public class EmptyTileTracker {
    private List<Tile> emptyTiles;
    private Map<Tile, Integer> indexMap;

    public EmptyTileTracker(int width, int height) {
        int capasity = width * height;
        emptyTiles = new ArrayList<>(capasity);
        indexMap = new HashMap<>(capasity);
    }

    /**
     * Update the state of the tracker. 
     * 
     * @param tile Tile that gets updated.
     */
    public void update(Tile tile) {
        if (tile.isEmpty()) {
            if (!indexMap.containsKey(tile)) {
                indexMap.put(tile, emptyTiles.size());
                emptyTiles.add(tile);
            }
        } else {
            if (indexMap.containsKey(tile)) {
                int index = indexMap.get(tile);
                Tile last = emptyTiles.removeLast();
                emptyTiles.set(index, last);
                indexMap.remove(tile);
            }
        }
    }

    /**
     * Get a random empty tile.
     * 
     * @param random Random generator to use.
     * @return A random empty tile.
     */
    public Tile getRandom(RandomGenerator random) {
        int index = random.nextInt(emptyTiles.size());
        return emptyTiles.get(index);
    }
}