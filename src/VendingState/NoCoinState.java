package VendingState;

public class NoCoinState implements VendingState{
    @Override
    public VendingState insertCoin(VendingMachine machine,int coin){
        machine.setInsertCoin(coin);
        System.out.println("Coin inserted. Current balance: Rs " + coin);
        return machine.getHasCoinState();
    }

    @Override
    public VendingState selectItem(VendingMachine machine){
        System.out.println("Please insert coin first!");
        return machine.getNoCoinState();
    }

    @Override
    public VendingState dispense(VendingMachine machine){
        System.out.println("Please insert coin first!");
        return machine.getNoCoinState();
    }

    @Override
    public VendingState returnCoin(VendingMachine machine) {
        System.out.println("Please insert coin first!");
        return machine.getNoCoinState();
    }

    @Override
    public VendingState refill(VendingMachine machine, int qantity) {
        System.out.println("Please insert coin first!");
        return machine.getNoCoinState();
    }

    @Override
    public String getStateName() {
        return "NO-Coin";
    }
}


public class HasCoinState implements VendingState{

    @Override
    public VendingState insertCoin(VendingMachine machine, int coin) {
        machine.setInsertCoin(coin);
        System.out.println("Additional coin inserted. Current balance: Rs " + machine.getInsertedCoin());
        return machine.getHasCoinState();
    }

    @Override
    public VendingState selectItem(VendingMachine machine) {
        if(machine.getInsertedCoin()>=machine.getPrice()){
            System.out.println("Item selected. Dispensing...");
            int change = machine.getInsertedCoin() - machine.getPrice();
            if (change > 0) {
                System.out.println("Change returned: Rs " + change);
            }
            machine.setInsertedCoin(0);

            return machine.getDispenseState();
        }

        else{
            int needed = machine.getPrice() - machine.getInsertedCoin();
            System.out.println("Insufficient funds. Need Rs " + needed + " more.");
            return machine.getHasCoinState(); // Stay in same state
        }
    }

    public VendingState dispense(VendingMachine machine) {
        System.out.println("Please select an item first!");
        return machine.getHasCoinState(); // Stay in same state
    }

    public VendingState returnCoin(VendingMachine machine) {
        System.out.println("Coin returned: Rs " + machine.getInsertedCoin());
        machine.setInsertedCoin(0);
        return machine.getNoCoinState(); // Transition to NoCoinState
    }

    public VendingState refill(VendingMachine machine, int quantity) {
        System.out.println("Can't refil in this state");
        return machine.getHasCoinState(); // Stay in same state
    }

    public String getStateName() {
        return "HAS_COIN";
    }
}

public VendingState DespenseState implements VendingState{


    public VendingState insertCoin(VendingMachine machine, int coin) {
        System.out.println("Please wait, already dispensing item. Coin returned: Rs " + coin);
        return machine.getDispenseState();  // Stay in same state
    }

    public VendingState selectItem(VendingMachine machine) {
        System.out.println("Already dispensing item. Please wait.");
        return machine.getDispenseState(); // Stay in same state
    }

   public VendingState dispense(VendingMachine machine){
        System.out.println("Item dispensed!");
        machine.decrementItemCount();
        if (machine.getItemCount() > 0) {
            return machine.getNoCoinState(); // Transition to NoCoinState
        }
        else {
            System.out.println("Machine is now sold out!");
            return machine.getSoldOutState(); // Transition to SoldOutState
        }
    }

    public VendingState returnCoin(VendingMachine machine) {
        System.out.println("Cannot return coin while dispensing item!");
        return machine.getDispenseState(); // Stay in same state
    }

    public VendingState refill(VendingMachine machine, int quantity) {
        System.out.println("Can't refil in this state");
        return machine.getDispenseState(); // Stay in same state
    }

    public String getStateName() {
        return "DISPENSING";
    }

}


class SoldOutState implements VendingState {
    public VendingState insertCoin(VendingMachine machine, int coin) {
        System.out.println("Machine is sold out. Coin returned: Rs " + coin);
        return machine.getSoldOutState(); // Stay in same state
    }

    public VendingState selectItem(VendingMachine machine) {
        System.out.println("Machine is sold out!");
        return machine.getSoldOutState(); // Stay in same state
    }

    public VendingState dispense(VendingMachine machine) {
        System.out.println("Machine is sold out!");
        return machine.getSoldOutState(); // Stay in same state
    }

    public VendingState returnCoin(VendingMachine machine) {
        System.out.println("Machine is sold out. No coin inserted.");
        return machine.getSoldOutState(); // Stay in same state
    }

    public VendingState refill(VendingMachine machine, int quantity) {
        System.out.println("Items refilling");
        machine.incrementItemCount(quantity);
        return machine.getNoCoinState();
    }

    public String getStateName() {
        return "SOLD_OUT";
    }
}

