package data;

/**
 * stores numerical value of a specific year
 */
public class DataYear {

    /**
     * year of data
     */
    private int year;

    /**
     * data stored
     */
    private double data;

    /**
     * initialize with year and data specified
     * @param year year of data
     * @param data data to be stored
     */
    public DataYear(int year, double data) {
        this.year = year;
        this.data = data;
    }

    /**
     * clones another DataYear object
     * @param dataYear DataYear to clone from
     */
    public DataYear(DataYear dataYear) {
        this.year = dataYear.year;
        this.data = dataYear.data;
    }

    /**
     * prints out the stored data
     * @return
     */
    @Override
    public String toString() {
        return "" + data;
    }

    /**
     * returns year of data
     * @return year of data
     */
    public int getYear() {
        return year;
    }

    /**
     * sets year of data
     * @param year year of data
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * returns stored data
     * @return stored data
     */
    public double getData() {
        return data;
    }

    /**
     * sets data
     * @param data new data value
     */
    public void setData(double data) {
        this.data = data;
    }
}
