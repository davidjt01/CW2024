package com.example.demo.controller;

/**
 *
 */
public interface Controller {
    /**
     * @param level
     */
    void onLevelSelected(String level);

    /**
     *
     */
    void onMainMenuSelected();

    /**
     *
     */
    void onLevelMenuSelected();

    /**
     *
     */
    void onContinueSelected();

    /**
     *
     */
    void onSettingsMenuSelected();
}
