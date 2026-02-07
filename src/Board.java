
public class Board {
    private int size;
    private Symbol[][] board;

    public Board(int size){
        this.size=size;
        this.board=new Symbol[size][size];
    }

    public void markCell(Symbol symbol,int x,int y){
        board[x][y]=symbol;
    }

    public int getSize() {
        return size;
    }

    public void  printBoard(){
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                System.out.print(board[i][j]+ " ");
            }
            System.out.println();
        }
    }

   public boolean checkCell(int x,int y){
        return board[x][y]==null;
    }

    public Symbol returnell(int x,int y){
        return board[x][y];
    }




}
