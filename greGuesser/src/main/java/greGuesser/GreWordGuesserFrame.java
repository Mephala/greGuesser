package greGuesser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.FileUtils;

@SuppressWarnings("serial")
public class GreWordGuesserFrame extends JFrame {

	private JPanel contentPane;
	private Properties prop;
	private String currentQuestion;
	final JButton btnNewButton;
	final JButton btnNewButton_1;
	final JButton btnNewButton_2;
	final JButton btnNewButton_3;
	final JButton btnNewButton_4;
	final JButton btnNewButton_5;
	final JPanel panel;
	final JLabel lblNewLabel;
	ImageIcon correctIcon;
	ImageIcon wrongIcon;
	private JLabel correctLabel;
	private JLabel lblCevaplanan;
	private JLabel lblNewLabel_3;
	private JLabel wrongLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GreWordGuesserFrame frame = new GreWordGuesserFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public GreWordGuesserFrame() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			System.out.println("PisError.");
		} catch (InstantiationException e1) {
			System.out.println("PisError.");
		} catch (IllegalAccessException e1) {
			System.out.println("PisError.");
		} catch (UnsupportedLookAndFeelException e1) {
			System.out.println("PisError.");
		}
		prop = new Properties();

		try {

			// InputStream inputStream =
			// getClass().getClassLoader().getResourceAsStream(propFileName);
			InputStream inputStream = getWordsAsInputStream();
			prop.load(inputStream);
			correctIcon = createImageIcon("/correctAdelaide.png", "a pretty but meaningless splat");
			wrongIcon = createImageIcon("/wrongAdelaide.jpg", "a pretty but meaningless splat");
		} catch (FileNotFoundException e1) {
			printError(e1);
		} catch (IOException e1) {
			printError(e1);
		}

		// load a properties file
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel();
		panel.setForeground(new Color(255, 0, 0));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		lblNewLabel = new JLabel("Click Next To Start...");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblNewLabel.setBounds(133, 52, 450, 52);
		panel.add(lblNewLabel);

		btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				elaborateAnswerAndCreateNextQuestion(e);
			}
		});
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(108, 115, 487, 23);
		panel.add(btnNewButton);

		btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				elaborateAnswerAndCreateNextQuestion(e);
			}
		});
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setBounds(108, 158, 487, 23);
		panel.add(btnNewButton_1);

		btnNewButton_2 = new JButton("New button");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				elaborateAnswerAndCreateNextQuestion(e);
			}
		});
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.setBounds(108, 192, 487, 23);
		panel.add(btnNewButton_2);

		btnNewButton_3 = new JButton("New button");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				elaborateAnswerAndCreateNextQuestion(e);
			}
		});
		btnNewButton_3.setEnabled(false);
		btnNewButton_3.setBounds(108, 226, 487, 23);
		panel.add(btnNewButton_3);

		btnNewButton_4 = new JButton("New button");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				elaborateAnswerAndCreateNextQuestion(e);
			}
		});
		btnNewButton_4.setEnabled(false);
		btnNewButton_4.setBounds(108, 260, 487, 23);
		panel.add(btnNewButton_4);

		btnNewButton_5 = new JButton("New button");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				elaborateAnswerAndCreateNextQuestion(e);
			}
		});
		btnNewButton_5.setEnabled(false);
		btnNewButton_5.setBounds(108, 294, 487, 23);
		panel.add(btnNewButton_5);

		JButton btnNewButton_6 = new JButton("Next");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				designNextQuestion();
			}
		});
		btnNewButton_6.setBounds(317, 358, 91, 23);
		panel.add(btnNewButton_6);

		wrongLabel = new JLabel("0");
		wrongLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		wrongLabel.setBounds(46, 11, 46, 33);
		wrongLabel.setForeground(Color.RED);
		panel.add(wrongLabel);

		correctLabel = new JLabel("0");
		correctLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		correctLabel.setBounds(607, 22, 46, 22);
		correctLabel.setForeground(Color.GREEN);
		panel.add(correctLabel);

		lblCevaplanan = new JLabel("Cevaplanan:");
		lblCevaplanan.setBounds(217, 27, 86, 14);
		panel.add(lblCevaplanan);

		lblNewLabel_3 = new JLabel("0");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(344, 28, 46, 14);
		panel.add(lblNewLabel_3);
	}

	private InputStream getWordsAsInputStream() {
		final String propFileName = "greWordList.properties";
		File propFile = new File(propFileName);
		if (propFile.exists()) {
			try {
				return new FileInputStream(propFile);
			} catch (FileNotFoundException e) {
				printError(e);
				return null;
			}
		} else {
			createOuterFile(propFileName);
			return getClass().getClassLoader().getResourceAsStream(propFileName);
		}
	}

	private void createOuterFile(final String propFileName) {
		Thread outerFileThread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Properties props = new Properties();
					props.load(GreWordGuesserFrame.class.getClassLoader().getResourceAsStream(propFileName));
					Set<Object> keys = props.keySet();
					File file = new File(propFileName);
					file.createNewFile();
					List<String> propFileList = new ArrayList<>();
					for (Object object : keys) {
						String key = (String) object;
						String value = props.getProperty(key);
						propFileList.add(key + " = " + value);
					}
					Collections.sort(propFileList);
					FileUtils.writeLines(file, propFileList);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Yeni prop dosyasi olusturulamadi : (((" + e.getMessage(), "Devasa bir hata", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		outerFileThread.start();
	}

	private void printError(Exception e1) {
		JOptionPane.showMessageDialog(null, e1.getMessage(), "HATA!", JOptionPane.ERROR_MESSAGE);

	}

	protected boolean checkAnswer(ActionEvent e) {
		JButton clickedButton = (JButton) e.getSource();
		String potentialAnswer = clickedButton.getText();
		if (prop.getProperty(currentQuestion).equals(potentialAnswer))
			return true;
		else
			return false;
	}

	private void designNextQuestion() {
		if (prop.size() == 0) {
			JOptionPane.showMessageDialog(null,
					"Tebrikler, testi tamamladin! Dogru :" + correctLabel.getText() + ", Yanlis: " + wrongLabel.getText() + " , Cevaplanan :" + lblCevaplanan.getText(), "BITTI!",
					JOptionPane.INFORMATION_MESSAGE, correctIcon);
			System.exit(0);
		}
		if (btnNewButton.isEnabled() == false)
			btnNewButton.setEnabled(true);
		if (btnNewButton_1.isEnabled() == false)
			btnNewButton_1.setEnabled(true);
		if (btnNewButton_2.isEnabled() == false)
			btnNewButton_2.setEnabled(true);
		if (btnNewButton_3.isEnabled() == false)
			btnNewButton_3.setEnabled(true);
		if (btnNewButton_4.isEnabled() == false)
			btnNewButton_4.setEnabled(true);
		if (btnNewButton_5.isEnabled() == false)
			btnNewButton_5.setEnabled(true);
		Set<Object> keySet = prop.keySet();
		List<Object> keyList = new ArrayList<>(keySet);
		Random r = new Random();
		int size = keySet.size();
		int qNumber = r.nextInt(size);
		int wrongAnswer1 = r.nextInt(size);
		int wrongAnswer2 = r.nextInt(size);
		int wrongAnswer3 = r.nextInt(size);
		int wrongAnswer4 = r.nextInt(size);
		int wrongAnswer5 = r.nextInt(size);
		String question = (String) keyList.get(qNumber);
		lblNewLabel.setText(question + " ?...");
		currentQuestion = question;
		String answer = prop.getProperty(question);
		String wrongAnswerString1 = prop.getProperty((String) keyList.get(wrongAnswer1));
		String wrongAnswerString2 = prop.getProperty((String) keyList.get(wrongAnswer2));
		String wrongAnswerString3 = prop.getProperty((String) keyList.get(wrongAnswer3));
		String wrongAnswerString4 = prop.getProperty((String) keyList.get(wrongAnswer4));
		String wrongAnswerString5 = prop.getProperty((String) keyList.get(wrongAnswer5));
		List<Integer> allMultipleChoices = new ArrayList<>();
		allMultipleChoices.add(0);
		allMultipleChoices.add(1);
		allMultipleChoices.add(2);
		allMultipleChoices.add(3);
		allMultipleChoices.add(4);
		allMultipleChoices.add(5);
		Collections.shuffle(allMultipleChoices);
		Integer choiseNumber = allMultipleChoices.get(0);
		switch (choiseNumber) {
		case 0:
			btnNewButton.setText(answer);
			btnNewButton_1.setText(wrongAnswerString1);
			btnNewButton_2.setText(wrongAnswerString2);
			btnNewButton_3.setText(wrongAnswerString3);
			btnNewButton_4.setText(wrongAnswerString4);
			btnNewButton_5.setText(wrongAnswerString5);
			break;
		case 1:
			btnNewButton.setText(wrongAnswerString4);
			btnNewButton_1.setText(wrongAnswerString1);
			btnNewButton_2.setText(wrongAnswerString2);
			btnNewButton_3.setText(wrongAnswerString3);
			btnNewButton_4.setText(answer);
			btnNewButton_5.setText(wrongAnswerString5);
			break;
		case 2:
			btnNewButton.setText(wrongAnswerString1);
			btnNewButton_1.setText(answer);
			btnNewButton_2.setText(wrongAnswerString2);
			btnNewButton_3.setText(wrongAnswerString3);
			btnNewButton_4.setText(wrongAnswerString4);
			btnNewButton_5.setText(wrongAnswerString5);
			break;
		case 3:
			btnNewButton.setText(wrongAnswerString5);
			btnNewButton_1.setText(wrongAnswerString1);
			btnNewButton_2.setText(wrongAnswerString2);
			btnNewButton_3.setText(wrongAnswerString3);
			btnNewButton_4.setText(wrongAnswerString4);
			btnNewButton_5.setText(answer);
			break;
		case 4:
			btnNewButton.setText(wrongAnswerString3);
			btnNewButton_1.setText(wrongAnswerString1);
			btnNewButton_2.setText(wrongAnswerString2);
			btnNewButton_3.setText(answer);
			btnNewButton_4.setText(wrongAnswerString4);
			btnNewButton_5.setText(wrongAnswerString5);
			break;
		case 5:
			btnNewButton.setText(wrongAnswerString2);
			btnNewButton_1.setText(wrongAnswerString1);
			btnNewButton_2.setText(answer);
			btnNewButton_3.setText(wrongAnswerString3);
			btnNewButton_4.setText(wrongAnswerString4);
			btnNewButton_5.setText(wrongAnswerString5);
			break;
		default:
			break;
		}
		panel.repaint();
	}

	private void elaborateAnswerAndCreateNextQuestion(ActionEvent e) {
		elaborateAnswer(e);
		removeQuestionFromList();
		designNextQuestion();
	}

	private void removeQuestionFromList() {
		prop.remove(currentQuestion);
	}

	private void elaborateAnswer(ActionEvent e) {
		boolean isAnswerCorrect = checkAnswer(e);
		if (isAnswerCorrect) {
			JOptionPane.showMessageDialog(null, "Helal olsun sana", "Afferin Kankuşuma", JOptionPane.INFORMATION_MESSAGE, correctIcon);
			increaseLabel(correctLabel);
		} else {
			JOptionPane.showMessageDialog(null, "Sıçtık... Dogru cevap: " + prop.getProperty(currentQuestion), "Yakışmadı...", JOptionPane.ERROR_MESSAGE, wrongIcon);
			increaseLabel(wrongLabel);
		}
		increaseLabel(lblNewLabel_3);
	}

	private void increaseLabel(JLabel label) {
		Integer currentAmount = Integer.parseInt(label.getText());
		currentAmount++;
		label.setText(currentAmount.toString());
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}
