package twitter.share;

public class TweetLocation {

    private double latitude;
    private double longitude;
    private double radius;
    private String radiusUnit;

    public TweetLocation(double latitude, double longitude, double radius, String radiusUnit) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.radiusUnit = radiusUnit;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @param radius the radius to set
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * @return the radiusUnit
     */
    public String getRadiusUnit() {
        return radiusUnit;
    }

    /**
     * @param radiusUnit the radiusUnit to set
     */
    public void setRadiusUnit(String radiusUnit) {
        this.radiusUnit = radiusUnit;
    }
}
