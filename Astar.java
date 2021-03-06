import java.util.*;

public class Astar {
	private Hashtable<String, Integer> misplacedExistSet;
	private Hashtable<String, Integer> manhattanExistSet;
	private MisplacedHeuristic misplacedAlg;
	private ManhattanHeuristic manhattanAlg;
	private PriorityQueue<Board> misplacedFrontier;
	private PriorityQueue<Board> manhattanFrontier;
	private String goal = "012345678";
	private int nodesExplored;
	private Board goalState = null;

	public Board getGoal() {
		return goalState;
	}

	public int astarMisplaced(Board board) {
		misplacedExistSet = new Hashtable<String, Integer>();
		misplacedAlg = new MisplacedHeuristic();
		misplacedFrontier = new PriorityQueue<Board>(20, misplacedAlg);
		nodesExplored = 1;
		misplacedExistSet.put(board.getAll(), 1);
		board.putCost(0);
		misplacedFrontier.add(board);
		while ((misplacedFrontier.size() > 0) && (!misplacedFrontier.peek().getAll().equals(goal))) {
			processMisplacedChildren(misplacedFrontier.poll());
		}
		goalState = misplacedFrontier.poll();
		System.out.println("End Cost: " + goalState.getCost());
		return nodesExplored;
	}

	public int astarManhattan(Board board) {
		manhattanExistSet = new Hashtable<String, Integer>();
		manhattanAlg = new ManhattanHeuristic();
		manhattanFrontier = new PriorityQueue<Board>(20, manhattanAlg);
		nodesExplored = 1;
		manhattanExistSet.put(board.getAll(), 1);
		board.putCost(0);
		manhattanFrontier.add(board);
		while ((manhattanFrontier.size() > 0) && (!manhattanFrontier.peek().getAll().equals(goal))) {
			processManhattanChildren(manhattanFrontier.poll());
		}
		goalState = manhattanFrontier.poll();
		System.out.println("End Cost: " + goalState.getCost());
		return nodesExplored;
	}

	private void processMisplacedChildren(Board board) {
		int cost = board.getCost();
		Board testLeft = testMove(board, "left");
		Board testRight = testMove(board, "right");
		Board testUp = testMove(board, "up");
		Board testDown = testMove(board, "down");
		if (testLeft != null) {
			if (misplacedExistSet.get(testLeft.getAll()) == null) {
				misplacedExistSet.put(testLeft.getAll(), 1);
				testLeft.putCost(cost + 1);
				testLeft.setParent(board);
				misplacedFrontier.add(testLeft);
				nodesExplored++;
			}
		}
		if (testRight != null) {
			if (misplacedExistSet.get(testRight.getAll()) == null) {
				misplacedExistSet.put(testRight.getAll(), 1);
				testRight.putCost(cost + 1);
				testRight.setParent(board);
				misplacedFrontier.add(testRight);
				nodesExplored++;
			}
		}
		if (testUp != null) {
			if (misplacedExistSet.get(testUp.getAll()) == null) {
				misplacedExistSet.put(testUp.getAll(), 1);
				testUp.putCost(cost + 1);
				testUp.setParent(board);
				misplacedFrontier.add(testUp);
				nodesExplored++;
			}
		}
		if (testDown != null) {
			if (misplacedExistSet.get(testDown.getAll()) == null) {
				misplacedExistSet.put(testDown.getAll(), 1);
				testDown.putCost(cost + 1);
				testDown.setParent(board);
				misplacedFrontier.add(testDown);
				nodesExplored++;
			}
		}
	}

	private void processManhattanChildren(Board board) {
		int cost = board.getCost();
		Board testLeft = testMove(board, "left");
		Board testRight = testMove(board, "right");
		Board testUp = testMove(board, "up");
		Board testDown = testMove(board, "down");
		if (testLeft != null) {
			if (manhattanExistSet.get(testLeft.getAll()) == null) {
				manhattanExistSet.put(testLeft.getAll(), 1);
				testLeft.putCost(cost + 1);
				testLeft.setParent(board);
				manhattanFrontier.add(testLeft);
				nodesExplored++;
			}
		}
		if (testRight != null) {
			if (manhattanExistSet.get(testRight.getAll()) == null) {
				manhattanExistSet.put(testRight.getAll(), 1);
				testRight.putCost(cost + 1);
				testRight.setParent(board);
				manhattanFrontier.add(testRight);
				nodesExplored++;
			}
		}
		if (testUp != null) {
			if (manhattanExistSet.get(testUp.getAll()) == null) {
				manhattanExistSet.put(testUp.getAll(), 1);
				testUp.putCost(cost + 1);
				testUp.setParent(board);
				manhattanFrontier.add(testUp);
				nodesExplored++;
			}
		}
		if (testDown != null) {
			if (manhattanExistSet.get(testDown.getAll()) == null) {
				manhattanExistSet.put(testDown.getAll(), 1);
				testDown.putCost(cost + 1);
				testDown.setParent(board);
				manhattanFrontier.add(testDown);
				nodesExplored++;
			}
		}
	}

	private Board testMove(Board board, String direction) {
		Board tempBoard = new Board(board.getAll());
		if (direction.equals("left")) {
			if (tempBoard.shiftLeft()) {
				return tempBoard;
			} else {
				return null;
			}
		} else if (direction.equals("right")) {
			if (tempBoard.shiftRight()) {
				return tempBoard;
			} else {
				return null;
			}
		} else if (direction.equals("up")) {
			if (tempBoard.shiftUp()) {
				return tempBoard;
			} else {
				return null;
			}
		} else if (direction.equals("down")) {
			if (tempBoard.shiftDown()) {
				return tempBoard;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}
