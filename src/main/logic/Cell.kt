package logic

data class Cell(var state: State) {

	val neighbors: MutableList<Cell> = mutableListOf()

	fun setupNeighbors(cellPosition: Position, universeCells: Array<Array<Cell>>) {
		val height = universeCells.size
		val width = universeCells[0].size

		val neighborPositions: List<Position> = cellPosition.neighbors().filter {
			it.isInBounds(height, width)
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

	fun isAlive(): Boolean = (state == State.ALIVE)

	fun evolve(livingNeighborsCount: Int = neighbors.count { it.isAlive() }) {
		state = when (livingNeighborsCount) {
			0, 1 -> State.DEAD
			2 -> state
			3 -> State.ALIVE
			else -> State.DEAD
		}
	}

	fun live() {
		state = State.ALIVE
	}

	fun die() {
		state = State.DEAD
	}
}
