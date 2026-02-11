package Model;

public class Building {

    private String name;
    private int numberOfFloors;
    private ElevatorController elevatorController;

    public Building(String name, int numberOfFloors, int numberOfElevators) {
        this.name = name; // Assign the building's name
        this.numberOfFloors = numberOfFloors; // Set the total number of floors
        // Initialize the elevator controller with the specified number of elevators
        // and floors
        this.elevatorController =
                new ElevatorController(numberOfElevators, numberOfFloors);
    }

    // Getters and Setters for the Model.Building
    public String getName() {
        return name;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public ElevatorController getElevatorController() {
        return elevatorController;
    }
}
