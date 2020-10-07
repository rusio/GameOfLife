package logic

data class Position(val row: Int, val column: Int) {

	fun neighbors(): Array<Position> = arrayOf(Position(row - 1, column - 1),
	                                           Position(row - 1, column),
	                                           Position(row - 1, column + 1),
	                                           Position(row, column - 1),
	                                           Position(row, column + 1),
	                                           Position(row + 1, column - 1),
	                                           Position(row + 1, column),
	                                           Position(row + 1, column + 1))

	fun isInBounds(height: Int, width: Int): Boolean = !(column < 0 || column >= width || row < 0 || row >= height)

}