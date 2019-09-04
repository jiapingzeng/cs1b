package cellularData;

/**
 * Cellular data of specific year
 *
 * @author Jiaping Zeng
 */

class SubscriptionYear {

    /**
     * year
     */
    private int year;

    /**
     * number of subscriptions in the year
     */
    private double subscriptions;

    /**
     * Stores number of subscriptions in a particular year
     *
     * @param year          year
     * @param subscriptions number of subscriptions
     */
    public SubscriptionYear(int year, double subscriptions) {
        this.year = year;
        this.subscriptions = subscriptions;
    }

    /**
     * Returns number of subscriptions as a string
     *
     * @return number of subscriptions as a string
     */
    @Override
    public String toString() {
        return Double.toString(subscriptions);
    }

    // ACCESSORS------------------

    /**
     * Returns year
     *
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * Set year
     *
     * @param year year of data to be stored
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Returns number of subscriptions in the year
     *
     * @return number of subscriptions
     */
    public double getSubscriptions() {
        return subscriptions;
    }

    /**
     * Set number of subscriptions in the year
     *
     * @param subscriptions number of subscriptions
     */
    public void setSubscriptions(double subscriptions) {
        this.subscriptions = subscriptions;
    }
}
