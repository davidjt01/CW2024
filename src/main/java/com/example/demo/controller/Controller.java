package com.example.demo.controller;

/**
 * The {@code Controller} interface defines the methods required for handling
 * various user interactions within the game, such as selecting levels, navigating
 * menus, and adjusting settings.
 */
public interface Controller {
    /**
     * Handles the event when a level is selected.
     *
     * @param level the name of the selected level.
     */
    void onLevelSelected(String level);

    /**
     * Handles the event when the main menu is selected.
     */
    void onMainMenuSelected();

    /**
     * Handles the event when the level menu is selected.
     */
    void onLevelMenuSelected();

    /**
     * Handles the event when the continue option is selected.
     */
    void onContinueSelected();

    /**
     * Handles the event when the settings menu is selected.
     */
    void onSettingsMenuSelected();
}
