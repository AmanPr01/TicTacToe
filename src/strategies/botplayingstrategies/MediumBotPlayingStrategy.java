package strategies.botplayingstrategies;

import models.Board;
import models.Cell;
import models.CellState;
import models.Move;
import strategies.winningstrategies.ColumnWinningStrategy;
import strategies.winningstrategies.DiagonalWinningStrategy;
import strategies.winningstrategies.RowWinningStrategy;
import strategies.winningstrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class MediumBotPlayingStrategy implements BotPlayingStrategy {

    List<WinningStrategy> winningStrategies = new ArrayList<>();

    @Override
    public Move makeMove(Board board) {
        winningStrategies.add(new ColumnWinningStrategy());
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());
        for (int i = 0; i < board.getDimension(); i++) {
            for (int j = 0; j < board.getDimension(); j++) {
                if (board.getBoard().get(i).get(j).getCellState().equals(CellState.FILLED)) {
                    continue;
                }
                Move move = new Move(null, board.getBoard().get(i).get(j));
                for (WinningStrategy winningStrategy : winningStrategies) {
                    if (winningStrategy.checkWinner(board, move)) {
                        return move;
                    }
                }
            }
        }

        for (List<Cell> cells : board.getBoard()) {
            for (Cell cell : cells) {
                if (cell.getCellState().equals(CellState.EMPTY)) {
                    return new Move(null, cell);
                }
            }
        }

        return null;
    }

//    public Move lastCell(Board board) {
//        for (int i = board.getDimension()-1; i >= 0; i--) {
//            for (int j = board.getDimension()-1; j >= 0; j--) {
//                if (board.getBoard().get(i).get(j).equals(CellState.EMPTY)) {
//                    return new Move(null, board.getBoard().get(i).get(j));
//                }
//            }
//        }
//
//        return null;
//    }
}
