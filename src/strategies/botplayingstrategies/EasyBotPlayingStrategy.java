package strategies.botplayingstrategies;

import models.Board;
import models.Cell;
import models.CellState;
import models.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements  BotPlayingStrategy {

    @Override
    public Move makeMove(Board board) {
        // Iterate through the board and make the move at the first available cell
        for (List<Cell> cells : board.getBoard()) {
            for (Cell cell : cells) {
                if (cell.getCellState().equals(CellState.EMPTY)) {
                    return new Move(null, cell);
                }
            }
        }

        return null;
    }
}
