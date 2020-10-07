package logic

/**
 * Represents the universe, containing all the [Cell]s, whether dead or alive.
 *
 * It enables us to simulate the evolution of all the [Cell]s as time passes.
 */
class Universe(val height: Int, val width: Int) {

	/**
	 * A 2D array that serves as a data structure for holding all the [Cell]s.
	 *
	 * Example: Universe(height=3, width=5) has cells distributed like this:
	 *
	 *                x, x, x, x, x
	 *                x, x, x, x, x
	 *                x, x, x, x, x
	 *
	 * Here, the top-left cell has Position(0, 0) and the bottom-right cell has Position(2, 4).
	 *
	 *           (0,0) (0,1) (0,2) (0,3) (0,4)
	 *           (1,0) (1,1) (1,2) (1,3) (1,4)
	 *           (2,0) (2,1) (2,2) (2,3) (2,4)
	 *
	 * Note: After the initial construction of the universe, all the [Cell]s are dead.
	 */
	val cells = Array(height) {
		Array(width) { Cell(State.DEAD) }
	}

	init {
		for (row in 0 until height) {
			for (column in 0 until width) {
				val cell = cells[row][column]
				cell.setupNeighbors(Position(row, column), cells)
			}
		}
	}

	/** Evolves the Universe to the next generation of [Cell]s. */
	fun evolve() {
		val livingNeighborCounts = Array(height) {
			Array(width) { 0 }
		}

		for (row in 0 until height) {
			for (column in 0 until width) {
				val cell = cells[row][column]
				livingNeighborCounts[row][column] = cell.neighbors.count { neighbor -> neighbor.isAlive() }
			}
		}

		for (row in 0 until height) {
			for (column in 0 until width) {
				val cell = cells[row][column]
				cell.evolve(livingNeighborCounts[row][column])
			}
		}
	}

	/** Returns the [Cell] at the specified position. */
	fun cellAt(row: Int, column: Int): Cell {
		return cells[1][2] // FIXME: 3.1 - Something is wrong here...
	}

	/** Resets the Universe, setting all [Cell]s to DEAD. */
	fun reset() {
		for (row in 0 until height) {
			for (column in 0 until width) {
				val cell = cells[row][column]
				cell.die()
			}
		}
	}
}
