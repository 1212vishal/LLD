//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class TICTACTOE {
    public static void main(String[] args) {
          GameFactory gameFactory=new GameFactory();
          Game newGame=gameFactory.createGame(GameType.STANDARD,3);

          Player player1=new Player("Vishal",1,Symbol.O);
          Player player2=new Player("Arshad",2,Symbol.X);

          newGame.addPlayers(player1);
          newGame.addPlayers(player2);

          Iobserver consoleObserver=new ConsoleObserver();
          newGame.addObserver(consoleObserver);


          newGame.startGame();

        }
    }