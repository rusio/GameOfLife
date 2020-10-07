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
public class GUI extends JPanel implements ActionListener, ChangeListener {

	private Timer timer;
	private Canvas canvas;

	private JButton start;
	private JButton clear;
	private JSlider pred;
	private JFrame frame;

	private final int maxDelay = 500;
	private final int initDelay = 100;

	private int iterNum = 0;
	private boolean running = false;

	public GUI(JFrame jf) {
		frame = jf;
		timer = new Timer(initDelay, this);
		timer.stop();
	}

	/**
	 * @param container to which GUI and board is added
	 */
	public void initialize(Container container) {
		container.setLayout(new BorderLayout());
		container.setSize(new Dimension(1024, 768));

		JPanel buttonPanel = new JPanel();

		start = new JButton("Start");
		start.setActionCommand("Start");
		start.setToolTipText("Starts clock");
		start.addActionListener(this);

		clear = new JButton("Clear");
		clear.setActionCommand("clear");
		clear.setToolTipText("Clears the board");
		clear.addActionListener(this);

		pred = new JSlider();
		pred.setMinimum(0);
		pred.setMaximum(maxDelay);
		pred.setToolTipText("Time speed");
		pred.addChangeListener(this);
		pred.setValue(maxDelay - timer.getDelay());

		buttonPanel.add(start);
		buttonPanel.add(clear);
		buttonPanel.add(pred);

		canvas = new Canvas(1024, 768 - buttonPanel.getHeight());
		container.add(canvas, BorderLayout.CENTER);
		container.add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * handles clicking on each button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(timer)) {
			iterNum++;
			frame.setTitle("Game of Life (" + Integer.toString(iterNum) + " iteration)");
			canvas.iteration();
		}
		else {
			String command = e.getActionCommand();
			if (command.equals("Start")) {
				if (!running) {
					timer.start();
					start.setText("Pause");
				}
				else {
					timer.stop();
					start.setText("Start");
				}
				running = !running;
				clear.setEnabled(true);

			}
			else if (command.equals("clear")) {
				iterNum = 0;
				timer.stop();
				start.setEnabled(true);
				canvas.clear();
				frame.setTitle("Cellular Automata Toolbox");
			}

		}
	}

	/**
	 * slider to control simulation speed
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		timer.setDelay(maxDelay - pred.getValue());
	}
}
