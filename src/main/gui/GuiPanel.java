package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class containing GUI: board + buttons
 */
public class GuiPanel extends JPanel implements ActionListener, ChangeListener {

	private static final int INITIAL_TIMER_DELAY = 100;
	private static final int MAX_TIMER_DELAY = 500;

	private final JFrame mainFrame;
	private final Timer clockTimer;

	private JButton startButton;
	private JButton clearButton;
	private JSlider speedSlider;

	private LifeBoard lifeBoard;

	private int iterationNumber = 0;
	private boolean isRunning = false;

	public GuiPanel(JFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.clockTimer = new Timer(INITIAL_TIMER_DELAY, this);
	}

	/**
	 * @param container to which GUI and board is added
	 */
	public void initialize(Container container) {
		container.setLayout(new BorderLayout());
		container.setSize(new Dimension(1024, 768));

		JPanel buttonPanel = new JPanel();

		startButton = new JButton("Start");
		startButton.setActionCommand("Start");
		startButton.setToolTipText("Starts the clock");
		startButton.addActionListener(this);

		clearButton = new JButton("Clear");
		clearButton.setActionCommand("clear");
		clearButton.setToolTipText("Clears the board");
		clearButton.addActionListener(this);

		speedSlider = new JSlider();
		speedSlider.setMinimum(0);
		speedSlider.setMaximum(MAX_TIMER_DELAY);
		speedSlider.setToolTipText("Time speed");
		speedSlider.addChangeListener(this);
		speedSlider.setValue(MAX_TIMER_DELAY - clockTimer.getDelay());

		buttonPanel.add(startButton);
		buttonPanel.add(clearButton);
		buttonPanel.add(speedSlider);

		lifeBoard = new LifeBoard(1024, 768 - buttonPanel.getHeight());
		container.add(lifeBoard, BorderLayout.CENTER);
		container.add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * handles clicking on each button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(clockTimer)) {
			iterationNumber++;
			mainFrame.setTitle("Game of Life (" + Integer.toString(iterationNumber) + " iteration)");
			lifeBoard.iteration();
		}
		else {
			String command = e.getActionCommand();
			if (command.equals("Start")) {
				if (!isRunning) {
					clockTimer.start();
					startButton.setText("Pause");
				}
				else {
					clockTimer.stop();
					startButton.setText("Start");
				}
				isRunning = !isRunning;
				clearButton.setEnabled(true);
			}
			else if (command.equals("clear")) {
				iterationNumber = 0;
				clockTimer.stop();
				startButton.setEnabled(true);
				lifeBoard.clear();
				mainFrame.setTitle("Cellular Automata Toolbox");
			}
		}
	}

	/**
	 * slider to control simulation speed
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		clockTimer.setDelay(MAX_TIMER_DELAY - speedSlider.getValue());
	}
}
