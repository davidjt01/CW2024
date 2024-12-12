# CW2024 Sky Battle

## Github Repository Link:
https://github.com/davidjt01/CW2024

## Compilation Instructions:

## Features Implemented And Working Properly:

### Added sound effects
- Added sound effects in game.
  - Explosion sound plays when the player's plane or an enemy plane is destroyed
  - Shield activation and deactivation sound plays when boss's shield is activated or deactivated
  - Different projectile firing sounds for different planes
    - A "pew" sound for the player's plane
    - Missile sound for enemy planes that fires missiles
    - Fireball sound for boss's that fires fireballs
  - "Ouch" sound plays when player takes damage

### Audio volume control
- Added volume control in settings menu where players can adjust the audio volume from 0 to 100 percent.
  - '-' button decreases volume by 10 percent while '+' button increases volume by 10 percent.

### Pause functionality
- When 'P' or 'esc' button is pressed, the game will pause and the Pause Menu will be shown where the paused scene of the game is set as the background.

### Horizontal movement for player's plane
- Player's plane can now move horizontally as well as vertically using arrow keys up, down, left, right.
- Player's movement will be limited to be within bounds of the level.

### Player movement additional controls
- Player can now also move up, down, left, and right using WASD instead.

### Limited firing rate of Player's plane
- Player can now only fire after a firing cooldown so when space bar is held, player will still fire at a reasonable rate.

### Improved enemy spawning
- Introduced a minimum spawn delay between enemy spawning to prevent enemies from spawning all at once.

### Improved performance
- Projectiles that are out of bounds are removed to not waste resources tracking them.

### Accurate hit boxes
-  Images of all planes and projectiles were cropped and their in game sizes were adjusted to appear as before for accureate collision handling.

### Menus

#### Main Menu
- Shown at game launch.
- Provides options to start the game (got to Level Menu), access settings or quit the application.

#### Level Menu
- Includes buttons to select different game levels from one to five and a back button to return to previous menu.

#### Settings Menu
- Current volume percentage is displayed and '-' and '+' buttons are used to decrease or increase volume by ten percent.
- Back button returns to previous menu.

#### Pause Menu
- Displays options to continue the game, go to level menu or return to main menu.
- A snapshot of the currently paused game scene is captured and used the background for Pause Menu

#### Game Over Menu
- Shown when player loses the game.
- Game Over image is displayed.
- Includes buttons to retry the current level the player lost at, go to level menu or return to main menu.

#### Win Menu
- Shown when the play wins the game.
- You Win image is displayed.
- Includes buttons to go to level menu or return to main menu.

### Explosion effect when planes are destroyed
- When a plane is destroyed, an explosion image is shown for one second at the position where the plane was destoroyed.
- An explosion sound will also be played.

### Health Bar for bosses
- Added a health bar which indicates a boss's health for all bosses in the game.
- The health bar position follows the boss's position off set by a specific amount depending on the boss.

### Shield for bosses
- A Shield image is displayed on top of the bosses when they are shielded.
- Shield position follows the boss's position off set by a specific amount depending on the boss.

### New Enemy Planes
- Advanced Enemy Planes are similar to Enemy Planes where it moves horizontally at a fixed velocity except Advanced Enemy Planes are faster and will randomly move up and down within the level bounds.
- Second Boss Plane has a similar shielding mechanic to the original boss but fires projectiles that randomly moves up and down, changing directions only after a certain time.
- Final Boss Plane also has a shielding mechanic but fires three projectiles at once, one projectile angled 20 degree upwards, another angled 20 degrees downwards and the last one travelling straight ahead.

### Levels
- Level one and two remains the same.
- Level three has no bosses and alternates between spawning Enemy Planes and Advanced Enemy Planes where the player will have to kil 20 enemies to advance.
- Level four is a boss fight with Second Boss Plane together with Enemy Planes periodically spawning where the player will have to kill the boss to advance.
- Level five is the final level where the player will have to fight Final Boss Plane while Enemy Planes and Advanced Enemy Planes endlessly spawn where the player will have to kill the boss to win the game.

