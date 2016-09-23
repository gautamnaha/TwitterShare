package twitter.share;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.GeoLocation;
import twitter4j.IDs;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class TweetCollector implements TweetDistributorInterface {

    private TweetLocation location;
    private String tweetQuery;
    private Twitter twitter;
    private int numberOfShares;

    public TweetCollector(Twitter twitter, TweetLocation location, String tweetQuery, int numberOfShares) {
        this.location = location;
        this.tweetQuery = tweetQuery;
        this.twitter = twitter;
        this.numberOfShares = numberOfShares;
    }

    private List<Status> getTweetStatus(TweetLocation location, String tweetQuery) throws TwitterException {
        List<Status> tweets = new ArrayList<>();
        Query query = new Query(tweetQuery)
                .geoCode(new GeoLocation(location.getLatitude(), location.getLongitude()), location.getRadius(), location.getRadiusUnit());
        QueryResult result;
        do {
            result = twitter.search(query);
            tweets.addAll(result.getTweets());
        } while ((query = result.nextQuery()) != null);
        return tweets;
    }

    private void shareTweets(ResponseList<User> users, List<Status> tweets) throws TwitterException {
        for (User u : users) {
            for (Status st : tweets) {
                twitter.sendDirectMessage(u.getScreenName(), st.getText());
            }
        }
    }

    @Override
    public void processTweets() {
        try {
            List<Status> tweets = getTweetStatus(location, tweetQuery);
            final IDs friendIds = twitter.getFollowersIDs(twitter.getId(), -1);
            long[] friends = new long[numberOfShares];
            System.arraycopy(friendIds.getIDs(), 0, friends, 0, numberOfShares);
            final ResponseList<User> users = twitter.lookupUsers(friends);
            shareTweets(users, tweets);
            System.out.println(tweets.size() + " tweets shared with " + numberOfShares + " friends successfully");
        } catch (TwitterException | IllegalStateException ex) {
            Logger.getLogger(TweetCollector.class.getName()).log(Level.SEVERE, "Exception occured while processing tweets", ex);
        }

    }

}
