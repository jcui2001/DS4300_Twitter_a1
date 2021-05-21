package ds4300_twitter;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalTime.now;

public class MySQLTwitterAPI implements TwitterAPI {

  // Connection object manages connection to database (Driver program)
  private Connection con;

  @Override
  public void postTweet(Tweet t) {
    try {
      String inserttweet = "INSERT INTO tweets VALUES ('" + t.getTweetID() + "', '" + t.getUserID() + "', '"
              + LocalDateTime.now() + "', '" + t.getText() + "');";

      Statement stmt = con.createStatement();
      // ResultSet rs = stmt.execute(inserttweet);
      stmt.executeUpdate(inserttweet);
      stmt.close();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }

  @Override
  public List<Tweet> getTimeline(int userID) {
    List<Tweet> tweets = new ArrayList<Tweet>();

    try {
      String sql = "SELECT t.user_id, t.tweet_ts, t.tweet_text FROM tweets t " +
              "WHERE t.user_id=(SELECT f.follows_id FROM follows f WHERE f.user_id=" + userID + ") LIMIT 10;";
      PreparedStatement stmt = con.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        Tweet tweet = new Tweet(rs.getInt("tweet_id"), rs.getInt("user_id"),
                rs.getTimestamp("tweet_ts"), rs.getString("tweet_text"));
        tweets.add(tweet);
      }

      rs.close();
      stmt.close();
      return tweets;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }

    return tweets;
  }

  @Override
  public List<Integer> getFollowers(Integer userid) {
    List<Integer> users = new ArrayList<Integer>();

    try {
      String sql = "SELECT * FROM FOLLOWS WHERE user_id = " + userid + ";";
      PreparedStatement stmt = con.prepareStatement(sql);
      // initialize resultSet
      ResultSet rs = stmt.executeQuery(sql);
      // iterates through resultSet
      while (rs.next()) {
        // build Tweet object
        int followsID = rs.getInt("follows_id");
        // adds it to List of Tweets
        users.add(followsID);
      }

      rs.close();
      stmt.close();
      return users;

    } catch (SQLException e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
    return users;
  }

  @Override
  public List<Integer> getTweets(Integer userid) {
    List<Integer> tweetIDs = new ArrayList<Integer>();

    try {
      // give it an insert query to run, grabs from the Driver Program
      String sql = "SELECT tweet_id FROM tweets WHERE " + userid + " = user_id);";
      Statement stmt = con.createStatement();
      stmt.executeQuery(sql);
      // initialize resultSet
      ResultSet rs = stmt.executeQuery(sql);
      // iterates through resultSet
      while (rs.next()) {
        // build Tweet object grabbing the user_id
        int usersID = rs.getInt("user_id");
        // adds it to List of Tweets
        tweetIDs.add(usersID);
      }

      // closes results set and statement
      rs.close();
      stmt.close();
      return tweetIDs;

    } catch (SQLException e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }

    return tweetIDs;
  }

  @Override
  public void addFollows(int userId, int followsID) {
    // give it an insert query to run, grabs from the Driver Program
    String sql = "INSERT INTO follows (user_id, follows_id) " +
            "VALUES (" + userId + "," + followsID + ");";

    try {
      Statement stmt = con.createStatement();
      stmt.executeUpdate(sql);
      stmt.close();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }

  @Override
  public void connect(String url, String user, String pw) {
    if (con == null) {
      // tries creating connection via DriverManager
      try {
        con = DriverManager.getConnection(url, user, pw);
      // catches SQL exception
      } catch (SQLException e) {
        System.err.println(e.getMessage());
      }
    }
  }

  @Override
  public void close() {
    try {
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