### Renaming of classes
- Classes were renamed for clarity standardisation.
- For example, classes Boss was renamed BossPlane while ShieldImage was renamed ShieldDisplay.

### Package organisation
- Classes were grouped and moved into new packages for organisation.

### Factory method for
- AudioPlayer
- GameController
- HealthBarDisplay
- HeartDisplay
- ShieldDisplay
- LevelView
- BossLevelView

## Features Implemented But Not Working Properly:

## Features Not Implemented:

## New Java Classes:

### AudioPlayer
The AudioPlayer class provides a streamlined way to manage and play audio clips within the application. It includes features such as global volume control and seamless audio clip playback. Below are the key functionalities of the AudioPlayer class:

#### Global Volume Control:
Allows setting and retrieving a global volume value applicable to all instances of the AudioPlayer.
Volume levels range from 0.0 (mute) to 1.0 (maximum volume).
#### Audio Management:
Provides a static factory method createAudioPlayer() to instantiate new AudioPlayer objects.
Facilitates loading audio files using the loadAudio(String filePath) method, which accepts the file path of the audio resource.
#### Playback:
Supports playing the loaded audio clip with the play() method.
Ensures that only properly loaded audio clips are playable.
#### Usage Example:
```
// Set global volume
AudioPlayer.setGlobalVolume(0.5);

// Create an AudioPlayer instance
AudioPlayer audioPlayer = AudioPlayer.createAudioPlayer();

// Load and play an audio file
audioPlayer.loadAudio("/path/to/audio/file.wav");
audioPlayer.play();
```
This class is built on top of JavaFX's AudioClip and ensures robust audio playback suitable for various interactive applications.

### Controller
The Controller interface defines the core methods required for handling user interactions within the game. It provides a structured way to manage navigation between levels, menus, and settings.

#### Key Functionalities:
- Level Selection:
  - Provides the onLevelSelected(String level) method to handle events when a specific game level is selected.
- Menu Navigation:
  - Includes methods for navigating between the main menu, level menu, and settings:
    - onMainMenuSelected(): Handles navigation to the main menu.
    - onLevelMenuSelected(): Handles navigation to the level selection menu.
    - onSettingsMenuSelected(): Handles navigation to the settings menu.
  - Gameplay Control:
    - Offers the onContinueSelected() method to resume gameplay, allowing seamless transitions back to the game after interruptions.

This interface ensures a consistent approach to managing user interactions and gameplay navigation in the application.

### ExplosionDisplay
The ExplosionDisplay class creates a visual effect for explosions within the game. It extends JavaFX's ImageView to display an explosion image at a specified position and automatically removes the effect after a brief duration.

#### Key Functionalities:
- Explosion Effect:
  - Displays an explosion image at a specific location in the game scene.
  - Automatically hides the explosion after one second using a Timeline.
- Positioning and Sizing:
  - Allows precise placement of the explosion via the constructor parameters (xPosition and yPosition).
  - Sets the explosion image height to a fixed size while preserving its aspect ratio.
- Usage Example:
```
// Creating an ExplosionDisplay at specific coordinates
ExplosionDisplay explosion = new ExplosionDisplay(100, 200);

// Adding the explosion to the scene
root.getChildren().add(explosion);
```
- Features:
  - Pre-configured explosion image located at /com/example/demo/images/explosion.png.
  - Automatically hides itself after one second to keep the scene clean and optimize performance.

This class provides a simple yet effective way to add dynamic visual effects for explosions in the game, enhancing user experience.


### HealthBarDisplay
The HealthBarDisplay class provides a visual representation of an entity's health in the game. It uses a JavaFX ProgressBar to display the current health status and allows customization of its position.

#### Key Functionalities:
- Health Bar Visualization:
  - Displays health status as a progress bar, with a red accent style by default.
  - Initializes with full health (1.0).
