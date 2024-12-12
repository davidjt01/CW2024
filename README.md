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

## Modified Java Classes:

## Unexpected Problems: