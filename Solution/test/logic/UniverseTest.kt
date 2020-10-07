package logic

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UniverseTest {

	@Test
	fun universeShouldBeGeneratedWithGivenSize() {
		val universe = Universe(height = 3, width = 3)
		assertEquals(9, universe.cells.flatten().size)
	}

	@Test
	fun universeShouldSetupCorrectNeighborRelations() {
		val cells = Universe(height = 3, width = 3).cells

		assertTrue(cells[0][0].hasNeighbors(cells[0][1], cells[1][0], cells[1][1]))
		assertTrue(cells[0][1].hasNeighbors(cells[0][0], cells[0][2], cells[1][0], cells[1][1], cells[1][2]))
		assertTrue(cells[0][2].hasNeighbors(cells[0][1], cells[1][1], cells[1][2]))
		assertTrue(cells[1][0].hasNeighbors(cells[0][0], cells[0][1], cells[1][1], cells[2][0], cells[2][1]))
		assertTrue(cells[1][1].hasNeighbors(cells[0][0],
		                                    cells[0][1],
		                                    cells[0][2],
		                                    cells[1][0],
		                                    cells[1][2],
		                                    cells[2][0],
		                                    cells[2][1],
		                                    cells[2][2]))
		assertTrue(cells[1][2].hasNeighbors(cells[0][1], cells[0][2], cells[1][1], cells[2][1], cells[2][2]))
		assertTrue(cells[2][0].hasNeighbors(cells[1][0], cells[1][1], cells[2][1]))
		assertTrue(cells[2][1].hasNeighbors(cells[1][0], cells[1][1], cells[1][2], cells[2][0], cells[2][2]))
		assertTrue(cells[2][2].hasNeighbors(cells[1][1], cells[1][2], cells[2][1]))
	}

	@Test
	fun universeShouldEvolveWithUnderpopulation() {
		val universe = Universe(height = 3, width = 3)
		universe.cellAt(1, 1).live()
		universe.cellAt(2, 2).live()

		universe.evolve()

		assertFalse(universe.cellAt(1, 1).isAlive())
		assertFalse(universe.cellAt(2, 2).isAlive())
	}

	@Test
	fun universeShouldEvolve() {
		val universe = Universe(height = 3, width = 3)
		universe.cellAt(1, 1).live()
		universe.cellAt(2, 1).live()
		universe.cellAt(2, 2).live()

		universe.evolve()

		assertTrue(universe.cellAt(1, 1).isAlive())
		assertTrue(universe.cellAt(2, 1).isAlive())
		assertTrue(universe.cellAt(2, 2).isAlive())
	}

	@Test
	fun universeShouldEvolveWithOverpopulation() {
		val universe = Universe(height = 3, width = 3)
		universe.cellAt(0, 0).live()
		universe.cellAt(0, 2).live()
		universe.cellAt(1, 1).live()
		universe.cellAt(2, 0).live()
		universe.cellAt(2, 2).live()

		universe.evolve()

		assertFalse(universe.cellAt(0, 0).isAlive())
		assertFalse(universe.cellAt(0, 2).isAlive())
		assertFalse(universe.cellAt(1, 1).isAlive())
		assertFalse(universe.cellAt(2, 0).isAlive())
		assertFalse(universe.cellAt(2, 2).isAlive())
	}

	@Test
	fun universeShouldEvolveWithRebirth() {
		val universe = Universe(height = 3, width = 3)
		universe.cellAt(0, 0).live()
		universe.cellAt(0, 2).live()
		universe.cellAt(2, 0).live()

		universe.evolve()

		assertFalse(universe.cellAt(0, 0).isAlive())
		assertFalse(universe.cellAt(0, 2).isAlive())
		assertFalse(universe.cellAt(2, 0).isAlive())

		assertTrue(universe.cellAt(1, 1).isAlive())
	}
}