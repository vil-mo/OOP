package ru.nsu.berezin.pizzeria.menu;

import java.time.Duration;

/**
 * Record class for menu items.
 *
 * @param name - name of the menu item
 * @param price - price of the menu item
 * @param averageBakingTime - how long item is baked for on average
 */
public record MenuItem(String name, double price, Duration averageBakingTime) {
}