- Positioning:
  - Can be placed at any specified coordinates on the game scene using the constructor parameters (xPosition and yPosition).
- Health Updates:
  - Provides the updateHealth(double healthPercentage) method to dynamically reflect changes in health status. The healthPercentage value should range between 0.0 (no health) and 1.0 (full health).
- Usage Example:
```
// Create a HealthBarDisplay at position (50, 50)
HealthBarDisplay healthBar = HealthBarDisplay.createHealthBarDisplay(50, 50);

// Add the health bar to the scene
root.getChildren().add(healthBar.getContainer());

// Update health dynamically
healthBar.updateHealth(0.75); // Set health to 75%
```
#### Features:
- Encapsulated in an HBox container, allowing easy integration into complex layouts.
- Customizable dimensions and visual styling.
- Provides a method getProgress() to retrieve the current health percentage. 

This class is a versatile and reusable component for displaying health status in interactive games. It ensures smooth updates and a visually intuitive way to monitor entity health.

### LevelThree
The LevelThree class represents the third level of the game. It extends the LevelParent class and introduces a mix of basic and advanced enemy planes. This level requires players to achieve a set number of kills to advance to the next level while maintaining their health.

#### Key Features:
- Background and Configuration:
  - Uses a custom background image: /com/example/demo/images/background1.jpg.
  - Requires the player to achieve 20 kills (KILLS_TO_ADVANCE) to advance to Level Four.
  - Spawns up to 5 enemies (TOTAL_ENEMIES) at a time with a 20% (ENEMY_SPAWN_PROBABILITY) chance of spawning on each attempt.
- Enemy Variants:
  - Alternates between spawning basic (EnemyPlane) and advanced enemy planes (AdvancedEnemyPlane) based on an internal counter.
- Player Initialization:
  - Places the player's plane in the scene during level setup.
- Game Over Conditions:
  - Ends the game if the player's plane is destroyed.
  - Transitions to the next level upon reaching the required number of kills.
#### Key Methods:
- checkIfGameOver():
  - Ends the game if the player's plane is destroyed (loseGame()). 
  - Advances to Level Four if the player has achieved the kill target.
- initializeFriendlyUnits():
  - Adds the player's plane to the level.
- spawnEnemyUnits():
  - Dynamically spawns a mix of basic and advanced enemies based on the spawn probability. 
  - Ensures enemies are spawned only if the current count is below the maximum allowed.
- instantiateLevelView():
  - Configures the visual components of the level.
- userHasReachedKillTarget():
  - Verifies if the player has reached the required kill count to progress.
Usage Example:
```
// Creating an instance of LevelThree
LevelThree levelThree = new LevelThree(gameController, 750, 1300);

// Initializing the scene
Scene levelScene = levelThree.initializeScene();
stage.setScene(levelScene);

// Starting the level
levelThree.startGame();
```
This class integrates dynamic enemy spawning, customizable game mechanics, and structured player progression to deliver an engaging gameplay experience. It serves as a bridge to increasingly challenging levels while balancing enemy variety and gameplay objectives.

### LevelFour
The LevelFour class is a pivotal stage in the game, introducing players to a challenging boss fight with the SecondBossPlane. It extends the LevelParent class and incorporates strategic gameplay mechanics, including dynamic enemy spawning and visually enhanced level components.

#### Key Features:
- Background and Level Setup:
  - Features a unique background image: /com/example/demo/images/background2.jpg.
  - Utilizes the BossLevelView for enhanced visual elements, including boss health and shields. 
  - Includes a major boss battle with the SecondBossPlane and additional regular enemies (EnemyPlane).
- Player and Enemy Mechanics:
  - Starts with a player health of 5 (PLAYER_INITIAL_HEALTH). 
  - Spawns up to 5 enemies (TOTAL_ENEMIES) at a time, with a 20% (ENEMY_SPAWN_PROBABILITY) chance of spawning during each cycle. 
  - Ensures the SecondBossPlane is always active until defeated.
