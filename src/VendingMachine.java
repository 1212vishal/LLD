import VendingState.VendingState;

public class VendingMachine {

    private int itemCount;
    private int itemPrice;
    private VendingState currentState;
    private int insertedCoin;

    private VendingState noCoinState;
    private VendingState hasCoinState;
    private VendingState despenseState;
    private VendingState soldOutState;

    public VendingMachine(int itemCount, int itemPrice){
        this.itemCount=itemCount;
        this.itemPrice=itemPrice;
        this.noCoinState=new NoCoinState();
        this.hasCoinState=new HasCoinState();
        this.despenseState=new DespenseState();
        this.soldOutState=new SoldOutState();


        if(itemCount>0){
            this.currentState=noCoinState;
        }
        else{
            this.currentState=soldOutState;
        }
    }

    public void insertCoin(int coin){
        currentState=currentState.insertCoin(this,coin);
    }

    public void selectItem(){
        currentState=currentState.selectItem(this);
    }

    public void despenseItem(){
        currentState=currentState.despense(this);
    }


    public void returnCoin(){
        currentState=currentState.returnCoin(this);
    }

    public void refill(int qantity){
        currentState=currentState.refill(this, qantity);
    }

    public void printStatus() {
        System.out.println("\n--- Vending Machine Status ---");
        System.out.println("Items remaining: " + itemCount);
        System.out.println("Inserted coin: Rs " + insertedCoins);
        System.out.println("Current state: " + currentState.getStateName() + "\n");
    }

    // Getters for states
    public VendingState getNoCoinState() {
        return noCoinState;
    }
    public VendingState getHasCoinState() {
        return hasCoinState;
    }
    public VendingState getDispenseState() {
        return dispenseState;
    }
    public VendingState getSoldOutState() {
        return soldOutState;
    }

    public int getItemCount() {
        return itemCount;
    }
    public void decrementItemCount() {
        itemCount--;
    }

    public void incrementItemCount(int count) {
        itemCount += count;
    }
    public void incrementItemCount() {
        itemCount += 1;
    }
    public int getInsertedCoin() {
        return insertedCoins;
    }
    public void setInsertedCoin(int coin) {
        insertedCoins = coin;
    }
    public void addCoin(int coin) {
        insertedCoins += coin;
    }
    public int getPrice() {
        return this.itemPrice;
    }
    public void setPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }
}
