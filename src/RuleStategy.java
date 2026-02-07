public interface RuleStategy {
    public boolean isValidMove(int x,int y, Board board);
    public boolean isMatchDraw(Board board);
    public boolean checkWinner(Board board, Symbol symbol);
}
