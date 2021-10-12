package TweetPredictionPackage;

/* Code Citation #1
 * Code Used: readFile() method, writeFile() methods, and doWrite() method
 * Author: Mark Doderer
 * Source: classroster/Roster.java
 * Purpose: file read and write
 * 
 * Code Citation #2
 * Created: 9 years ago
 * Files Used: negative-words.txt, positive-words.txt
 * Author: mkulakowski2
 * Source: https://gist.github.com/mkulakowski2/4289441, https://gist.github.com/mkulakowski2/4289437
 * Purpose: stores a bank of "positive" and "negative" words in a .txt file, used for later prediction in tweets
 */

/* Author: Shandon Probst
 * Class: CSCI 3381 8:00 AM T/TR
 * Purpose: This program is meant to create an archive of tweets given a text file and predict their polarity
 * in methods that reflect correct sentiment analysis
 */

public class MainTester {

	public static void main(String[] args) 
	{
		//*********************** BEGINNING OF TESTER FOR testFileIn.txt ***********************
		
		//sample tweets
		Tweet firstTweet = new Tweet("0,0000000001,Shando,Testing!");
		Tweet secondTweet = new Tweet("0,9990536256,Max,Hi Shandon :^)");
		Tweet thirdTweet = new Tweet("0,8552636256,Max,There's a troll chasing me");
		Tweet fourthTweet = new Tweet("4,000000002,Shando,acclaim acclaimed acclamation accolade accolades");
		
		//sample tweet collection
		TweetCollection TestTweets = new TweetCollection("./TweetPredictionPackage/testFileIn.txt");
		
		//adds sample tweets to the testcollection
		TestTweets.addTweet(firstTweet);
		TestTweets.addTweet(secondTweet);
		TestTweets.addTweet(thirdTweet);
		TestTweets.addTweet(fourthTweet);
		
		System.out.println("Gets Tweets from TweetID : '0000000001'" + TestTweets.getTweetById("0000000001"));
		System.out.println("\nGets Tweets from Twitter user 'Max': " + TestTweets.getTweetByUserName("Max"));
		
		//prints all tweets
		System.out.print("\nAll Tweets in the TestTweets collection: ");
		System.out.println(TestTweets);
		System.out.println("\nAll Tweet IDs that exist in the collection: " + TestTweets.getAllCollectionIds());
		System.out.println("\nTweet IDs that are made by Max: " + TestTweets.getCollectionIds("Max"));
		System.out.println("\nThe prediction(polarity) for thirdTweet: " + TestTweets.prediction(thirdTweet));
		System.out.println("\nThe prediction(polarity) for fourthTweet: " + TestTweets.prediction(fourthTweet));
		
		//removes a tweet by id and prints tweets with the id
		TestTweets.removeTweetById("9990536256");
		System.out.println("\n(After Removal) Tweet IDs that are made by Max: " + TestTweets.getCollectionIds("Max"));
		
		//writes the new predictions to an outfile
		TestTweets.writeFile("./TweetPredictionPackage/testFileOut.txt");
		
		//displays the accuracy of the predictions, comparing the infile to the outfile polarities
		
		System.out.println("\n****************** PREDICTIONS FOR (testFileIn.txt) *******************");
		TestTweets.collectionPolarityTest();
		System.out.println("\n************** END OF PREDICTIONS FOR (testFileIn.txt) ****************");
		
		//************************** END OF TESTER FOR testFileIn.txt **************************
		
		
		//********************** BEGINNING OF TESTER FOR testProcessed.txt **********************
		
		System.out.println("\n**************** PREDICTIONS FOR (testProcessed.txt) ******************");
		TweetCollection Tweets = new TweetCollection("./TweetPredictionPackage/testProcessed.txt");
		Tweets.collectionPolarityTest();
		Tweets.writeFile("./TweetPredictionPackage/TweetCollectionText.txt");
		System.out.println("\n************* END OF PREDICTIONS FOR (testProcessed.txt) **************");
		
		//************************* END OF TESTER FOR testProcessed.txt *************************
	}

}
