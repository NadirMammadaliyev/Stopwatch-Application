import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class Stopwatch implements ActionListener {
	int x, y;
	

	JFrame frame = new JFrame();

	JPanel panel = new JPanel();

	JButton startButton = new JButton(new ImageIcon("icons8_play_32px.png"));
	JButton resetButton = new JButton(new ImageIcon("icons8_reset_32px.png"));

	JLabel timeLabel = new JLabel();
	JLabel lineLabel = new JLabel(new ImageIcon("icons8_horizontal_line_20px_4.png"));
	JLabel exitLabel = new JLabel(new ImageIcon("icons8_delete_20px_1.png"));
	JLabel timerLabel = new JLabel(new ImageIcon("icons8_timer_20px.png"));

	int elapsedTime = 0;
	int seconds = 0;
	int minutes = 0;
	int hours = 0;
	boolean started = false;
	String seconds_string = String.format("%02d", seconds);
	String minutes_string = String.format("%02d", minutes);
	String hours_string = String.format("%02d", hours);

	Timer timer = new Timer(1000, new ActionListener() {

		public void actionPerformed(ActionEvent e) {

			elapsedTime += 1000;
			hours = (elapsedTime / 3600000);
			minutes = (elapsedTime / 60000) % 60;
			seconds = (elapsedTime / 1000) % 60;
			seconds_string = String.format("%02d", seconds);
			minutes_string = String.format("%02d", minutes);
			hours_string = String.format("%02d", hours);
			timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);

		}
	});

	File file = new File("Ayuye.wav");

	Stopwatch() {
		timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
		timeLabel.setBounds(0, 20, 200, 100);
		timeLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
		timeLabel.setBorder(BorderFactory.createBevelBorder(1));
		timeLabel.setOpaque(true);
		timeLabel.setBackground(Color.black);
		timeLabel.setForeground(Color.green);
		timeLabel.setHorizontalAlignment(JTextField.CENTER);

		startButton.setBounds(0, 120, 100, 50);
		// startButton.setFont(new Font("Verdana", Font.PLAIN, 15)); //yazilari aktiv
		// etmek istesen bunlari aktiv et:)
		startButton.setFocusable(false);
		startButton.setBackground(Color.green);
		startButton.addActionListener(this);

		resetButton.setBounds(100, 120, 100, 50);
		// resetButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		resetButton.setFocusable(false);
		resetButton.setBackground(Color.CYAN);
		resetButton.addActionListener(this);

		timerLabel.setBounds(3, 0, 20, 20);
		timerLabel.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
		timerLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
					Clip clip = AudioSystem.getClip();
					clip.open(audioStream);
					clip.start();
					int response = JOptionPane.showConfirmDialog(frame, "Ağıllı ol çox yaşa !", "Message from NadeeR", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION,new ImageIcon("icons8_victory_hand_medium_light_skin_tone_48px.png"));
	                if(response == JOptionPane.OK_OPTION) {
	                	clip.stop();
	                }
				} catch (UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (LineUnavailableException e1) {
					e1.printStackTrace();
				}
				
				/*
				 * countdown = new Countdown (); countdown.Countdown(); frame.dispose();
				 */

			}
		});

		exitLabel.setBounds(180, 0, 20, 20);
		exitLabel.setOpaque(true);
		exitLabel.setBackground(Color.gray);

		lineLabel.setBounds(160, 0, 20, 20);
		lineLabel.setOpaque(true);
		lineLabel.setBackground(Color.gray);
		// lineLabel.setCursor(Cursor.HAND_CURSOR);
		lineLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lineLabel.setBackground(Color.gray);

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lineLabel.setBackground(new java.awt.Color(64, 64, 66));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(JFrame.ICONIFIED);
			}
		});
		exitLabel.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitLabel.setBackground(Color.gray);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				exitLabel.setBackground(Color.red);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		panel.setBounds(0, 0, 200, 20);
		panel.setBackground(Color.gray);
		panel.setLayout(null);
		panel.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				int xx = e.getXOnScreen();
				int yy = e.getYOnScreen();
				frame.setLocation(xx - x, yy - y);

			}
		});
		panel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		panel.add(exitLabel);
		panel.add(lineLabel);
		panel.add(timerLabel);

		frame.add(panel);
		frame.add(startButton);
		frame.add(resetButton);
		frame.add(timeLabel);
		frame.setTitle("Stopwatch");
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200, 170);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			if (started == false) {
				started = true;
				// startButton.setText("STOP");
				startButton.setIcon(new ImageIcon("icons8_pause_32px.png"));

				startButton.setBackground(Color.red);
				start();
			} else {
				started = false;
				// startButton.setText("START");
				startButton.setIcon(new ImageIcon("icons8_play_32px.png"));
				startButton.setBackground(Color.green);
				stop();
			}
		}
		if (e.getSource() == resetButton) {
			started = false;
			// startButton.setText("START");
			startButton.setIcon(new ImageIcon("icons8_play_32px.png"));
			startButton.setBackground(Color.green);
			reset();
		}

	}

	void start() {
		timer.start();
	}

	void stop() {
		timer.stop();
	}

	void reset() {
		timer.stop();
		elapsedTime = 0;
		seconds = 0;
		minutes = 0;
		hours = 0;
		seconds_string = String.format("%02d", seconds);
		minutes_string = String.format("%02d", minutes);
		hours_string = String.format("%02d", hours);
		timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
	}
}
