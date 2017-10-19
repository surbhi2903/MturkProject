import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by surbhi on 10/11/17.
 */
public class TwitterInfo {

    public static void main(String[] args) throws TwitterException, IOException {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true).setOAuthConsumerKey("uHoWjnMCT6Hme7lWGhqwonFzo")
                .setOAuthConsumerSecret("PwPTdHW1TacSmRMOSyCufnbr3AEKTuZMifhV6QrQ0ZEZFDSnJm")
                .setOAuthAccessToken("1024176547-nHywb1EugIyrrTTgrLDFkTHTpi5goxr0kXUmok8")
                .setOAuthAccessTokenSecret("Vsqmfa4AAlhnNWHH9CGGJEW7vv8LnrVzRsFR0qyPrmEZ8");

        TwitterFactory tf = new TwitterFactory(configurationBuilder.build());
        twitter4j.Twitter twitter= tf.getInstance();

        try {
            Query query = new Query("#religious");
            query.setCount(100);
            QueryResult result;
            result = twitter.search(query);
            List<Status> tweets = result.getTweets();
            for (Status tweet : tweets) {
                System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                List<String[]> lines = new ArrayList<>(result.getTweets().size());
                for(Status status : result.getTweets()) {
                    lines.add(new String[] { status.getText() });
                }
                String fileName = "/Users/surbhi/Desktop/data.csv";
                CSVWriter writer = new CSVWriter(new FileWriter(fileName));
                writer.writeAll(lines);
            }

        }
        catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }


    }
}
