import org.junit.Test;

public class test {

	@Test
	public void testBoardSize() {
		Board board = new Board();
		int[][] boardBlocks = board.getBoard();
		assert(boardBlocks.length == 20);
	}

}
