# TicTacToe

A scalable and modular implementation of the classic TicTacToe game, built using Java. This project supports multiple players, including AI-controlled bots with varying difficulty levels, and employs different winning strategies.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Usage](#usage)
- [How to Play](#how-to-play)
- [Future Enhancements](#future-enhancements)

## Introduction

TicTacToe is a simple yet popular game where players take turns marking a 3x3 grid with their symbols (X or O). The first player to align three of their symbols in a row, column, or diagonal wins the game. This project is an object-oriented implementation of the game, designed to be easily extendable and customizable.

## Features

- **Modular Design**: The game is designed using a modular approach, with each component encapsulated in its own package.
- **Bot Players**: Includes a basic bot with an easy playing strategy that makes moves at the first available cell.
- **Multiple Winning Strategies**: The game can determine winners based on rows, columns, or diagonals.
- **Replayability**: Players can choose to play multiple games in one session.
- **Custom Exceptions**: The project includes custom exception handling to manage invalid game states and moves.

## Project Structure

```bash
TicTacToe/
├── src/
│   ├── controllers/
│   │   └── GameController.java
│   ├── exceptions/
│   │   ├── InvalidBotCountException.java
│   │   ├── InvalidMoveException.java
│   │   ├── InvalidPlayerCountException.java
│   │   └── InvalidPlayerSymbolException.java
│   ├── models/
│   │   ├── Board.java
│   │   ├── Bot.java
│   │   ├── BotDifficultyLevel.java
│   │   ├── Cell.java
│   │   ├── CellState.java
│   │   ├── Game.java
│   │   ├── GameState.java
│   │   ├── Move.java
│   │   ├── Player.java
│   │   ├── PlayerType.java
│   │   └── Symbol.java
│   ├── strategies/
│   │   ├── botplayingstrategies/
│   │   │   ├── BotPlayingStrategy.java
│   │   │   ├── EasyBotPlayingStrategy.java
│   │   │   ├── HardBotPlayingStrategy.java
│   │   │   └── MediumBotPlayingStrategy.java
│   │   └── winningstrategies/
│   │       ├── ColumnWinningStrategy.java
│   │       ├── DiagonalWinningStrategy.java
│   │       ├── RowWinningStrategy.java
│   │       └── WinningStrategy.java
│   ├── Main.java
│   └── .gitignore
├── .idea/
├── out/
└── README.md
```

## Installation

To set up and run this project locally, follow these steps:

1. **Clone the repository**:

    ```bash
    git clone https://github.com/AmanPr01/TicTacToe.git
    cd TicTacToe
    ```

2. **Compile the source code**:

    ```bash
    javac -d bin -cp src src/Main.java
    ```

3. **Run the game**:

    ```bash
    java -cp bin Main
    ```

## Usage

### Running the Game

To start the game, run the `Main` class from your IDE or the command line. The game is played in the console, where players will take turns making moves on a 3x3 grid.

## How to Play

1. **Start the Game**:
    - The game begins with two players: Aman (human) and Chiku (bot).
    - The grid size is set to 3x3 by default.

2. **Make Moves**:
    - Players take turns entering their moves. The bot moves automatically based on its difficulty level.

3. **Win Conditions**:
    - The game will check for a winner after each move based on row, column, and diagonal winning strategies.

4. **End of Game**:
    - Once a player wins or the game ends in a draw, you will be prompted to play again.

5. **Replay**:
    - Enter "yes" to start a new game, or "no" to exit.

### Example Output

```plaintext
 X | O | O
-----------
   | X |  
-----------
   |   | X

Aman has WON the Game

Do you want to play again? (yes/no):
```

## Future Enhancements

- **Custom Board Size**: Allow players to set a custom board size (e.g., 4x4, 5x5).
- **Advanced Bot AI**: Implement more sophisticated AI strategies for the bot.
- **Undo/Redo Functionality**: Enable players to undo and redo moves.
- **Multiplayer Mode**: Support for multiplayer games over a network.
