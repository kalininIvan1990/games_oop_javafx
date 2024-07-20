package ru.job4j.chess;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.black.RookBlack;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.job4j.chess.firuges.Cell.*;

public class LogicTest {

    @Test
    public void whenMoveThenFigureNotFoundException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        FigureNotFoundException figureNotFoundException =
                assertThrows(FigureNotFoundException.class, () -> {
            logic.move(Cell.C1, Cell.H6);
        });
        BishopBlack bishopBlack = new BishopBlack(Cell.F8);
        Figure rook = new RookBlack(Cell.E7);
        logic.add(bishopBlack);
        logic.add(rook);
        OccupiedCellException occupiedCellException =
                 assertThrows(OccupiedCellException.class, () -> {
            logic.move(Cell.F8, Cell.E7);
        });
        assertThat(occupiedCellException.getMessage()).
                isEqualTo("Cell E7 is occupied");
        ImpossibleMoveException impossibleMoveException =
                assertThrows(ImpossibleMoveException.class, () -> {
            logic.move(Cell.F8, Cell.D8);
        });
        assertThat(impossibleMoveException.getMessage()).
                isEqualTo("Could not way by diagonal from F8 to D8");
    }

    @Test
    void whenMoveThenCellSteps() throws ImpossibleMoveException {
        Figure figure = new BishopBlack(Cell.C1);
        Cell[] steps = figure.way(Cell.F4);
        Cell[] expected = {Cell.D2, Cell.E3, Cell.F4};
        assertThat(expected).isEqualTo(steps);
    }

}