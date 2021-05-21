package ds4300_twitter;

import java.util.List;

public interface TwitterAPI {

  /**
   * Posts given tweet to twitter_a1.tweets through JDBC.
   * @param t Tweet to be posted.
   */
  void postTweet(Tweet t);

  /**
   * Gets the Tweets of the user of the given ID
   * @param userID index of given
   * @return
   */
  List<Tweet> getTimeline(int userID);

  /**
   * Gets the ID's of the followers of the user of the given ID.
   * @param userid index of the user whose followers are retrieved.
   * @return List of Integers representing ID's of followers.
   */
  List<Integer> getFollowers(Integer userid);

  /**
   * Gets tweet ID's of given user
   * @param userid index of user in question
   * @return List of Integers of ID's of tweets.
   */
  List<Integer> getTweets(Integer userid);

  /**
   * Adds an entry into the follows table.
   * @param userId userId to be inserted.
   * @param followsID followsId to be inserted.
   */
  void addFollows(int userId, int followsID);

  /**
   * Creates connection through DriverManager between Driver and JDBC implementation.
   * @param url to retrieve file path.
   * @param user username to access the database.
   * @param pw password to access database.
   */
  void connect(String url, String user, String pw);

  /**
   *
   */
  void close();
}
