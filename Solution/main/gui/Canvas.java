package gui;

import logic.Cell;
import logic.Universe;

import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;

/**
 * Board with Points that may be expanded (with automatic change of cell
 * number) with mouse event listener
 */
public class Canvas extends JComponent implements MouseInputListener, ComponentListener {

	private final int size = 14;

	private Universe universe;

	public Canvas(int length, int height) {
		addMouseListener(this);
		addComponentListener(this);
		addMouseMotionListener(this);
		setBackground(Color.WHITE);
		setOpaque(true);

		universe = new Universe(height, length);
	}

	// single iteration
	public void iteration() {
		universe.evolve();
		this.repaint();
	}

	// clearing board
	public void clear() {
		universe.reset();
		this.repaint();
	}

	private void initialize(int length, int height) {
		universe = new Universe(height, length);
	}

	//paint background and separators between cells
	@Override
	protected void paintComponent(Graphics g) {
		if (isOpaque()) {
			g.setColor(getBackground());
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
		g.setColor(Color.GRAY);
		drawNetting(g, size);
	}

	// draws the background netting
	private void drawNetting(Graphics g, int gridSpace) {
		Insets insets = getInsets();
		int firstX = insets.left;
		int firstY = insets.top;
		int lastX = this.getWidth() - insets.right;
		int lastY = this.getHeight() - insets.bottom;

		int x = firstX;
		while (x < lastX) {
			g.drawLine(x, firstY, x, lastY);
			x += gridSpace;
		}

		int y = firstY;
		while (y < lastY) {
			g.drawLine(firstX, y, lastX, y);
			y += gridSpace;
		}

		Cell[][] points = universe.getCells();
		for (x = 0; x < points.length; ++x) {
			for (y = 0; y < points[x].length; ++y) {
				Cell cell = points[x][y];
				if (cell.isAlive()) {
					g.setColor(new Color(0x0000ff));
				}
				else {
					g.setColor(new Color(0xffffff));
				}
				g.fillRect((x * size) + 1, (y * size) + 1, (size - 1), (size - 1));
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX() / size;
		int y = e.getY() / size;
		Cell[][] points = universe.getCells();
		if ((x < points.length) && (x > 0) && (y < points[x].length) && (y > 0)) {
			Cell cell = points[x][y];
			if (cell.isAlive()) {
				cell.die();
			}
			else {
				cell.live();
			}
			this.repaint();
		}
	}

	@Override
	public void componentResized(ComponentEvent e) {
		int dlugosc = (this.getWidth() / size) + 1;
		int wysokosc = (this.getHeight() / size) + 1;
		initialize(dlugosc, wysokosc);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX() / size;
		int y = e.getY() / size;
		Cell[][] points = universe.getCells();
		if ((x < points.length) && (x > 0) && (y < points[x].length) && (y > 0)) {
			Cell cell = points[x][y];
			cell.live();
			this.repaint();
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

}
