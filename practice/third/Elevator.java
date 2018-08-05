
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.TreeSet;

/**
 * <pre>
 * Design a single elevator system
 * 
 * Handle requests coming from outside which will request for the lift to stop so that they can get in.
 * They will press an up arrow button if they want to go floor which is up and a down arrow button if 
 * they want to go down floor
 * 
 * Once a person gets inside the lift, there will be requests to get down at a particular floor
 * 
 * Handle this optimally
 * </pre>
 * @author rebecca
 */
public class Elevator {

	private int totalFloors;

	private int currentFloor = 1;

	private Direction currentDirection = Direction.UP;

	private TreeSet<Integer> upRequests = new TreeSet<>();

	private TreeSet<Integer> downRequests = new TreeSet<>(Collections.reverseOrder());

	private TreeSet<Integer> getInRequestsUp = new TreeSet<>();

	private TreeSet<Integer> getInRequestsDown = new TreeSet<>();

	public Elevator(int totalFloors) {
		this.totalFloors = totalFloors;
	}

	public void takeGetDownRquest(int floorNumber) {
		if (currentFloor < floorNumber) {
			downRequests.add(floorNumber);
		} else {
			upRequests.add(floorNumber);
		}
	}

	public void takeGetInRequest(int floorNumber, Direction direction) {
		if (direction == Direction.UP)
			getInRequestsUp.add(floorNumber);
		else if (direction == Direction.DOWN)
			getInRequestsDown.add(floorNumber);
	}

	public void move() {
		if (currentDirection == Direction.UP) {
			moveUp();
		} else if (currentDirection == Direction.DOWN) {
			moveDown();
		}
	}

	public void moveUp() {

		Integer nextGetInFloor = getInRequestsUp.pollFirst();
		Integer nextGetDownFloor = upRequests.pollFirst();

		if (nextGetDownFloor == null || nextGetDownFloor == null) {
			if (nextGetInFloor == null) {
				if (nextGetDownFloor == null) {
					// TODO : nothing to do or move one level up
				} else {

				}
			} else {

			}
		} else {

		}
	}

	public void moveDown() {

	}

}

class ElevatorHandler {

	public static void main(String[] args) throws Exception {

		Elevator elevator = new Elevator(40);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			while (true) {
				String line = reader.readLine();
				Command command = parseCommand(line);
				handleCommand(command, elevator);
			}
		}
	}

	private static void handleCommand(Command command, Elevator elevator) {
		switch (command.getCommandType()) {
		case GET_IN:
			elevator.takeGetInRequest(command.getFloorNumber(), command.getDirection());
			break;

		case GET_OUT:
			elevator.takeGetDownRquest(command.getFloorNumber());
			break;

		case MOVE:
			elevator.move();
			break;

		default:
			throw new RuntimeException("Unexpected command type while processing the command !");
		}
	}

	private static Command parseCommand(String line) {
		String[] fields = line.split("\t", -1);
		// expected format
		// commandType[ TAB ] floorNumber [ TAB ] Direction
		CommandType commandType = CommandType.valueOf(fields[0]);

		Command command = null;

		switch (commandType) {
		case MOVE:
			command = new Command(commandType, null, -1);
			break;

		case GET_IN:
			int getInFloor = Integer.parseInt(fields[1]);
			Direction direction = Direction.valueOf(fields[2]);
			command = new Command(commandType, direction, getInFloor);
			break;

		case GET_OUT:
			int getOutFloor = Integer.parseInt(fields[1]);
			command = new Command(commandType, null, getOutFloor);
			break;

		default:
			throw new RuntimeException("Unexpected command type while parsing !");
		}

		return command;
	}

}

class Command {
	private CommandType commandType;

	private Direction direction;

	private int floorNumber;

	public Command(CommandType commandType, Direction direction, int floorNumber) {
		super();
		this.commandType = commandType;
		this.direction = direction;
		this.floorNumber = floorNumber;
	}

	/**
	 * @return the commandType
	 */
	public CommandType getCommandType() {
		return commandType;
	}

	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * @return the floorNumber
	 */
	public int getFloorNumber() {
		return floorNumber;
	}

}

enum CommandType {
	GET_IN, GET_OUT, MOVE;
}

enum Direction {
	UP, DOWN;
}
