package TweetPredictionPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.TreeSet;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.Font;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JRadioButtonMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.event.MenuListener;
import javax.swing.plaf.basic.BasicButtonListener;
import javax.swing.event.MenuEvent;

public class MainPanel extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPanel frame = new MainPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainPanel() {
		setTitle("Main Menu for \"TweetCollectionText.txt\"");
		//creation of the new TweetCollection object called tweets, read in from file "TweetCollectionText.txt" which contains the results
		//from main
		TweetCollection tweets = new TweetCollection("./TweetPredictionPackage/TweetCollectionText.txt");
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainPanel.class.getResource("/TweetPredictionPackage/twitter-icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane all_tweets_pane = new JScrollPane();
		all_tweets_pane.setBounds(10, 38, 444, 294);
		contentPane.add(all_tweets_pane);
		
		JTextArea txtrTest = new JTextArea();
		txtrTest.setEditable(false);
		txtrTest.setFont(new Font("Monospaced", Font.PLAIN, 14));
		txtrTest.setText(tweets.toString());
		all_tweets_pane.setViewportView(txtrTest);
		
		JLabel TweetCollectionLabel = new JLabel("All Tweets from \"TweetCollectionText.txt\"");
		TweetCollectionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TweetCollectionLabel.setBounds(10, 0, 444, 27);
		contentPane.add(TweetCollectionLabel);
		
		JLabel SelectedTweetsLabel = new JLabel("List of Selected Tweets");
		SelectedTweetsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		SelectedTweetsLabel.setBounds(510, 0, 444, 27);
		contentPane.add(SelectedTweetsLabel);
		
		JScrollPane selected_tweets_pane = new JScrollPane();
		selected_tweets_pane.setBounds(510, 40, 444, 294);
		contentPane.add(selected_tweets_pane);
		
		JTextArea selected_tweets = new JTextArea();
		String selected_tweets_text = "";
		selected_tweets.setText(selected_tweets_text);
		selected_tweets_pane.setViewportView(selected_tweets);
		
		JButton addTweet = new JButton("Add Tweet");
		addTweet.setBounds(510, 345, 125, 23);
		contentPane.add(addTweet);

		
		JButton removeTweet = new JButton("Remove Tweet");
		removeTweet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		removeTweet.setBounds(667, 345, 125, 23);
		contentPane.add(removeTweet);
		
		
	}
}
