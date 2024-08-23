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
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidBotCountException, InvalidPlayerCountException, InvalidPlayerSymbolException, InvalidMoveException {
        Scanner scanner = new Scanner(System.in);
        boolean again = true;
        int dimension = 3;

        while (again) {
            // Initialize players for each game
            List<Player> players = new ArrayList<>();
            players.add(new Player("Aman", new Symbol('X'), PlayerType.HUMAN));
            players.add(new Bot("Chiku", new Symbol('O'), BotDifficultyLevel.EASY));

            // Initialize winning strategies for each game
            List<WinningStrategy> winningStrategies = List.of(
                    new RowWinningStrategy(),
                    new ColumnWinningStrategy(),
                    new DiagonalWinningStrategy()
            );

            // Initialize game controller and game for each new game
            GameController gameController = new GameController();
            Game game = gameController.startGame(
                    dimension,
                    players,
                    winningStrategies
            );

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
                again = false;
            }
        }
        scanner.close();
    }
}
