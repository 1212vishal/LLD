import java.util.*;

public class Game {

    private Board board;
    private Deque<Player> players;
    private List<Iobserver> observers;
    private RuleStategy ruleStategy;
    private boolean gameOver = false;

    public Game(int size, RuleStategy ruleStategy) {
        this.board = new Board(size);
        this.ruleStategy = ruleStategy;
        this.players = new ArrayDeque<>();
        this.observers = new ArrayList<>();
    }

    public void addPlayers(Player player) {

        players.addLast(player);
    }

    public void addObserver(Iobserver observer) {

        observers.add(observer);
    }

    public void notify(String msg){
        for(int i=0;i<observers.size();i++){
            observers.get(i).update(msg);
        }
    }

    public void startGame() {

        if(players.size()<2){
            System.out.println("Need atleast 2 player");

            return;
        }

        notify("Match has been started");

        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
           board.printBoard();
           Player turnPlayer=players.peekFirst();
           System.out.print(turnPlayer.getName() + " (" + turnPlayer.getSymbol() + ") - Enter row and column: ");

            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if(ruleStategy.isValidMove(row, col,board)) {
                board.markCell(turnPlayer.getSymbol(),row,col);
                notify(turnPlayer.getName() + " played (" + row + "," + col + ")");

                if(ruleStategy.checkWinner(board, turnPlayer.getSymbol())) {
                    board.printBoard();
                    System.out.println(turnPlayer.getName() + " wins!");
                    turnPlayer.setScore();

                    notify(turnPlayer.getName() + " wins!");

                    gameOver = true;
                }
                else if(ruleStategy.isMatchDraw(board)) {
                    board.printBoard();

                    System.out.println("It's a draw!");
                    notify("Game is Draw!");

                    gameOver = true;
                }
                else {
                    // Move player to back of queue
                    players.removeFirst();
                    players.addLast(turnPlayer);
                }
            }
            else {
                System.out.println("Invalid move! Try again.");
            }
        }

        }
    }

