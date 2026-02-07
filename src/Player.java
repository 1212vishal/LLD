public class Player {
    private String name;
    private int id;
    private int score=0;
    private Symbol symbol;

    public Player(String name, int id, Symbol symbol) {
        this.name = name;
        this.id = id;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setScore() {
        this.score++;
    }
}
