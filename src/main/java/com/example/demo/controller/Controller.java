package com.example.demo.controller;

public interface Controller {
    void onLevelSelected(String level);

    void onMainMenuSelected();

    void onLevelMenuSelected();

    void onPauseSelected();

    void onContinueSelected();

    void onSettingsMenuSelected();
}
