package VendingState;

public interface VendingState {
    VendingState insertCoin(VendingMachine machine, int coin);
    VendingState selectItem(VendingMachine machine);
    VendingState despense(VendingMachine machine);
    VendingState returnCoin(VendingMachine machine);
    VendingState refill(VendingMachine machine,int qantity);

    String getStateName();
}
