package cellularData;

/**
 * Cellular data of specific country over a period of years
 *
 * @author Jiaping Zeng
 */

class Country {

    /**
     * name of country
     */
    private String name;

    /**
     * collection of number of subscriptions of different years
     */
    private LinkedList<SubscriptionYear> subscriptions;

    /**
     * earliest year with available data
     */
    private int minYear;

    /**
     * latest year with available data
     */
    private int maxYear;

    /**
     * Create a country with just name and no data
     * @param name name of country
     */
    public Country(String name) {
        this.name = name;
        subscriptions = new LinkedList<>();
        minYear = 9999;
        maxYear = 0;
    }

    /**
     * Adds number of subscriptions of a specific year to country
     *
     * @param year         year of recorded subscriptions
     * @param subscription number of subscriptions
     */
    void addSubscriptionYear(int year, double subscription) {
        if (year < minYear) minYear = year;
        if (year > maxYear) maxYear = year;
        subscriptions.add(new SubscriptionYear(year, subscription));
    }

    /**
     * Returns number of subscriptions within a period of time
     *
     * @param startYear requested start year (inclusive)
     * @param endYear   requested end year (inclusive)
     * @return number of subscriptions in period
     * @throws IllegalArgumentException when requested end year is earlier than start year or when start and end years are both out of range
     */
    double getNumSubscriptionsForPeriod(int startYear, int endYear) throws IllegalArgumentException {
        if (startYear > endYear) {
            throw new IllegalArgumentException("Requested end year is earlier than start year");
        }
        if (startYear < minYear && endYear > maxYear) {
            throw new IllegalArgumentException("Requested start year and end year are both out of range");
        }
        if (startYear - minYear < 0) {
            System.out.println("WARNING: Invalid start year " + startYear + " for country " + name + ", setting start year to " + minYear);
            startYear = minYear;
        }
        if (endYear > maxYear) {
            System.out.println("WARNING: Invalid end year " + endYear + " for country " + name + ", setting end year to " + maxYear);
            endYear = maxYear;
        }
        double sum = 0;
        for (SubscriptionYear subscription: subscriptions) {
            int year = subscription.getYear();
            if (year >= startYear && year <= endYear) {
                sum += subscription.getSubscriptions();
            }
        }
        return sum;
    }

    /**
     * Returns cellular data of a country (tab separated)
     *
     * @return cellular data of a country (tab separated)
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        if (subscriptions == null) return sb.toString();
        for (SubscriptionYear subscription : subscriptions) {
            if (subscription != null) {
                sb.append('\t');
                sb.append(subscription);
            }
        }
        return sb.toString();
    }

    /**
     * Check if this Country is the same as a different object
     * @param obj Object to compare to
     * @return true if same, false if not
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Country && ((Country) obj).getName().equals(this.name);
    }

    // ACCESSORS-----------------------------------

    /**
     * Returns name of country     *
     * @return name of country
     */
    String getName() {
        return name;
    }

    /**
     * Sets name of country     *
     * @param name name of country
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * Returns list of subscriptions
     *
     * @return list of subscriptions
     */
    LinkedList<SubscriptionYear> getSubscriptions() {
        return subscriptions;
    }

    /**
     * Sets subscriptions
     *
     * @param subscriptions subscriptions
     */
    void setSubscriptions(LinkedList<SubscriptionYear> subscriptions) {
        this.subscriptions = subscriptions;
    }
}