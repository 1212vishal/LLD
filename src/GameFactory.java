public class GameFactory {
    public Game createGame(GameType type,int size){
        if(type==GameType.STANDARD){
            RuleStategy rule=new StandardRule();
            return new Game(size,rule);
        }
        return null;
    }
}
