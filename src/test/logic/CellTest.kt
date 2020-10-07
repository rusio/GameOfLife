package logic

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CellTest {

	@Test
	fun cellShouldDieWhenUnderpopulation() {
		val cell = Cell(State.ALIVE)

		cell.evolve()

		assertFalse(cell.isAlive())
	}

	@Test
	fun cellShouldLiveWhenTwoOrThreeLivingNeighbors() {
		val cell = Cell(State.ALIVE)
		cell.neighbors.add(Cell(State.ALIVE))
		cell.neighbors.add(Cell(State.ALIVE))

		cell.evolve()

		assertTrue(cell.isAlive())
	}

	@Test
	fun cellShouldDieWhenOverpopulation() {
		val cell = Cell(State.ALIVE)
		cell.neighbors.add(Cell(State.ALIVE))
		cell.neighbors.add(Cell(State.ALIVE))
		cell.neighbors.add(Cell(State.ALIVE))
		cell.neighbors.add(Cell(State.ALIVE))

		cell.evolve()

		assertFalse(cell.isAlive())
	}

	@Test
	fun cellShouldRebornWhenThreeLivingNeighbors() {
		val cell = Cell(State.DEAD)
		cell.neighbors.add(Cell(State.ALIVE))
		cell.neighbors.add(Cell(State.ALIVE))
		cell.neighbors.add(Cell(State.ALIVE))

		cell.evolve()

		assertTrue(cell.isAlive())
	}
}