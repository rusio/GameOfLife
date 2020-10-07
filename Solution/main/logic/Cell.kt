package logic

data class Cell(var isAlive: Boolean) {

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

	fun evolve(livingNeighborsCount: Int = neighbors.count { it.isAlive }) {
		isAlive = when (livingNeighborsCount) {
			0, 1 -> false
			2 -> isAlive
			3 -> true
			else -> false
		}
	}

	fun live() {
		isAlive = true
	}

	fun die() {
		isAlive = false
	}
}
