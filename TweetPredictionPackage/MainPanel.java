package TweetPredictionPackage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.JOptionPane;

/* Author: Shandon Probst
 * Class: CSCI 3381 8:00 AM M/W/F
 * Purpose: This program is meant to create a GUI that allows a user to 
 * interact with the data from Project 1
 */

public class MainPanel extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField usernameTextField;
	private JTextField idTextField;

	private String enteredUser;
	private String enteredId;

	private TweetCollection tweets;
	private JTextArea listOfTweets;
	public MainPanel() {
		//creation of the new TweetCollection object called tweets, read in from file "TweetCollectionText.txt" which contains the results
		//from main
		tweets = new TweetCollection("./TweetPredictionPackage/TweetCollectionText.txt");
		
		//Sets title for the GUI
		setTitle("Main Menu for " + tweets.getFileName());
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainPanel.class.getResource("/TweetPredictionPackage/twitter-icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Creates scroll pane for "listOfTweets"
		JScrollPane all_tweets_pane = new JScrollPane();
		all_tweets_pane.setBounds(20, 38, 634, 294);
		contentPane.add(all_tweets_pane);
		
		//Creation of the Text Area "listOfTweets"
		listOfTweets = new JTextArea();
		listOfTweets.setEditable(false);
		listOfTweets.setFont(new Font("Monospaced", Font.PLAIN, 14));
		listOfTweets.setText(tweets.toString());
		all_tweets_pane.setViewportView(listOfTweets);
		
		//JLabel that contains "Tweets from " text + the name of file being used for the Tweets
		JLabel TweetCollectionLabel = new JLabel("Tweets from " + tweets.getFileName());
		TweetCollectionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TweetCollectionLabel.setBounds(20, 24, 634, 15);
		contentPane.add(TweetCollectionLabel);
		
		//Creates a panel that contains the functionalities to filter Tweets in the collection and titles it "Filter Tweets"
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Filter Tweets", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(8, 343, 646, 187);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//************************* START OF COMBO BOX **********************
		//creation of the combobox
		JComboBox<Tweet> dropDownTweets = new JComboBox<Tweet>();
		dropDownTweets.setBounds(217, 81, 419, 20);
		panel.add(dropDownTweets);
		
		dropDownTweets.removeAllItems();
		
		for (Tweet tweet : tweets.getAllTweets()) {
			dropDownTweets.addItem(tweet);
		}
		//************************ END OF COMBO BOX *************************
		
		//******************* START OF FILTER RADIO BUTTONS *****************
		
		JRadioButton noneRadioButton = new JRadioButton("N/A - None");
		buttonGroup.add(noneRadioButton);
		noneRadioButton.setBounds(6, 16, 109, 23);
		panel.add(noneRadioButton);
		
		//sets the textpane to nothing and removes all tweets from combobox
		noneRadioButton.addActionListener((e) -> {
			listOfTweets.setText("");
			dropDownTweets.removeAllItems();
		});
		
		//Radio Button to Show ALL Tweets in TweetCollection object
		JRadioButton allRadioButton = new JRadioButton("N/A - All");
		buttonGroup.add(allRadioButton);
		allRadioButton.setSelected(true);
		allRadioButton.setBounds(6, 49, 109, 23);
		panel.add(allRadioButton);
		
		//finds all type of polarity tweets and sets the text pane to show them while also adding the tweets to the combobox
		allRadioButton.addActionListener((e) -> {
			dropDownTweets.removeAllItems();
			listOfTweets.setText(tweets.toString());
			for (Tweet tweet : tweets.getAllTweets()) {
				dropDownTweets.addItem(tweet);
			}
		});
		
		//Radio Button to Show ALL NEGATIVE Tweets in TweetCollection object
		JRadioButton negativeRadioButton = new JRadioButton("0 - Negative");
		negativeRadioButton.setBounds(6, 82, 109, 23);
		panel.add(negativeRadioButton);
		buttonGroup.add(negativeRadioButton);
		
		//finds negative polarity tweets and sets the text pane to show them while also adding the tweets to the combobox
		negativeRadioButton.addActionListener((e) -> {
			String selected_tweets_text = "";
			dropDownTweets.removeAllItems();
			for (Tweet tweet : tweets.getAllTweets()) {
				if (tweet.getPolarity().equals("0")) {
					selected_tweets_text += tweet.toString();
					dropDownTweets.addItem(tweet);
				}
				listOfTweets.setText(selected_tweets_text);
			}
		});
		
		
		//Radio Button to Show ALL NEUTRAL Tweets in TweetCollection object
		JRadioButton neutralRadioButton = new JRadioButton("2 - Neutral");
		neutralRadioButton.setBounds(6, 115, 109, 23);
		panel.add(neutralRadioButton);
		buttonGroup.add(neutralRadioButton);
		
		//finds neutral polarity tweets and sets the text pane to show them while also adding the tweets to the combobox
		neutralRadioButton.addActionListener((e) -> {
			String selected_tweets_text = "";
			dropDownTweets.removeAllItems();
			for (Tweet tweet : tweets.getAllTweets()) {
				if (tweet.getPolarity().equals("2")) {
					selected_tweets_text += tweet.toString();
					dropDownTweets.addItem(tweet);
				}
				listOfTweets.setText(selected_tweets_text);
			}
		});
		
		//Radio Button to Show ALL POSITIVE Tweets in TweetCollection object
		JRadioButton positiveRadioButton = new JRadioButton("4 - Positive");
		positiveRadioButton.setBounds(6, 148, 109, 23);
		panel.add(positiveRadioButton);
		buttonGroup.add(positiveRadioButton);
		
		//finds positive polarity tweets and sets the text pane to show them while also adding the tweets to the combobox
		positiveRadioButton.addActionListener((e) -> {
			String selected_tweets_text = "";
			dropDownTweets.removeAllItems();
			for (Tweet tweet : tweets.getAllTweets()) {
				if (tweet.getPolarity().equals("4")) {
					selected_tweets_text += tweet;
					dropDownTweets.addItem(tweet);
				}
				listOfTweets.setText(selected_tweets_text);
			}
		});
		//******************** END OF FILTER RADIO BUTTONS ******************
		
		//*********************** START OF TWEET LOOKUP *********************
		//Label to show username: next to textfield
		JLabel labelUsername = new JLabel("Username:\r\n");
		labelUsername.setBounds(153, 20, 62, 14);
		panel.add(labelUsername);
		
		//Textfield to receive username
		usernameTextField = new JTextField();
		usernameTextField.setBounds(217, 17, 175, 20);
		panel.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		//Label to show id: next to textfield
		JLabel labelId = new JLabel("Tweet ID:");
		labelId.setBounds(402, 20, 62, 14);
		panel.add(labelId);
		
		//Textfielld to receive id
		idTextField = new JTextField();
		idTextField.setBounds(461, 17, 175, 20);
		panel.add(idTextField);
		idTextField.setColumns(10);
		idTextField.setText(enteredId);
		
		//Label to show tweet: next to tweet drop down box
		JLabel labelSelectedTweets = new JLabel("Tweet:");
		labelSelectedTweets.setBounds(171, 84, 50, 14);
		panel.add(labelSelectedTweets);
		
		//Deletes selected tweet from the combobox
		JButton deleteTweetButton = new JButton("Delete Tweet");
		deleteTweetButton.setForeground(Color.RED);
		deleteTweetButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		deleteTweetButton.setBounds(217, 112, 419, 23);
		panel.add(deleteTweetButton);
		
		deleteTweetButton.addActionListener((e) -> {
			if (dropDownTweets.getItemCount()!=0)
			{
				for (Tweet tweet : tweets.getAllTweets()) {
					if (dropDownTweets.getSelectedItem().equals(tweet)) {
						dropDownTweets.removeItem(tweet);
						tweets.removeTweet(tweet);
						break;
					}
				}
			} else
				JOptionPane.showMessageDialog(contentPane, "No entries exist in the menu.", "No Entries Exist", JOptionPane.ERROR_MESSAGE);
		});
		
		//Prompts for a new tweet to be added
		JButton addTweetButton = new JButton("Add Tweet");
		addTweetButton.setForeground(new Color(34, 139, 34));
		addTweetButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		addTweetButton.setBounds(217, 146, 419, 23);
		panel.add(addTweetButton);
		
		addTweetButton.addActionListener((e) -> {
			String tweetInfo = "";
			tweetInfo = JOptionPane.showInputDialog(contentPane, "Please enter a Tweet to Add: (Example: \"0,123,testuser,testcontent\")", "Add Tweet", 1);
			try {
				Tweet tweetToAdd = new Tweet(tweetInfo);
				tweets.addTweet(tweetToAdd);
				JOptionPane.showMessageDialog(contentPane,  "Tweet has been added." , "Tweet Added", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(contentPane, "Could not enter Tweet.", "Tweet Not Entered", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		//Refreshes the tweets textfield to show all tweets with the given id and/or username
		JButton getTweetsButton = new JButton("Get Tweet(s)");
		getTweetsButton.setBounds(217, 47, 175, 23);
		panel.add(getTweetsButton);
		
		getTweetsButton.addActionListener((e) -> {
			String textToReturn = "";
			dropDownTweets.removeAllItems();
			boolean found = false;
			
			//while tweet exists in TweetCollection object, if BOTH the tweet username is equal to the username in the text field
			//AND the tweet id is equal to the id in the text field, OR if the tweet id is equal to the id in the text field AND the
			//tweet username is not entered in the text field, OR if the tweet username is equal to the username in the text field AND
			//the tweet id is not entered in the text field, THEN get the tweets with the entered information
			for (Tweet tweet : tweets.getAllTweets()) {
				if ((tweet.getTweetUserName().equals(usernameTextField.getText()) && tweet.getTweetId().equals(idTextField.getText())) || 
						(tweet.getTweetId().equals(idTextField.getText()) && usernameTextField.getText().equals("")) || 
						(tweet.getTweetUserName().equals(usernameTextField.getText()) && idTextField.getText().equals(""))) {
					textToReturn += tweet.toString();
					dropDownTweets.addItem(tweet);	
					listOfTweets.setText(textToReturn);
					found = true;
				} else if (usernameTextField.getText().equals("") && idTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(contentPane, "No entries exist in the fields.", "No Field Entries Exist", JOptionPane.ERROR_MESSAGE);
					break;
				}
			}
			
			if (!(usernameTextField.getText().equals("") && idTextField.getText().equals("")) && (found == false)) {
				JOptionPane.showMessageDialog(contentPane, "No such entries exist.", "No Such Entries Exist", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		//Resets the username and id text fields
		JButton resetFieldsButton = new JButton("Reset Fields & Refresh");
		resetFieldsButton.setBounds(461, 47, 175, 23);
		panel.add(resetFieldsButton);
		
		resetFieldsButton.addActionListener((e) -> {
			usernameTextField.setText("");
			idTextField.setText("");
			
			String selected_tweets_text = "";
			dropDownTweets.removeAllItems();
			for (Tweet tweet : tweets.getAllTweets()) {
				selected_tweets_text += tweet.toString();
				listOfTweets.setText(selected_tweets_text);
				dropDownTweets.addItem(tweet);
				allRadioButton.setSelected(true);
			
			}
			JOptionPane.showMessageDialog(contentPane,  "Entry fields have been reset and the Tweets list has been refreshed." , "Field Entries Reset Tweets Refreshed", JOptionPane.INFORMATION_MESSAGE);
		});
		
		//************************ START OF MENU BAR ************************
		JMenuBar tweetMenuBar = new JMenuBar();
		tweetMenuBar.setBounds(-4, 0, 668, 22);
		contentPane.add(tweetMenuBar);
		
		JMenu fileMenu = new JMenu("File");
		tweetMenuBar.add(fileMenu);
		
		JMenuItem fileSaveAs = new JMenuItem("Save As...");
		fileMenu.add(fileSaveAs);
		fileSaveAs.addActionListener((e) -> {
			String newFileName = "";
			try {
				newFileName = JOptionPane.showInputDialog(contentPane, "Enter the Name of the File to Save (\"example.txt\"): ", "Save As", 1);
				if (!newFileName.equals("")) {
					try {
						tweets.writeFile("./TweetPredictionPackage/" + newFileName);
						JOptionPane.showMessageDialog(contentPane,  "File has been saved as \"" + newFileName + "\"" , "Tweet Removed", JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception exception) {
						JOptionPane.showMessageDialog(contentPane, "Invalid file name was entered.", "Invalid File Name", JOptionPane.ERROR_MESSAGE);
					}
				} 
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(contentPane, "Save As was cancalled.", "Save As Cancelled", JOptionPane.INFORMATION_MESSAGE);
			}

		});
		
		//Menu button for Edit
		JMenu editMenu = new JMenu("Edit");
		tweetMenuBar.add(editMenu);
		
		//Menu item for Edit
		JMenu editRemoveTweetMenu = new JMenu("Remove Tweet");
		editMenu.add(editRemoveTweetMenu);
		
		//Menu item for Edit
		JMenuItem removeByUsername = new JMenuItem("By Username");
		editRemoveTweetMenu.add(removeByUsername);
		
		//Listener that removes tweet based on username
		removeByUsername.addActionListener((e) -> {
			int error_code = 0;
			//tries to take a new entry for username
			try {
				enteredUser = JOptionPane.showInputDialog(contentPane, "Please enter a Tweet Username: ", "Remove Tweet By Username", 1);
				for (Tweet tweet : tweets.getAllTweets()) {
					if (tweet.getTweetUserName().equals(enteredUser)) {
						dropDownTweets.removeItem(tweet);
						tweets.removeTweetById(enteredUser);
						error_code = 0;
						JOptionPane.showMessageDialog(contentPane,  "Tweet has been removed." , "Tweet Removed", JOptionPane.INFORMATION_MESSAGE);
						break;
					} else if (enteredUser.equals(""))
						error_code = 1;
					else
						error_code = 2;
				}
				//exception caught is the cancel button is pressed
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(contentPane,  "Remove was cancelled." , "Remove Cancelled", JOptionPane.INFORMATION_MESSAGE);
			}
			
			if (error_code == 1)
				JOptionPane.showMessageDialog(contentPane, "No entry was entered.", "No Value Entered", JOptionPane.ERROR_MESSAGE);
			else if (error_code == 2)
				JOptionPane.showMessageDialog(contentPane, "No such entry exists.", "No Such Entry Exist", JOptionPane.ERROR_MESSAGE);

		});
		
		//Menu items for Edit
		JMenuItem removeTweetById = new JMenuItem("By ID");
		editRemoveTweetMenu.add(removeTweetById);
		
		removeTweetById.addActionListener((e) -> {
			int error_code = 0;
			//tries to take a new entry for id
			try {
				enteredId = JOptionPane.showInputDialog(contentPane, "Please enter a Tweet ID: ", "Remove Tweet By ID", 1);
				for (Tweet tweet : tweets.getAllTweets()) {
					if (tweet.getTweetId().equals(enteredId)) {
						dropDownTweets.removeItem(tweet);
						tweets.removeTweetById(enteredId);
						error_code = 0;
						JOptionPane.showMessageDialog(contentPane,  "Tweet has been removed." , "Tweet Removed", JOptionPane.INFORMATION_MESSAGE);
						break;
					} else if (enteredId.equals(""))
						error_code = 1;
					else
						error_code = 2;
				}
				//exception caught is the cancel button is pressed
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(contentPane,  "Remove was cancelled." , "Remove Cancelled", JOptionPane.INFORMATION_MESSAGE);
			}
			
			if (error_code == 1)
				JOptionPane.showMessageDialog(contentPane, "No value was entered.", "No Value Entered", JOptionPane.ERROR_MESSAGE);
			else if (error_code == 2)
				JOptionPane.showMessageDialog(contentPane, "No such entry exists.", "No Such Entry", JOptionPane.ERROR_MESSAGE);
		});
		
		//adds view menu to menu bar
		JMenu viewMenu = new JMenu("View");
		tweetMenuBar.add(viewMenu);
		
		//adds filter menu to view menu
		JMenu viewFilter = new JMenu("Filter");
		viewMenu.add(viewFilter);
		
		//adds menu item to filter menu
		JMenuItem filterUsername = new JMenuItem("By Username");
		viewFilter.add(filterUsername);
		
		//filters the current tweets to only show what username was entered
		filterUsername.addActionListener((e) -> {
			int error_code = -1;
			String textToReturn = "";
			//tries to find tweet with entered username
			try {
				enteredUser = JOptionPane.showInputDialog(contentPane, "Please enter a Username: ", "Filter Tweet By Username", 1);
				for (Tweet tweet : tweets.getAllTweets()) {
					if (tweet.getTweetUserName().equals(enteredUser)) {
						dropDownTweets.removeAllItems();
						dropDownTweets.addItem(tweet);
						textToReturn += tweet;
						error_code = 0;
						break;
					} else if (enteredUser.equals(""))
						error_code = 1;
					else
						error_code = 2;
				}
				//exception caught if cancelled
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(contentPane,  "Filter was cancelled." , "Filter Cancelled", JOptionPane.INFORMATION_MESSAGE);
			}
			
			if (error_code == 0) {
				listOfTweets.setText(textToReturn);
				JOptionPane.showMessageDialog(contentPane,  "Tweets has been filtered." , "Tweets Filtered", JOptionPane.INFORMATION_MESSAGE);
			}
			else if (error_code == 1)
				JOptionPane.showMessageDialog(contentPane, "No entry was entered.", "No Entry Entered", JOptionPane.ERROR_MESSAGE);
			else if (error_code == 2)
				JOptionPane.showMessageDialog(contentPane, "No such entry exists.", "No Such Entry", JOptionPane.ERROR_MESSAGE);
			
		});
		
		//menu item for filter menu
		JMenuItem filterID = new JMenuItem("By ID");
		viewFilter.add(filterID);
		
		//filters the current tweets to only show what id was entered
		filterID.addActionListener((e) -> {
			int error_code = -1;
			String textToReturn = "";
			//tries to find tweet with entered id
			try {
				enteredId = JOptionPane.showInputDialog(contentPane, "Please enter a Tweet ID: ", "Filter Tweets By ID", 1);
				for (Tweet tweet : tweets.getAllTweets()) {
					if (tweet.getTweetId().equals(enteredId)) {
						dropDownTweets.removeAllItems();
						dropDownTweets.addItem(tweet);
						textToReturn += tweet;
						error_code = 0;
						break;
					} else if (enteredId.equals(""))
						error_code = 1;
					else
						error_code = 2;
				}
				//exception caught if cancelled
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(contentPane,  "Filter was cancelled." , "Filter Cancelled", JOptionPane.INFORMATION_MESSAGE);

			}
			
			if (error_code == 0) {
				listOfTweets.setText(textToReturn);
				JOptionPane.showMessageDialog(contentPane,  "Tweets has been filtered." , "Tweets Filtered", JOptionPane.INFORMATION_MESSAGE);
			}
			else if (error_code == 1)
				JOptionPane.showMessageDialog(contentPane, "No value was entered.", "No Value Entered", JOptionPane.ERROR_MESSAGE);
			else if (error_code == 2)
				JOptionPane.showMessageDialog(contentPane, "No such entry exists.", "No Such Entry", JOptionPane.ERROR_MESSAGE);
			
		});
		//*************************** END OF MENU BAR ***************************
	}
}
