import gui.GuiPanel;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Program extends JFrame {

	private final GuiPanel guiPanel;

	public Program() {
		setTitle("Game of Life");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		guiPanel = new GuiPanel(this);
		guiPanel.initialize(getContentPane());

		setSize(800, 600);
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(Program::new);
	}
}
