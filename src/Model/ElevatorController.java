package Model;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {
    private List<Elevator> elevators;
    private List<Floors> floors;
    private SchedulingStategy schedulingStartegy;
    private int currentElavatorId;

    public ElevatorController(int numberOfElevators, int numberOfFloors){
        this.elevators=new ArrayList<>();
        this.floors=new ArrayList<>();
        for (int i = 1; i <= numberOfElevators; i++) {
            elevators.add(new Elevator(i));
        }

        for (int i = 1; i <= numberOfFloors; i++) {
            floors.add(new Floors(i));
        }

    }

    // Set the scheduling strategy dynamically
    public void setSchedulingStrategy(SchedulingStategy strategy) {
        this.schedulingStartegy = strategy;
    }

    // Handle external elevator requests from a specific floor

    public void ExternalRequest(int elevatorId,int floorNumber, Direction direction){
        System.out.println(
                "External request: Floor " + floorNumber + ", Enum.Direction " + direction);
        Elevator selectedElevator=getElevatorById(elevatorId);
        if(selectedElevator!=null){
            selectedElevator.addRequest(new ElevatorCommand(elevatorId, floorNumber, false, direction));
            System.out.println("Assigned elevator " + selectedElevator.getId()
                    + " to floor " + floorNumber);
        }
        else {
            System.out.println("No elevator available for floor " + floorNumber);
        }
    }

    public void InternalRequest(int elevatorId, int floorNumber){
        Elevator elevator = getElevatorById(elevatorId);
        System.out.println("Internal request: Model.Elevator " + elevator.getId()
                + " to floor " + floorNumber);
        Direction direction = floorNumber > elevator.getCurrentFloor()
                ? Direction.UP
                : Direction.DOWN;
        elevator.addRequest(
                new ElevatorCommand(elevatorId, floorNumber, true, direction));
    }

    private Elevator getElevatorById(int elevatorId) {
        for (Elevator elevator : elevators) {
            if (elevator.getId() == elevatorId)
                return elevator;
        }
        return null; // Return null if no matching elevator is found
    }

    public void step(){
        for(Elevator elevator: elevators){
            if(!elevator.getRequests().isEmpty()){
                int nextStop = schedulingStartegy.getNextStop(elevator);

                if (elevator.getCurrentFloor() != nextStop)
                    elevator.moveToNextStop(nextStop);
            }
        }
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    public List<Floors> getFloors() {
        return floors;
    }
}
