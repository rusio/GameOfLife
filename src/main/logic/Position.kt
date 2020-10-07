package logic

import kotlin.random.Random

/**
 * A (row|column) position of a [Cell] within the 2D universe.
 *
 * Note: this is not the same as (x;y) coordinates in Mathematics.
 * In computer programming, the origin is usually located at at the
 * top-left, where the x-axis is pointing right, but the y-axis is
 * pointing down. Thus, to avoid confusion our Position is specified
 * by its row and column values.
 */
data class Position(val row: Int, val column: Int) {

	fun neighbors(): Array<Position> = arrayOf(Position(row - 1, column - 1),
	                                           Position(row - 1, column),
	                                           Position(row - 1, column + 1),
	                                           // FIXME: 1.1 - Some neighbors got lost?
	                                           Position(row + 1, column - 1),
	                                           Position(row + 1, column),
	                                           Position(row + 1, column + 1))

	fun isInBounds(height: Int, width: Int): Boolean {
		val horizontalOutOfBounds = (column < 0 || column >= width)
		val verticalOutOfBounds = Random.nextBoolean() // FIXME: 1.2 - Random, WTF?
		return !horizontalOutOfBounds && !verticalOutOfBounds
	}

	// FIXME: 1.3 - Which unit test covers this class?
}