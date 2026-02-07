public class StandardRule implements RuleStategy{

    @Override
    public boolean isValidMove(int x, int y, Board board) {
        int size=board.getSize();

        if(x<0||y<0||x>=size||y>=size)
            return false;
        return board.checkCell(x,y);
    }

    @Override
    public boolean isMatchDraw(Board board) {
        int size = board.getSize();
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(board.returnell(i, j) == null) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean checkWinner(Board board,Symbol symbol) {
        int size = board.getSize();

        // Check rows
        for(int i = 0; i < size; i++) {
            boolean win = true;
            for(int j = 0; j < size; j++) {
                if(board.returnell(i, j) != symbol) {
                    win = false;
                    break;
                }
            }
            if(win) return true;
        }

        // Check columns
        for(int j = 0; j < size; j++) {
            boolean win = true;
            for(int i = 0; i < size; i++) {
                if(board.returnell(i, j) != symbol) {
                    win = false;
                    break;
                }
            }
            if(win) return true;
        }

        // Check main diagonal
        boolean win = true;
        for(int i = 0; i < size; i++) {
            if(board.returnell(i, i) != symbol) {
                win = false;
                break;
            }
        }
        if(win) return true;

        // Check anti-diagonal
        win = true;
        for(int i = 0; i < size; i++) {
            if(board.returnell(i, size-1-i) != symbol) {
                win = false;
                break;
            }
        }
        return win;
    }
}
