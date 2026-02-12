//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("=== Water Bottle VENDING MACHINE ===");

        int itemCount = 2;
        int itemPrice = 20;
        VendingMachine machine = new VendingMachine(itemCount, itemPrice);
        machine.printStatus();

        System.out.println("1. Trying to select item without coin:");
        machine.selectItem();  // Should ask for coin, no state change
        machine.printStatus();

        System.out.println("2. Inserting coin:");
        machine.insertCoin(10);  // State changes to HAS_COIN
        machine.printStatus();

        System.out.println("3. Selecting item with insufficient funds:");
        machine.selectItem();  // Insufficient funds, stays in HAS_COIN
        machine.printStatus();

        System.out.println("4. Adding more coins:");
        machine.insertCoin(10);  // Add more money, stays in HAS_COIN
        machine.printStatus();

        System.out.println("5. Selecting item Now");
        machine.selectItem();  // State changes to SOLD
        machine.printStatus();

        System.out.println("6. Dispensing item:");
        machine.dispense(); // State changes to NO_COIN (items remaining)
        machine.printStatus();

        System.out.println("7. Buying last item:");
        machine.insertCoin(20);  // State changes to HAS_COIN
        machine.selectItem();  // State changes to SOLD
        machine.dispense(); // State changes to SOLD_OUT (no items left)
        machine.printStatus();

        System.out.println("8. Trying to use sold out machine:");
        machine.insertCoin(5);  // Coin returned, stays in SOLD_OUT

        System.out.println("8. Trying to use sold out machine:");
        machine.insertCoin(5);  // Coin returned, stays in SOLD_OUT

    }
}