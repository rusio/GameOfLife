package logic

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CellTest {

	@Test
	fun cellShouldDieWhenUnderpopulation() {
		val cell = Cell(true)

		cell.evolve()

		assertFalse(cell.isAlive)
	}

	@Test
	fun cellShouldLiveWhenTwoOrThreeLivingNeighbors() {
		val cell = Cell(true)
		cell.neighbors.add(Cell(true))
		cell.neighbors.add(Cell(true))

		cell.evolve()

		assertTrue(cell.isAlive)
	}

	@Test
	fun cellShouldDieWhenOverpopulation() {
		val cell = Cell(true)
		cell.neighbors.add(Cell(true))
		cell.neighbors.add(Cell(true))
		cell.neighbors.add(Cell(true))
		cell.neighbors.add(Cell(true))

		cell.evolve()

		assertFalse(cell.isAlive)
	}

	@Test
	fun cellShouldRebornWhenThreeLivingNeighbors() {
		val cell = Cell(false)
		cell.neighbors.add(Cell(true))
		cell.neighbors.add(Cell(true))
		cell.neighbors.add(Cell(true))

		cell.evolve()

		assertTrue(cell.isAlive)
	}
}