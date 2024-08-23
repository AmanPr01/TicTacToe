//import controllers.GameController;
//import exceptions.InvalidBotCountException;
//import exceptions.InvalidMoveException;
//import exceptions.InvalidPlayerCountException;
//import exceptions.InvalidPlayerSymbolException;
//import models.*;
//import strategies.winningstrategies.ColumnWinningStrategy;
//import strategies.winningstrategies.DiagonalWinningStrategy;
//import strategies.winningstrategies.RowWinningStrategy;
//import strategies.winningstrategies.WinningStrategy;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;

//public class Main {
//    public static void main(String[] args) throws InvalidBotCountException, InvalidPlayerCountException, InvalidPlayerSymbolException, InvalidMoveException {
//        Scanner scanner = new Scanner(System.in);
//        boolean again = true;
//        int dimension = 3;
//
//        while (again) {
//            // Initialize players for each game
//            List<Player> players = new ArrayList<>();
//            players.add(new Player("Aman", new Symbol('X'), PlayerType.HUMAN));
//            players.add(new Bot("Chiku", new Symbol('O'), BotDifficultyLevel.EASY));
//
//            // Initialize winning strategies for each game
//            List<WinningStrategy> winningStrategies = List.of(
//                    new RowWinningStrategy(),
//                    new ColumnWinningStrategy(),
//                    new DiagonalWinningStrategy()
//            );
//
//            // Initialize game controller and game for each new game
//            GameController gameController = new GameController();
//            Game game = gameController.startGame(
//                    dimension,
//                    players,
//                    winningStrategies
//            );
//
//            while (game.getGameState().equals(GameState.IN_PROGRESS)) {
//                gameController.printBoard(game);
//                gameController.makeMove(game);
//            }
//
//            gameController.printBoard(game);
//            if (game.getGameState().equals(GameState.ENDED)) {
//                System.out.println(gameController.getWinner(game).getName() + " has WON the Game");
//            } else {
//                System.out.println("Game Draw");
//            }
//
//            System.out.print("Do you want to play again? (yes/no): ");
//            String response = scanner.nextLine().trim().toLowerCase();
//            if (!response.equals("yes")) {
//                again = false;
//            }
//        }
//        scanner.close();
//    }
//}

import controllers.GameController;
import exceptions.InvalidBotCountException;
import exceptions.InvalidMoveException;
import exceptions.InvalidPlayerCountException;
import exceptions.InvalidPlayerSymbolException;
import models.*;
import strategies.winningstrategies.ColumnWinningStrategy;
import strategies.winningstrategies.DiagonalWinningStrategy;
import strategies.winningstrategies.RowWinningStrategy;
import strategies.winningstrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final char STARTING_SYMBOL = 'A';

    public static void main(String[] args) throws InvalidBotCountException, InvalidPlayerCountException, InvalidPlayerSymbolException, InvalidMoveException {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        List<Player> previousPlayers = null;

        while (playAgain) {
            List<Player> players;
            int dimension;

            if (previousPlayers == null) {
                // Get number of players and dimension
                System.out.print("Enter the number of players (2-26): ");
                int numPlayers = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                if (numPlayers < 2 || numPlayers > 26) {
                    System.out.println("Number of players must be between 2 and 26.");
                    continue;
                }

                dimension = numPlayers + 1;

                players = new ArrayList<>();
                HashSet<Character> usedSymbols = new HashSet<>();

                // Get human players and their details
                System.out.print("Enter number of human players: ");
                int numHumanPlayers = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                if (numHumanPlayers >= numPlayers) {
                    System.out.println("Number of human players must be less than total number of players.");
                    continue;
                }

                for (int i = 0; i < numHumanPlayers; i++) {
                    System.out.print("Enter name for human player " + (i + 1) + ": ");
                    String name = scanner.nextLine();

                    char symbol;
                    while (true) {
                        System.out.print("Enter symbol for " + name + " (single capital letter): ");
                        symbol = scanner.nextLine().toUpperCase().charAt(0);

                        if (symbol < 'A' || symbol > 'Z') {
                            System.out.println("Symbol must be a capital letter.");
                        } else if (usedSymbols.contains(symbol)) {
                            System.out.println("Symbol already used. Choose another.");
                        } else {
                            usedSymbols.add(symbol);
                            break;
                        }
                    }

                    players.add(new Player(name, new Symbol(symbol), PlayerType.HUMAN));
                }

                // Get bot players and their details
                int numBots = numPlayers - numHumanPlayers;
                System.out.println("Enter details for bots:");
                for (int i = 0; i < numBots; i++) {
                    char botSymbol;
                    while (true) {
                        System.out.print("Enter symbol for Bot " + (i + 1) + " (single capital letter): ");
                        botSymbol = scanner.nextLine().toUpperCase().charAt(0);

                        if (botSymbol < 'A' || botSymbol > 'Z') {
                            System.out.println("Symbol must be a capital letter.");
                        } else if (usedSymbols.contains(botSymbol)) {
                            System.out.println("Symbol already used. Choose another.");
                        } else {
                            usedSymbols.add(botSymbol);
                            break;
                        }
                    }

                    players.add(new Bot("Bot" + (i + 1), new Symbol(botSymbol), BotDifficultyLevel.EASY));
                }

                // Display bot symbols assigned
                System.out.println("Bot Symbols Assigned:");
                for (Player player : players) {
                    if (player instanceof Bot) {
                        System.out.println(player.getName() + " -> " + player.getSymbol().getaChar());
                    }
                }
                System.out.println("Starting the game...");

                previousPlayers = players;
            } else {
                players = previousPlayers;
                dimension = players.size() + 1;
            }

            // Initialize winning strategies
            List<WinningStrategy> winningStrategies = List.of(
                    new RowWinningStrategy(),
                    new ColumnWinningStrategy(),
                    new DiagonalWinningStrategy()
            );

            // Initialize game controller and game
            GameController gameController = new GameController();
            Game game = gameController.startGame(dimension, players, winningStrategies);

            while (game.getGameState().equals(GameState.IN_PROGRESS)) {
                gameController.printBoard(game);
                gameController.makeMove(game);
            }

            gameController.printBoard(game);
            if (game.getGameState().equals(GameState.ENDED)) {
                System.out.println(gameController.getWinner(game).getName() + " has WON the Game");
            } else {
                System.out.println("Game Draw");
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("yes")) {
                playAgain = false;
                previousPlayers = null;  // Reset for next run
            }
        }
        scanner.close();
    }
}

