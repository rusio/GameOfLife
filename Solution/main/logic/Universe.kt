package logic

class Universe(val height: Int, val width: Int) {

	val cells = Array(height) {
		Array(width) { Cell(false) }
	}

	init {
		for (row in 0 until height) {
			for (column in 0 until width) {
				val cell = cells[row][column]
				cell.setupNeighbors(Position(row, column), cells)
			}
		}
	}

	fun evolve() {
		val livingNeighborCounts = Array(height) {
			Array(width) { 0 }
		}

		for (row in 0 until height) {
			for (column in 0 until width) {
				val cell = cells[row][column]
				livingNeighborCounts[row][column] = cell.neighbors.count { neighbor -> neighbor.isAlive }
			}
		}

		for (row in 0 until height) {
			for (column in 0 until width) {
				val cell = cells[row][column]
				cell.evolve(livingNeighborCounts[row][column])
			}
		}
	}

	fun cellAt(row: Int, column: Int): Cell {
		return cells[row][column]
	}

	fun reset() {
		for (row in 0 until height) {
			for (column in 0 until width) {
				val cell = cells[row][column]
				cell.die()
			}
		}
	}
}
