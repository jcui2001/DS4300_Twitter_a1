package ds4300_twitter;

import java.sql.Timestamp;
import java.util.Date;

public class Tweet {
  private int tweetID;
  private int userID;
  private Timestamp timeStamp;
  final private String text;

  public Tweet(int tweetID, int userID, Timestamp timeStamp, String text) {
    this.tweetID = tweetID;
    this.userID = userID;
    this.timeStamp = timeStamp;
    this.text = text;
  }

  public Tweet(int tweetID, int userID, String text) {
    this.tweetID = tweetID;
    this.userID = userID;
    this.text = text;
  }

  public Tweet(int userID, String text) {
    this.userID = userID;
    this.text = text;
  }

  public int getTweetID() {
    return tweetID;
  }

  public int getUserID() {
    return userID;
  }

  public Timestamp getTimeStamp() {
    return timeStamp;
  }

  public String getText() {
    return text;
  }
}
