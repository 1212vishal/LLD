package SchedulingStategy;

import Model.Elevator;

public interface SchedulingStategy {
    public int getNextStop(Elevator elevator);
}
