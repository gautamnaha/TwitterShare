package twitter.share;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TweetClient {

    private static final String consumerKeyStr = "LphIgJXIcKHd8vsOzHwym0jES";
    private static final String consumerSecretStr = "JLnrcfRKafZp93nLsqtIDKIkG6id3wvFoEY7dtHtyZ99YHun85";
    private static final String accessTokenStr = "36985075-KAtXNIzrhuFqV0ETuxo0fzlWr8vQbwYrwfhT4rB5p";
    private static final String accessTokenSecretStr = "A3hmdJK8sDB2YYZP1Put7SvYG2PGck6odK19jW4ur6dSR";
    private final Twitter myTwitter;

    public TweetClient() {
        myTwitter = new TwitterFactory().getInstance();
        myTwitter.setOAuthConsumer(consumerKeyStr, consumerSecretStr);
        AccessToken accessToken = new AccessToken(accessTokenStr, accessTokenSecretStr);
        myTwitter.setOAuthAccessToken(accessToken);
    }

    public static void main(String args[]) {
        TweetClient client = new TweetClient();
        TweetLocation myLocation = new TweetLocation(37.5269815, -121.9241126, 30, "mi");
        String myQueryString = "2016 US Presidential Elections";
        int numberOfShares = 3;
        TweetDistributorInterface myTweetDistributor = new TweetCollector(client.getMyTwitter(), myLocation, myQueryString, numberOfShares);
        myTweetDistributor.processTweets();

    }

    /**
     * @return the myTwitter
     */
    Twitter getMyTwitter() {
        return myTwitter;
    }
}
