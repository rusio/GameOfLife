package logic

import kotlin.random.Random

// FIXME: 2.4 - The class Cell is missing a simple documentation sentence.

data class Cell(var state: State) {

	val neighbors: MutableList<Cell> = mutableListOf()

	fun setupNeighbors(cellPosition: Position, universeCells: Array<Array<Cell>>) {
		val height = universeCells.size
		val width = universeCells[0].size

		val neighborPositions: List<Position> = cellPosition.neighbors().filter { position ->
			position.isInBounds(height, width)
		}

		for (neighborPosition in neighborPositions) {
			val neighbor = universeCells[neighborPosition.row][neighborPosition.column]
			neighbors.add(neighbor)
		}
	}

	fun hasNeighbors(vararg others: Cell): Boolean {
		others.forEach { if (!this.neighbors.contains(it)) return false }
		return others.size == this.neighbors.size
	}

	fun isAlive(): Boolean {
		return (state == State.ALIVE)
	}

	fun evolve(livingNeighborsCount: Int = neighbors.count { it.isAlive() }) {
		// FIXME: 2.3 - Use the existing tests for implementing the rules of life!
		// FIXME: 2.4 - Life or death should not happen by random :(
		if (Random.nextBoolean()) {
			live() // FIXME: 2.5 - Let a dead cell become alive, if it has 3 neighbors.
		}
		else {
			die() // FIXME: 2.6 - Let a living cell die, if lonely or overcrowded.
		}
	}

	fun live() {
		// FIXME: 2.1 - How to let the Cell live?
	}

	fun die() {
		// FIXME: 2.2 - Write basic unit tests for live() and die()
		state = State.DEAD
	}
}
