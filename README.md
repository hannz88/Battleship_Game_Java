# Battleship Game Java
[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/contains-cat-gifs.svg)](https://forthebadge.com)

## Introduction
This is a battleship game I wrote using Java while learning OOP. And here's a cat gif on ship (well... paperboat), because I promised there'll be cat gif(s). 

<p align="center">
    <img src="https://github.com/hannz88/Battleship_Game_Java/blob/main/Images/cat.gif" alt="Gif of cat on paperboat">
</p>

## Where
You can play it [here](https://repl.it/@hannz88/BattleshipGameJava?v=1), which is a link to the replit site where I uploaded my code or you could download the src file and compile it.

## How to play
### 1. Read the instructions
The program will then give you the instructions. Press y to advance through them or key in ex to skip them.

<p align="center">
    <img src="https://github.com/hannz88/Battleship_Game_Java/blob/main/Images/instructions.png" alt="Program giving instructions to player">
</p>

### 2. Set your character
The program will ask you to set your character representing you.

<p align="center">
    <img src="https://github.com/hannz88/Battleship_Game_Java/blob/main/Images/Ask_player_for_symbol.png" alt="Program asks player for representing symbol">
</p>


### 3. Set the ships
You'll be given the choise to set the positions for the ships of the size 2,2,3,4, and 5 or let the ships be set randomly. If you chose to manually set the ships, all you have to do is put in the row and column coordinate of the head of the ship. Then, you'll be asked to decide the orientation of the ship: v for vertical, h for horizontal. Make sure that the ship won't fall outside of the board. The program will warn you if it does.

<p align="center">
    <img src="https://github.com/hannz88/Battleship_Game_Java/blob/main/Images/Ask_player_set_ships.png" alt="Program asks if player wants to set ships manually or randomly">
</p>

### 4. Fire away!
Then, take turns and guess where the ships for the machine (the opponent) are! The winner is the first to get all ships!

## Versions
MKI implements a simple guessing algorithm where the machine will just randomly guess the player's ships' positions.
MKII will implement a local search for the machine.

