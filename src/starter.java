import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class starter {
	JPanel cards; // a panel that uses CardLayout
	JPanel card3 = new JPanel(); // Updateable panel
	JPanel card2 = new JPanel();
	JTextArea answer = new JTextArea("Answer...");
	int score = 0;
	Reader getWords;
	Boolean ifSubmitted = false;
	static starter demo = new starter();

	int wordCount;
	int wordCount2;
	int wordCount3;
	JLabel testWord = new JLabel("Score: " + score);
	JRadioButton opt1 = new JRadioButton("opt1");
	JRadioButton opt2 = new JRadioButton("opt2");
	JRadioButton opt3 = new JRadioButton("opt3");
	final JButton submit = new JButton("Submit");
	final JButton next = new JButton("Next Question");
	final JButton add = new JButton("Add");
	String selectedRadiobutton;

	// retrieve from reader
	static ArrayList word;
	static ArrayList synonym1;
	static ArrayList synonym2;
	int correctWord;

	ActionListener handler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getActionCommand() == "Start") {
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, "HOME");
				System.out.println(arg0.getActionCommand());
			} else if (arg0.getActionCommand().equals("Go")) {
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, "GO");
				wordCount = 0;
				wordCount2 = 1;
				wordCount3 = 2;
				Reader.readWords();
				word = Reader.firstWord;
				synonym1 = Reader.secondWord;
				synonym2 = Reader.thirdWord;
				score = 0;
				testWord.setText("Score: " + score + " ||"
						+ (String) word.get(Reader.a[wordCount]));
				next.show();
				submit.show();
				opt1.show();
				opt2.show();
				opt3.show();

				// Randomize correct word
				double randomValue1;
				randomValue1 = 3 * Math.random();
				correctWord = (int) Math.ceil(randomValue1);

				switch (correctWord) {
				case 1:
					opt1.setText((String) synonym1.get(Reader.a[wordCount]));
					opt2.setText((String) word.get(Reader.a[wordCount2]));
					opt3.setText((String) synonym2.get(Reader.a[wordCount3]));
					break;
				case 2:
					opt2.setText((String) synonym1.get(Reader.a[wordCount]));
					opt1.setText((String) word.get(Reader.a[wordCount2]));
					opt3.setText((String) synonym2.get(Reader.a[wordCount3]));
					break;
				case 3:
					opt3.setText((String) synonym2.get(Reader.a[wordCount]));
					opt2.setText((String) synonym1.get(Reader.a[wordCount2]));
					opt1.setText((String) word.get(Reader.a[wordCount3]));
					break;
				}

				card3.revalidate();

			} else {
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, "MWORDS");
				System.out.println(arg0.getActionCommand());
			}

		}
	};

	public void addComponentToPane(Container pane) {
		// Put the JComboBox in a JPanel to get a nicer look.
		JPanel comboBoxPane = new JPanel(); // use FlowLayout
		comboBoxPane.setBackground(Color.ORANGE);
		JButton start = new JButton("Home");
		JButton moreWords = new JButton("More Words");
		JButton go = new JButton("Start test");
		start.setActionCommand("Start");
		moreWords.setActionCommand("mWords");
		moreWords.addActionListener(handler);
		start.addActionListener(handler);
		go.setActionCommand("Go");
		go.addActionListener(handler);
		comboBoxPane.add(moreWords);
		comboBoxPane.add(start);
		comboBoxPane.add(go);

		// Create the "cards".
		// Card1=home, info
		JPanel card1 = new JPanel();
		card1.setLayout(new GridBagLayout());
		card1.add(new JTextArea("To add more words click on 'More Words'. \n"
				+ "in order to start test click on 'Start' \n \n"
				+ "When you add a new word you will also have to enter \n"
				+ "two synonyms"));

		// Card2=Add more words, info

		card2.setLayout(new GridBagLayout());
		final JTextField word1 = new JTextField(20);
		final JTextField word2 = new JTextField(20);
		final JTextField word3 = new JTextField(20);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		card2.add(word1, gbc);
		gbc.gridy = 1;
		card2.add(word2, gbc);
		gbc.gridy = 2;
		card2.add(word3, gbc);
		gbc.gridy = 4;
		card2.add(add, gbc);
		final JTextField done = new JTextField("");
		done.hide();
		gbc.gridy = 5;
		card2.add(done, gbc);

		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (word1.getText() != "" && word2.getText() != ""
						&& word3.getText() != "") {
					String[] toWriter = new String[3];
					toWriter[0] = word1.getText();
					toWriter[1] = word2.getText();
					toWriter[2] = word3.getText();
					if(Writer.writeToFile(toWriter)){
						done.setText("Added: " + " " + toWriter[0] +" " + toWriter[1] +" " + toWriter[2]);
						word1.setText("");
						word2.setText("");
						word3.setText("");
						done.show();
						card2.revalidate();
					};

				}
				;
			}
		});

		// Card3=Test
		card3.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		card3.add(testWord, gbc);

		// Options for card3
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.insets = new Insets(50, -300, 0, 0);
		JPanel options = new JPanel();
		options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));
		final ButtonGroup bgroup = new ButtonGroup();

		ActionListener radioActionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("opt1")) {
					selectedRadiobutton = "opt1";
				} else if (e.getActionCommand().equals("opt2")) {
					selectedRadiobutton = "opt2";
				} else {
					selectedRadiobutton = "opt3";
				}
			}
		};

		opt1.addActionListener(radioActionListener);
		opt1.setActionCommand("opt1");
		opt2.addActionListener(radioActionListener);
		opt2.setActionCommand("opt2");
		opt3.addActionListener(radioActionListener);
		opt3.setActionCommand("opt3");

		bgroup.add(opt1);
		bgroup.add(opt2);
		bgroup.add(opt3);
		options.add(opt1);
		options.add(opt2);
		options.add(opt3);
		card3.add(options, gbc);

		// Controlls cards3
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 0, 0, 0);
		JPanel Controlls = new JPanel();

		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				switch (correctWord) {
				case 1:
					answer.setText("Similar word: "
							+ (String) synonym2.get(Reader.a[wordCount]));
					if (!ifSubmitted && selectedRadiobutton.equals("opt1")) {
						score++;
					}
					opt1.setText("Correct: "
							+ (String) synonym1.get(Reader.a[wordCount]));
					ifSubmitted = true;
					testWord.setText("Score: " + score + ". "
							+ (String) word.get(Reader.a[wordCount]));
					break;
				case 2:
					answer.setText("Similar word: "
							+ (String) synonym2.get(Reader.a[wordCount]));
					if (!ifSubmitted && selectedRadiobutton.equals("opt2")) {
						score++;
					}
					opt2.setText("Correct: "
							+ (String) synonym1.get(Reader.a[wordCount]));
					ifSubmitted = true;
					testWord.setText("Score: " + score + ". "
							+ (String) word.get(Reader.a[wordCount]));

					break;
				case 3:
					answer.setText("Similar word: "
							+ (String) synonym1.get(Reader.a[wordCount]));
					if (!ifSubmitted && selectedRadiobutton.equals("opt3")) {
						score++;
					}
					opt3.setText("Correct: "
							+ (String) synonym2.get(Reader.a[wordCount]));
					ifSubmitted = true;
					testWord.setText("Score: " + score + ". "
							+ (String) word.get(Reader.a[wordCount]));
					break;
				}

				card3.revalidate();

			}
		});
		submit.setActionCommand("Submit");

		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (ifSubmitted) {
					answer.setText("");
					ifSubmitted = false;
					wordCount++;
					wordCount2++;
					wordCount3++;

					if (wordCount == word.size()) {
						next.hide();
						submit.hide();
						opt1.hide();
						opt2.hide();
						opt3.hide();
						testWord.setText("Score: " + score + " out of "
								+ word.size());

						card3.revalidate();
					} else {

						if (wordCount2 == word.size()) {
							wordCount2 = 0;
						} else if (wordCount3 == word.size()) {
							wordCount3 = 0;
						}
						System.out.println(wordCount2);
						// Randomize correct word
						double randomValue1;
						randomValue1 = 3 * Math.random();
						correctWord = (int) Math.ceil(randomValue1);

						switch (correctWord) {
						case 1:
							opt1.setText((String) synonym1
									.get(Reader.a[wordCount]));
							opt2.setText((String) word
									.get(Reader.a[wordCount2]));
							opt3.setText((String) synonym2
									.get(Reader.a[wordCount3]));
							break;
						case 2:
							opt2.setText((String) synonym1
									.get(Reader.a[wordCount]));
							opt1.setText((String) word
									.get(Reader.a[wordCount2]));
							opt3.setText((String) synonym2
									.get(Reader.a[wordCount3]));
							break;
						case 3:
							opt3.setText((String) synonym2
									.get(Reader.a[wordCount]));
							opt2.setText((String) synonym1
									.get(Reader.a[wordCount2]));
							opt1.setText((String) word
									.get(Reader.a[wordCount3]));
							break;
						}

						testWord.setText("Score: " + score + ". "
								+ (String) word.get(Reader.a[wordCount]));

						card3.revalidate();
					}
				}
			}
		});
		next.setActionCommand("Next");
		Controlls.add(submit);
		Controlls.add(next);
		card3.add(Controlls, gbc);

		// Answer
		gbc.gridy = 3;
		card3.add(answer, gbc);

		// Create the panel that contains the "cards".
		cards = new JPanel(new CardLayout());
		cards.add(card1, "HOME");
		cards.add(card2, "MWORDS");
		cards.add(card3, "GO");

		pane.add(comboBoxPane, BorderLayout.PAGE_START);
		pane.add(cards, BorderLayout.CENTER);
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Vocabulary tester");

		// Create and set up the content pane.

		frame.pack();
		frame.setSize(500, 300);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		demo.addComponentToPane(frame.getContentPane());

	}

	public static void main(String[] args) {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();

			}
		});
	}
}