- Progression:
  - Advances to Level Five (NEXT_LEVEL) upon defeating the boss. 
  - Ends the game if the player's plane is destroyed.
#### Key Methods:
- initializeFriendlyUnits():
  - Adds the player's plane to the scene.
- checkIfGameOver():
  - Monitors game state:
    - Calls loseGame() if the player's plane is destroyed. 
    - Calls goToNextLevel() to transition to Level Five when the boss is defeated.
- spawnEnemyUnits():
  - Dynamically manages enemy population:
    - Spawns the SecondBossPlane if no enemies are present.
    - Adds additional EnemyPlane instances based on a probability threshold.
- instantiateLevelView():
  - Configures and initializes the specialized BossLevelView for this stage.
Usage Example:
```
// Create an instance of LevelFour
LevelFour levelFour = new LevelFour(gameController, 750, 1300);

// Initialize the level's scene
Scene levelScene = levelFour.initializeScene();
stage.setScene(levelScene);

// Start the level
levelFour.startGame();
```
#### Features:
- Boss Mechanics:
  - Centralized around the SecondBossPlane, offering a high-stakes battle with advanced behaviors.
- Dynamic Spawning:
  - Balances regular enemy planes with the boss for varied gameplay.
- Visual Enhancements:
  - Utilizes the BossLevelView for displaying boss-specific elements like health bars and shields.

The LevelFour class provides an intense and rewarding gameplay experience, combining strategic challenges and visually immersive boss mechanics. It serves as the final preparation before progressing to the climactic final level.

### LevelFive
The LevelFive class represents the climactic final level of the game, featuring a challenging battle with the FinalBossPlane. It extends the LevelParent class and integrates a mix of dynamic enemy spawning and high-stakes gameplay.

#### Key Features:
- Background and Setup:
  - Features the final stage background: /com/example/demo/images/background2.jpg. 
  - Starts the player with 5 health points (PLAYER_INITIAL_HEALTH). 
  - Combines the FinalBossPlane with additional enemies (EnemyPlane and AdvancedEnemyPlane) to create a dynamic and intense gameplay experience.
- Enemy Mechanics:
  - Centralizes gameplay around the FinalBossPlane as the primary challenge. 
  - Spawns up to 5 enemies (TOTAL_ENEMIES) at a time, alternating between basic and advanced enemy types. 
- Game Completion:
  - Ends the game with a victory (winGame()) upon defeating the FinalBossPlane.
  - Ends the game with a loss (loseGame()) if the player's plane is destroyed.

#### Key Methods:
- initializeFriendlyUnits():
  - Adds the player's plane to the level's scene.
- checkIfGameOver():
  - Determines the game's state:
    - Calls winGame() when the FinalBossPlane is defeated. 
    - Calls loseGame() if the player's plane is destroyed.
- spawnEnemyUnits():
  - Ensures the FinalBossPlane is always present until destroyed.
  - Dynamically spawns additional enemies using a 20% probability (ENEMY_SPAWN_PROBABILITY) and alternates between EnemyPlane and AdvancedEnemyPlane.
- instantiateLevelView():
  - Configures the enhanced BossLevelView for this level, providing specialized visuals for the boss battle.
Usage Example:
```
// Create an instance of LevelFive
LevelFive levelFive = new LevelFive(gameController, 750, 1300);

// Initialize the level's scene
Scene levelScene = levelFive.initializeScene();
stage.setScene(levelScene);

// Start the final level
levelFive.startGame();
```
#### Features:
- Final Boss Challenge:
  - Centers around defeating the FinalBossPlane in a high-intensity battle.
- Dynamic Gameplay:
  - Alternates between different enemy types to maintain engagement and difficulty.
- Enhanced Visuals:
  - Uses the BossLevelView to display health and shield indicators for the boss.

The LevelFive class provides a fitting conclusion to the game, combining strategic challenges, intense gameplay, and immersive visuals to deliver a rewarding final experience.




## Modified Java Classes:

## Unexpected Problems: