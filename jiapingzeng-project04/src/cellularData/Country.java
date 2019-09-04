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
    private final String name;

    /**
     * collection of number of subscriptions of different years
     */
    private final SubscriptionYear[] subscriptions;

    /**
     * Stores number of subscriptions of a specific country over specific number of years
     *
     * @param name          name of country
     * @param numberOfYears number of years to be stored
     */
    public Country(String name, int numberOfYears) {
        this.name = name;
        subscriptions = new SubscriptionYear[numberOfYears];
    }

    /**
     * Adds number of subscriptions of a specific year to country
     *
     * @param year         year of recorded subscriptions
     * @param subscription number of subscriptions
     */
    void addSubscriptionYear(int year, double subscription) {
        if (subscriptions[0] == null) {
            subscriptions[0] = new SubscriptionYear(year, subscription);
        } else if (year < subscriptions[0].getYear() || year - subscriptions[0].getYear() >= subscriptions.length) {
            System.out.println("Attempted year " + year + " is out of range");
        } else {
            subscriptions[year - subscriptions[0].getYear()] = new SubscriptionYear(year, subscription);
        }
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
        if (hasData()) {
            if (startYear > endYear) {
                throw new IllegalArgumentException("Requested end year is earlier than start year");
            }
            int firstYear = subscriptions[0].getYear();
            int start = startYear - firstYear;
            int end = endYear - firstYear;
            if (start < 0 && end >= subscriptions.length) {
                throw new IllegalArgumentException("Requested start year and end year are both out of range");
            }
            if (start < 0) {
                start = 0;
                System.out.println("WARNING: Invalid start year " + startYear + " for country " + name + ", setting start year to " + (firstYear + start));
            }
            if (end >= subscriptions.length) {
                end = subscriptions.length - 1;
                System.out.println("WARNING: Invalid end year " + endYear + " for country " + name + ", setting end year to " + (firstYear + end));
            }
            double sum = 0;
            for (int i = start; i <= end; i++) {
                sum += subscriptions[i].getSubscriptions();
            }
            return sum;
        } else {
            System.out.println("Data has not been fully added. Please add subscriptions data for all years.");
            return -1;
        }
    }

    /**
     * Returns cellular data of a country (tab separated)
     *
     * @return cellular data of a country (tab separated)
     */
    @Override
    public String toString() {
        String result = name;
        for (SubscriptionYear subscription : subscriptions) {
            if (subscription != null) {
                result += "\t" + subscription;
            }
        }
        return result;
    }

    // ACCESSORS-----------------------------------

    /**
     * Returns name of country
     *
     * @return name of country
     */
    public String getName() {
        return name;
    }

    // HELPER METHODS------------------------------

    /**
     * Checks if subscription data is properly filled
     *
     * @return true if all data has been added, false if not
     */
    private boolean hasData() {
        for (SubscriptionYear subscription : subscriptions) {
            if (subscription == null) {
                return false;
            }
        }
        return true;
    }
}
