package ru.job4j.chess.firuges.black;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.junit.jupiter.api.Assertions.*;

class BishopBlackTest {

    @Test
    void whenPositionThenReturnPosition() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        Cell expected = bishopBlack.position();
        assertThat(Cell.C1).isEqualTo(expected);
    }

    @Test
    void whenCopyThenNewBishopBlack() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        Figure newBishop = bishopBlack.copy(Cell.A2);
        Cell expected = newBishop.position();
        assertThat(expected).isEqualTo(Cell.A2);
    }

    @Test
    void whenWayThenCorrect() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        Cell[] actual = bishopBlack.way(Cell.G5);
        Cell[] expected = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        assertThat(expected).isEqualTo(actual);

    }

    @Test
    void whenWayIsNotDiagonal() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        ImpossibleMoveException exception = assertThrows(
                ImpossibleMoveException.class, () -> bishopBlack.way(Cell.C2));
        assertThat(exception.getMessage()).isEqualTo("Could not way by diagonal from C1 to C2");
    }
}