package elevator;

import building.enums.Direction;

public class ElevatorReport {
    private final int elevatorId;
    private final int currentFloor;
    private final boolean doorClosed;
    private final boolean[] floorRequests;
    private final Direction direction;
    private final int doorOpenTimer;
    private final int endWaitTimer;
    private final boolean outOfService;
    private final boolean isTakingRequests;

    public ElevatorReport(int elevatorId, int currentFloor, Direction direction, boolean doorClosed, boolean[] floorRequests, int doorOpenTimer, int endWaitTimer, boolean outOfService, boolean isTakingRequests) {
        this.elevatorId = elevatorId;
        this.currentFloor = currentFloor;
        this.doorClosed = doorClosed;
        this.floorRequests = floorRequests;
        this.direction = direction;
        this.doorOpenTimer = doorOpenTimer;
        this.endWaitTimer = endWaitTimer;
        this.outOfService = outOfService;
        this.isTakingRequests = isTakingRequests;
    }

    public int getElevatorId() {
        return this.elevatorId;
    }

    public int getCurrentFloor() {
        return this.currentFloor;
    }

    public boolean isDoorClosed() {
        return this.doorClosed;
    }

    public boolean[] getFloorRequests() {
        return this.floorRequests;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public int getDoorOpenTimer() {
        return this.doorOpenTimer;
    }

    public int getEndWaitTimer() {
        return this.endWaitTimer;
    }

    public boolean isOutOfService() {
        return this.outOfService;
    }

    public boolean isTakingRequests() {
        return this.isTakingRequests;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.outOfService && this.currentFloor == 0) {
            sb.append(String.format("Out of Service[Floor %d]", this.currentFloor));
            return sb.toString();
        }
        if (this.endWaitTimer > 0) {
            sb.append(String.format("Waiting[Floor %d, Time %d]", this.currentFloor, this.endWaitTimer));
            return sb.toString();
        }
        sb.append(String.format("[%d|%s|", this.currentFloor, this.direction));
        if (this.doorClosed) {
            sb.append("C  ]<");
        } else {
            sb.append(String.format("O %d]<", this.doorOpenTimer));
        }
        for (int i = 0; i < this.floorRequests.length; i++) {
            if (this.floorRequests[i]) {
                sb.append(String.format(" %2d", i));
            } else {
                sb.append(" --");
            }
        }
        sb.append(">");
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElevatorReport)) {
            return false;
        }
        ElevatorReport that = (ElevatorReport)o;
        if (this.elevatorId != that.elevatorId || this.currentFloor != that.currentFloor ||
            this.doorClosed != that.doorClosed || this.doorOpenTimer != that.doorOpenTimer ||
            this.endWaitTimer != that.endWaitTimer || this.outOfService != that.outOfService ||
            this.isTakingRequests != that.isTakingRequests || this.direction != that.direction) {
            return false;
        }
        for (int i = 0; i < this.floorRequests.length; i++) {
            if (this.floorRequests[i] != that.floorRequests[i]) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int result = 17;
        result = 31 * result + this.elevatorId;
        result = 31 * result + this.currentFloor;
        result = 31 * result + (this.doorClosed ? 1 : 0);
        result = 31 * result + this.doorOpenTimer;
        result = 31 * result + this.endWaitTimer;
        result = 31 * result + (this.outOfService ? 1 : 0);
        result = 31 * result + (this.isTakingRequests ? 1 : 0);
        result = 31 * result + this.direction.hashCode();
        for (boolean floorRequest : this.floorRequests) {
            result = 31 * result + (floorRequest ? 1 : 0);
        }
        return result;
    }
}
