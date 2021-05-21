package ds4300_twitter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class TwitterTester {

  public static void main(String[] args) {
    //Connection con = DriverManager.getConnection(jdbc:myDriver:myDatabase);
    TwitterAPI api = new MySQLTwitterAPI();
    String url = "jdbc:mysql://localhost:3306/twitter_a1?serverTimezone=EST5EDT";
    //
    String user = "twitter_user"; //System.getenv("TWITUSER");
    String pw = "Orangebutt12!"; //System.getenv("TWITPW");
    api.connect(url, user, pw);

/*
    List<Integer> users = api.getFollowers(3);
    System.out.println(users.size());
*/
    try {
      /*
      File tweetfile = new File("tweet.csv");
      Scanner readr = new Scanner(tweetfile);
      while (readr.hasNextLine()) {
        String data = readr.nextLine();
        System.out.println(data);
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
       */
      BufferedReader reader = new BufferedReader(new FileReader("/Users/jeremycui/Documents/DS4300 HW/hw1_data/tweet.csv"));
      api.connect(url, user, pw);
      long start = System.nanoTime();
      int count = 0;
      String currentLine;
      reader.readLine();
      while ((currentLine = reader.readLine()) != null) {
        String[] values = currentLine.split(",");
        int userId = Integer.parseInt(values[0]);
        String tweettx = values[1];
        count += 1;
        //System.out.println(userId + ", " + tweettx);
        Tweet tweet = new Tweet(userId, tweettx);
        api.postTweet(tweet);
        //System.out.println(count);
        //System.out.println(start);

      }
      long end = System.nanoTime();
      System.out.println(end - start);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    try {
      Random rand = new Random();
      BufferedReader reader = new BufferedReader(new FileReader("/Users/jeremycui/Documents/DS4300 HW/hw1_data/tweet.csv"));
      api.connect(url, user, pw);
      long start = System.nanoTime();
      int count = 0;
      String currentLine;
      reader.readLine();
      int largestId = 0;
      while ((currentLine = reader.readLine()) != null) {
        String[] values = currentLine.split(",");
        int userId = Integer.parseInt(values[0]);
        String tweettx = values[1];

        if (userId > largestId) {
          largestId = userId;
        }
        count += 1;
        //System.out.println(userId + ", " + tweettx);
        int upbound = largestId;

        api.getTimeline(rand.nextInt(largestId));
      }
      long end = System.nanoTime();
      System.out.println(end - start);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    List<Integer> tweetids = api.getTweets(5);
    System.out.println(tweetids);

    api.close();
  }
}
