package test;

import org.junit.jupiter.api.*;
import main.Board;

class BoardTest {

	@Test
	void testBoardSize() {
		Board testBoard = new Board();

		assert(testBoard.getBoard().length == 20);
	}

}
