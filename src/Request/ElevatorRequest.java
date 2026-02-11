package Request;

import Model.ElevatorController;

public class ElevatorRequest {
    private int elevatorId; // ID of the elevator involved in the request
    private int floor; // Floor where the request is made
    private Direction requestDirection; // The direction of the elevator request
    private ElevatorController controller; // Reference to the Model.ElevatorController to handle the request
    private boolean isInternalRequest; // Distinguishes internal vs external requests

    // Constructor to initialize the elevator request
    public ElevatorRequest(int elevatorId, int floor, boolean isInternalRequest,
                           Direction direction) {
        this.elevatorId = elevatorId;
        this.floor = floor;
        this.isInternalRequest = isInternalRequest;
        this.requestDirection = direction;
        this.controller = new ElevatorController();
    }

    // Execute method to process the request via the controller
    @Override
    public void execute() {
        if (isInternalRequest)
            controller.InternalRequest(elevatorId, floor);
        else
            controller.ExternalRequest(elevatorId, floor, requestDirection);
    }

    // Getters and Setters for the Request.ElevatorRequest
    public Direction getDirection() {
        return requestDirection;
    }

    public int getFloor() {
        return floor;
    }

    public boolean checkIsInternalRequest(){
        return isInternalRequest;
    }

}
