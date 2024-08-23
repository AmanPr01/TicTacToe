package strategies.winningstrategies;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy {

    private Map<Integer, Map<Symbol, Integer>> colCounts = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if (!colCounts.containsKey(col)) {
            colCounts.put(col, new HashMap<>());
        }

        Map<Symbol, Integer> colMap = colCounts.get(col);

        if (!colMap.containsKey(symbol)) {
            colMap.put(symbol, 0);
        }

        colMap.put(symbol, colMap.get(symbol) + 1);

        return colMap.get(symbol).equals(board.getDimension());
    }
}
