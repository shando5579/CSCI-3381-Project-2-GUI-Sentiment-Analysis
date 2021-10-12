package TweetPredictionPackage;

public class Tweet implements Comparable<Tweet>
{
	private String tweetId;			//stored tweet ID variable
	private String polarity;
	private String userName;		//stored username variable
	private String contents;		//stored contents of the tweet variable
	
	public Tweet (String tweet)
	{
		String[] temp = tweet.split(",", 4);
		
		polarity = temp[0];
		tweetId = temp[1];
		userName = temp[2];
		contents = temp[3];
		
	}

	//********************** START OF GETTERS AND SETTERS **********************
	//gets a tweet's userName
	public String getTweetUserName()
	{
		return userName;
	}

	//sets tweet object's userName
	public void setUserName(String userName)
	{	
		this.userName = userName;
	}

	//gets a tweet's tweetId 
	public String getTweetId()	
	{
		return tweetId;
	}

	//sets tweet object's tweetId
	public void setTweetId(String tweetId) 	
	{
		this.tweetId = tweetId;
	}
	
	//gets a tweet's contents
	public String getContents() 
	{
		return contents;
	}

	//sets tweet object's contents
	public void setContents(String contents)
	{
		this.contents = contents;
	}
	
	//gets a tweet's polarity
	public String getPolarity() 
	{
		return polarity;
	}

	//sets a tweet object's polarity
	public void setPolarity(String polarity) 
	{
		this.polarity = polarity;
	}
	//*********************** END OF GETTERS AND SETTERS ***********************
	
	//displays tweet information as a string
	public String toString ()  
	{
		int format_length = 35;
		format_length -= this.getTweetUserName().length();
		String toReturn = "\nTweet Polarity: " + this.getPolarity();
		toReturn += "\tTweet ID: " + this.getTweetId();
		if (this.getTweetId().length() > 5)
			toReturn += "\tUsername: " + this.getTweetUserName();
		else
			toReturn += "\t\tUsername: " + this.getTweetUserName();

		toReturn += String.format("%" + format_length + "s", "Contents: ");
		toReturn += this.getContents();
		return toReturn;
	}

	@Override
	public int compareTo(Tweet tweet) {
		if (tweetId.compareTo(tweet.getTweetId()) < 0){
			return -1;
		}
		if (tweetId.compareTo(tweet.getTweetId()) > 0){
			return 1;
		}
		return 0;
	}
}

