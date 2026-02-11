package Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Elevator {
    private int id;
    private int currentFloor;
    private Direction direction;
    private ElevatorState state;
    private ArrayList<ElevatorObserver> observers;
    private Queue<ElevatorCommand> requests;

    public Elevator(int id){
        this.id=id;
        this.currentFloor=1;
        this.direction= Direction.IDLE;
        this.state= ElevatorState.IDLE;
        this.observers= new ArrayList<>();
        this.requests=new LinkedList<>();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void addObserver(ElevatorObserver observer){
        observers.add(observer);
    }

    public void removeObserver(ElevatorObserver observer){
        observers.remove(observer);
    }

    public void notifyStateChange(ElevatorState state){
        for(ElevatorObserver observer: observers){
            observer.onElevatorStateChange(this,state);
        }
    }

    private void notifyFloorChange(int floor) {
        for (ElevatorObserver observer : observers) {
            observer.onElevatorFloorChange(this, floor);
        }
    }

    public void setState(ElevatorState state){
        this.state=state;
        notifyStateChange(state);
    }

    public void addRequest(ElevatorCommand request){
        if(!requests.contains(request)){
            requests.add(request);
        }

        int requestFloor=request.getFloor();
        if(state== ElevatorState.IDLE && !requests.isEmpty()){
            if(requestFloor>currentFloor){
                direction= Direction.UP;
            } else if (requestFloor<currentFloor) {
                direction= Direction.DOWN;
            }
            setState(ElevatorState.MOVING);
        }

    }



    public void moveToNextStop(int nextStop){
        if(state!= ElevatorState.MOVING)
            return;

        while(currentFloor!=nextStop){
            if(direction== Direction.UP)
                currentFloor++;
            else
                currentFloor--;

            notifyFloorChange(currentFloor);

            if(currentFloor==nextStop){
                completeArrival();
                return;
            }
        }

    }


    public void CompleteArrival(){

        setState(ElevatorState.STOPPED);
        requests.removeIf(request -> request.getFloor()==currentFloor);

        if(requests.isEmpty()){
            setDirection(Direction.IDLE);
            setState(ElevatorState.IDLE);
        }
        else{
            setState(ElevatorState.MOVING);
        }
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public ElevatorState getState() {
        return state;
    }

    // Get a copy of the current requests queue to prevent external modification
    public Queue<ElevatorCommand> getRequests() {
        return  new LinkedList<>(requests);
    }

    // Get a list of all destination floors for display purposes
    public List<ElevatorCommand> getDestinationFloors() {
        return new ArrayList<>(requests);
    }
}
