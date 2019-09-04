package data;

import util.LinkedList;

/**
 * Cellular and school enrollment data of specific country over a period of years
 *
 * @author Jiaping Zeng
 */

public class Country {

    /**
     * name of country
     */
    private String name;

    /**
     * collection of numerical data
     */
    private LinkedList<DataYear> data;

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
        data = new LinkedList<>();
        minYear = 9999;
        maxYear = 0;
    }

    /**
     * clones from another country
     * @param country country to clone from
     */
    public Country(Country country) {
        this.name = new String(country.getName());
        LinkedList<DataYear> oldData = country.getData();
        data = new LinkedList<>();
        for (DataYear dataYear : oldData) {
            this.data.add(new DataYear(dataYear));
        }
    }

    public void addDataYear(int year, double data) {
        if (year < minYear) minYear = year;
        if (year > maxYear) maxYear = year;
        this.data.add(new DataYear(year, data));
    }

    /**
     * Returns number of subscriptions within a period of time
     *
     * @param startYear requested start year (inclusive)
     * @param endYear   requested end year (inclusive)
     * @return number of subscriptions in period
     * @throws IllegalArgumentException when requested end year is earlier than start year or when start and end years are both out of range
     */
    public double getDataForPeriod(int startYear, int endYear) throws IllegalArgumentException {
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
        for (DataYear dataYear: data) {
            int year = dataYear.getYear();
            if (year >= startYear && year <= endYear) {
                sum += dataYear.getData();
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
        if (data == null) return sb.toString();
        for (DataYear dataYear : data) {
            if (dataYear != null) {
                sb.append('\t');
                sb.append(dataYear);
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
     * returns name of country
     * @return name of country
     */
    public String getName() {
        return name;
    }

    /**
     * sets name of country
     * @param name name of country
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * returns data stored in this Country object
     * @return data stored
     */
    public LinkedList<DataYear> getData() {
        return data;
    }

    /**
     * sets data for this Country object
     * @param data data to be set
     */
    public void setData(LinkedList<DataYear> data) {
        this.data = data;
    }
}