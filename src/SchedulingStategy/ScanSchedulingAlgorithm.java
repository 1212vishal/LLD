package SchedulingStategy;

import Model.Elevator;
import Request.ElevatorCommand;

import java.util.Queue;

public class ScanSchedulingAlgorithm implements SchedulingStategy {

    @Override
    public int getNextStop(Elevator elevator) {
        // Retrieve elevator's current direction and floor
        Direction elevatorDirection = elevator.getDirection();
        int currentFloor = elevator.getCurrentFloor();
        Queue<ElevatorCommand> requests = elevator.getRequestsQueue();


        // If there are no requests, stay on the current floor
        if (requests.isEmpty())
            return currentFloor;


        // Priority queues to handle requests in up and down directions
        PriorityQueue<ElevatorCommand> upQueue =
                new PriorityQueue<>(); // Min-heap for upward requests
        PriorityQueue<ElevatorCommand> downQueue =
                new PriorityQueue<>((a, b) -> b.getFloor() - a.getFloor()); // Max-heap for downward requests


        // Categorize requests based on their relative position to the current floor
        while (!requests.isEmpty()) {
            ElevatorCommand elevatorRequest = requests.poll();
            int floor = elevatorRequest.getFloor();
            if (floor > currentFloor)
                upQueue.add(elevatorRequest);
            else
                downQueue.add(elevatorRequest);
        }


        // Handle the case when the elevator is IDLE
        if (elevatorDirection == Direction.IDLE) {
            // Determine the nearest request and set direction accordingly
            int nearestUpwardRequest =
                    upQueue.isEmpty() ? -1 : upQueue.peek().getFloor();
            int nearestDownwardRequest =
                    downQueue.isEmpty() ? -1 : downQueue.peek().getFloor();


            if (nearestUpwardRequest == -1) {
                elevator.setDirection(Direction.DOWN);
                return downQueue.poll().getFloor();
            } else if (nearestDownwardRequest == -1) {
                elevator.setDirection(Direction.UP);
                return upQueue.poll().getFloor();
            } else {
                // Choose the closest request
                if (Math.abs(nearestUpwardRequest - currentFloor)
                        < Math.abs(nearestDownwardRequest - currentFloor)) {
                    elevator.setDirection(Direction.UP);
                    return upQueue.poll().getFloor();
                } else {
                    elevator.setDirection(Direction.DOWN);
                    return downQueue.poll().getFloor();
                }
            }
        }


        // Handle movement in the UP direction
        if (elevatorDirection == Direction.UP) {
            return !upQueue.isEmpty() ? upQueue.poll().getFloor()
                    : switchDirection(elevator, downQueue);
        }
        // Handle movement in the DOWN direction
        else {
            return !downQueue.isEmpty() ? downQueue.poll().getFloor()
                    : switchDirection(elevator, upQueue);
        }


    }

    private int switchDirection(
            Elevator elevator, PriorityQueue<ElevatorCommand> requestsQueue) {
        elevator.setDirection(elevator.getDirection() == Direction.UP
                ? Direction.DOWN
                : Direction.UP);
        return requestsQueue.isEmpty() ? elevator.getCurrentFloor()
                : requestsQueue.poll().getFloor();
    }

}
