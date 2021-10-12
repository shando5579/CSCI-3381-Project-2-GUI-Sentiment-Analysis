package TweetPredictionPackage;

import java.util.Iterator;
import java.util.TreeSet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class TweetCollection 
{
	private TreeSet<Tweet> tweets;
	private TreeSet<String> positives;
	private TreeSet<String> negatives;
	private int actualPositive;
	private int actualNeutral;
	private int actualNegative;
	private String fileName;
	
	//default constructor
	public TweetCollection()
	{
		fileName = null;
		tweets = new TreeSet<Tweet>();
		positives = new TreeSet<String>();
		negatives = new TreeSet<String>();
		
	}
	
	//constructor with a name and filename
	public TweetCollection(String fName)
	{
		this();
		fileName = fName;
		readTweetFile();
		//removeDupes();
	}
	
	//********************** START OF COLLECTION MANAGEMENT **********************
	//adds the tweet object to the collection
	public void addTweet(Tweet tweet)
	{
		tweets.add(tweet);
	}

	//adds a positive word to the positive treeset
	public void addPositive(String positive) 
	{
		positives.add(positive);
	}

	//adds a negative word to the negative treeset
	public void addNegative(String negative) 
	{
		negatives.add(negative);
	}

	//gets the actual number of positive tweets read from a file
	public int getActualPositive() 
	{
		return actualPositive;
	}
	
	//adds +1 to the list of actual positives
	public void addActualPositive() 
	{
		actualPositive++;
	}

	//gets the actual number of neutral tweets read from a file
	public int getActualNeutral() 
	{
		return actualNeutral;
	}
	
	//adds +1 to the list of actual neutrals
	public void addActualNeutral() 
	{
		actualNeutral++;
	}

	//gets the actual number of negative tweets read from a file
	public int getActualNegative() 
	{
		return actualNegative;
	}

	//adds +1 to the list of actual negatives
	public void addActualNegative() 
	{
		actualNegative++;
	}

	//removes the tweet object from the collection
	public void removeTweet(Tweet tweet)
	{
		tweets.remove(tweet);
	}
	
	//removes the tweet object(s) from the collection that contains the specified id
	public void removeTweetById(String tweetId)
	{
		tweets.remove(this.getTweetById(tweetId));
	}
	
	//gets a tweet object(s) that contains the specified id
	public Tweet getTweetById (String tweetId)
	{	
		Iterator<Tweet> iter = tweets.iterator();
		
		while(iter.hasNext())
		{
			Tweet tweet = iter.next();
			if (tweet.getTweetId().equals(tweetId))
				return tweet;
		}
			return null;
	}
	
	//returns tweets based on a given polarity
	public Tweet getTweetByPolarity (String polarity)
	{	
		Iterator<Tweet> iter = tweets.iterator();
		
		while(iter.hasNext())
		{
			Tweet tweet = iter.next();
			if (tweet.getPolarity().equals(polarity))
				return tweet;
		}
			return null;
	}
	
	//gets a tweet object(s) that contains the specified username
	public Tweet getTweetByUserName (String tweetUserName)		//gets a tweet in the tweets treeset by its username
	{		 
		Iterator<Tweet> iter = tweets.iterator();
		
		while(iter.hasNext())
		{
			Tweet tweet = iter.next();
			if (tweet.getTweetUserName().equals(tweetUserName))
				return tweet;
		}
			return null;
	}
	
	/*public void removeDupes()
	{
		Iterator<Tweet> itera = tweets.iterator();
		while(itera.hasNext())
		{
			Iterator<Tweet> iterb = tweets.iterator();
			while (iterb.hasNext()) 
			{
				if(iterb.next().compareTo(itera.next()) > 0)
				{
					removeTweetById(iterb.next().getTweetId());
					removeTweetById(itera.next().getTweetId());
				}
			}
		}
	}*/
	
	//*********************** END OF COLLECTION MANAGEMENT ***********************
	
	//*********************** START OF COLLECTION FUNCTIONS **********************
	//toString for object, displays all information from Tweet object
	public String toString()
	{
		String toReturn = "";
		for (Tweet tweet : tweets)
		{
			toReturn += tweet;
		}
		return toReturn;
	}
	
	//gets all ids in the collection
	public TweetCollection getAllCollectionIds()
	{
		TweetCollection toReturn = new TweetCollection();
		
		for (Tweet tweet : tweets)
			toReturn.addTweet(tweet);
		return toReturn;
	}
	
	//gets all ids from a specified user in the collection
	public TweetCollection getCollectionIds(String userName)
	{
		TweetCollection toReturn = new TweetCollection();
		
		for (Tweet tweet : tweets)
		{
			if (tweet.getTweetUserName().equals(userName))
				toReturn.addTweet(tweet);
		}
		return toReturn;
	}
	
	//tests the tweets in the collection for their predicted polarity and displays the relevant information, including predictor accuracy
	public void collectionPolarityTest()
	{
		double accuracy = 0.0;
		int tested = 0;
		
		int predict_negative = 0;
		int predict_neutral = 0;
		int predict_positive = 0;
		
		for (Tweet tweet : tweets)
		{
			String prediction = String.valueOf(prediction(tweet));
			tested += 1;
			
			if (prediction.equals("0"))
				predict_negative++;
			else if(prediction.equals("2"))
				predict_neutral++;
			else if(prediction.equals("4"))
				predict_positive++;
			
			if (prediction.equals(tweet.getPolarity()))
				accuracy += 1;
		}
		System.out.println("\nActual # of Positive Tweets: " + this.getActualPositive());
		System.out.println("Actual # of Neutral Tweets: " + this.getActualNeutral());
		System.out.println("Actual # of Negative Tweets: " + this.getActualNegative());
		
		System.out.println("\nPredicted # of Positive Tweets: " + predict_positive);
		System.out.println("Predicted # of Neutral Tweets: " + predict_neutral);
		System.out.println("Predicted # of Negative Tweets: " + predict_negative);
		
		System.out.println("\nPredictor Accuracy: " + Math.round(accuracy/tested*100) + "%");
	}
	//*********************** END OF COLLECTION FUNCTIONS ************************
	
	//****************** START OF FILE MANAGEMENT & FUNCTIONS ********************
	
	//reads the provided list of tweets and creates a treeset of the tweets
	private void readTweetFile()

	{
		BufferedReader lineReader = null;
		try 
		{
			FileReader fr = new FileReader(fileName);
			lineReader = new BufferedReader(fr);
			String tweet;
			while ((tweet = lineReader.readLine())!=null) 
			{
				addTweet(new Tweet(tweet));
				if (tweet.startsWith("0"))
					this.addActualNegative();
				else if(tweet.startsWith("2"))
					this.addActualNeutral();
				else if(tweet.startsWith("4"))
					this.addActualPositive();
			}
		} catch (Exception e)
		{
			System.err.println("There was a problem with the file reader, try different read type.");
			try
			{
				
				lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName.substring(1))));
				String tweet;
				while ((tweet = lineReader.readLine())!=null) 
				{
					addTweet(new Tweet(tweet));
					if (tweet.startsWith("0"))
						this.addActualNegative();
					else if(tweet.startsWith("2"))
						this.addActualNeutral();
					else if(tweet.startsWith("4"))
						this.addActualPositive();
				}
			} catch (Exception e2)
			{
				System.err.println("There was a problem with the file reader, try again.  either no such file or format error");
			} 
			finally
			{
				if (lineReader != null)
				{
					try
					{
						lineReader.close();
					} catch (IOException e2)
					{
						System.err.println("Could not close BufferedReader");
					}
				}
						
			}			
		} finally
		{
			if (lineReader != null)
			{
				try
			{
					lineReader.close();
				} catch (IOException e) {
					System.err.println("Could not close BufferedReader");
				}
			}
		}
			
	}
	
	//reads the list of positive words from the positive-word.txt file and creates a treeset of the words
	private void readPositiveFile()

	{
		BufferedReader lineReader = null;
		try 
		{
			FileReader fr = new FileReader("./TweetPredictionPackage/positive-words.txt");
			lineReader = new BufferedReader(fr);
			String positive;
			while ((positive = lineReader.readLine())!=null) 
			{
				addPositive(new String (positive));
			}
		} catch (Exception e)
		{
			System.err.println("There was a problem with the file reader, try different read type.");
			try
			{
				
				lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName.substring(1))));
				String positive;
				while ((positive = lineReader.readLine())!=null) 
				{
					addPositive(new String (positive));
				}
			} catch (Exception e2)
			{
				System.err.println("There was a problem with the file reader, try again.  either no such file or format error");
			} 
			finally
			{
				if (lineReader != null)
				{
					try
					{
						lineReader.close();
					} catch (IOException e2)
					{
						System.err.println("Could not close BufferedReader");
					}
				}
						
			}			
		} finally
		{
			if (lineReader != null)
			{
				try
			{
					lineReader.close();
				} catch (IOException e) {
					System.err.println("Could not close BufferedReader");
				}
			}
		}
	}
	
	//reads the list of negative words from the negative-word.txt file and creates a treeset of the words
	private void readNegativeFile()
	{
		BufferedReader lineReader = null;
		try 
		{
			FileReader fr = new FileReader("./TweetPredictionPackage/negative-words.txt");
			lineReader = new BufferedReader(fr);
			String negative;
			while ((negative = lineReader.readLine())!=null) 
			{
				addNegative(new String (negative));
			}
		} catch (Exception e)
		{
			System.err.println("There was a problem with the file reader, try different read type.");
			try
			{
				
				lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName.substring(1))));
				String negative;
				while ((negative = lineReader.readLine())!=null) 
				{
					addNegative(new String (negative));
				}
			} catch (Exception e2)
			{
				System.err.println("There was a problem with the file reader, try again.  either no such file or format error");
			} 
			finally
			{
				if (lineReader != null)
				{
					try
					{
						lineReader.close();
					} catch (IOException e2)
					{
						System.err.println("Could not close BufferedReader");
					}
				}
						
			}			
		} finally
		{
			if (lineReader != null)
			{
				try
			{
					lineReader.close();
				} catch (IOException e) {
					System.err.println("Could not close BufferedReader");
				}
			}
		}
	}
		
	public void writeFile ()
	{
		// overloaded method: this calls doWrite with file used to read data
		// use this for saving data between runs
		doWrite(fileName);
	} // end of writeFile method
	
	public void writeFile(String altFileName) 
	{
		// overloaded method: this calls doWrite with different file name 
		// use this for testing write
		doWrite(altFileName);		
	}// end of writeFile method
	
	private void doWrite(String fn) 
	{
		// this method writes all of the data in the tweets treeset to a file
		try
		{
			FileWriter fw = new FileWriter(fn);
			BufferedWriter myOutfile = new BufferedWriter(fw);	
			
			for (Tweet tweet : tweets)
			{
				myOutfile.write(String.valueOf(prediction(tweet)) + "," + tweet.getTweetId() + "," + tweet.getTweetUserName() + "," + tweet.getContents());
				myOutfile.write("\n");
			}
			myOutfile.flush();
			myOutfile.close();
			
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Didn't save to " + fn);
		}
		
	}
	
	
	//******************* END OF FILE MANAGEMENT & FUNCTIONS *********************
	
	//********************** START OF PREDICTION FUNCTIONS ***********************
	
	//predicts the polarity of a tweet, based on the accumulation of positive or negative words in the tweet
	//0 = negative, 2 = neutral, 4 = positive
	public int prediction(Tweet tweet)
	{
		readPositiveFile();
		readNegativeFile();
		
		int polarity = -1;
		int points = 0;
		
		for (String string : positives)
		{
			if (tweet.getContents().toLowerCase().contains(string))
			{
				points += 1;
			}
		}
			
		for (String string : negatives)
		{
			if (tweet.getContents().toLowerCase().contains(string))
			{
				points -= 1;
			}
		}
		
		if (points >= 1)
			polarity = 4;
		else if (points <= -1)
			polarity = 0;
		else if (points == 0)
			polarity = 2;
			
		return polarity;
	}
	
	//********************** END OF PREDICTION FUNCTIONS *************************
}


