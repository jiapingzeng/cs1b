package cellularData;

/**
 * Stores table of cellular data of countries over a period of time
 *
 * @author Jiaping Zeng
 */

public class CellularData {

    /**
     * Cellular data table
     */
    private double[][] table;
    /**
     * List of country names in the table
     */
    private String[] countries;
    /**
     * Keeps track of country
     */
    private int numCountries;
    /**
     * First year of data (inclusive)
     */
    private int startingYear;
    /**
     * Number of rows in the table
     */
    private int rows;
    /**
     * Number of columns in the table
     */
    private int cols;

    /**
     * Creates a table to store cellular data
     *
     * @param numRows      number of rows to initialize the table with (additional rows may be added later)
     * @param numCols      number of columns of the table
     * @param startingYear first year of recorded data
     */
    public CellularData(int numRows, int numCols, int startingYear) {
        table = new double[numRows][numCols];
        this.startingYear = startingYear;
        rows = numRows;
        cols = numCols;
        numCountries = 0;
        countries = new String[numCountries];
    }

    /**
     * add a new country to the table
     *
     * @param countryName name of the new country
     * @param countryData cellular data of the new country (with starting year and length specified in the constructor)
     */
    public void addCountry(String countryName, double[] countryData) {
        if (countryName.isEmpty() || countryData.length != cols) {
            // cancel adding if country has no name or if data doesn't match table columns size
            System.out.println("Received invalid data for " + countryName);
            return;
        }
        // store country name
        String[] copy = countries.clone();
        countries = new String[copy.length + 1];
        for (int i = 0; i < copy.length; i++) {
            countries[i] = copy[i];
        }
        countries[numCountries] = countryName;
        if (table[rows - 1].length > 0) {
            // table is full, make new table with additional row
            double[][] clone = table.clone();
            table = new double[clone.length + 1][cols];
            for (int i = 0; i < clone.length; i++) {
                table[i] = clone[i];
            }
            rows++;
        }
        table[numCountries] = countryData;
        numCountries++;
    }

    /**
     * Get sum of number of subscriptions of a country over a period of years
     *
     * @param countryName  name of the country
     * @param startingYear first year of data
     * @param endingYear   last year of data
     * @return number of cellular subscriptions from startingYear to endingYear
     */
    public double getNumSubscriptionsInCountryForPeriod(String countryName, int startingYear, int endingYear) {
        double sum = 0;
        for (int i = 0; i < countries.length; i++) {
            if (countries[i].equals(countryName)) {
                double[] countryData = table[i];
                int startingIndex = startingYear - this.startingYear;
                int endingIndex = endingYear - this.startingYear;
                if (startingIndex < 0) {
                    startingIndex = 0;
                    System.out.println("WARNING: Invalid start year " + startingYear + " for country " + countryName + ", setting start year to " + (this.startingYear + startingIndex));
                }
                if (endingIndex >= countryData.length) {
                    endingIndex = countryData.length - 1;
                    System.out.println("WARNING: Invalid end year " + endingYear + " for country " + countryName + ", setting end year to " + (this.startingYear + endingIndex));
                }
                if (endingIndex < startingIndex) {
                    System.out.println("Provided end year is earlier in time than start year");
                    return 0;
                }
                for (int j = startingIndex; j <= endingIndex; j++) {
                    sum += countryData[j];
                }
            }
        }
        return sum;
    }

    /**
     * Returns a formatted table as a string
     *
     * @return formatted table of data in the current object
     */
    @Override
    public String toString() {
        // Make first column's width base on the country with the longest name,
        // or 12 of no country has a name longer than column header "Country Name"
        String result = "";
        int countryNameLength = getLongestCountryNameLength();
        if (countryNameLength < 12) {
            countryNameLength = 12;
        }
        result += addSpacesAfter("Country Year", countryNameLength - 12);
        result += "  ";
        // display years in header row
        for (int i = 0; i < cols; i++) {
            result += " ";
            result += startingYear + i;
            result += " ";
        }
        result += "\n";
        // display data
        for (int i = 0; i < table.length; i++) {
            if (table[i].length < 1 || countries.length <= i || countries[i].isEmpty()) {
                // stop if country doesn't exist
                break;
            }
            // add spaces after country name to make make data columns start at the same point
            result += addSpacesAfter(countries[i], countryNameLength - countries[i].length());
            result += "  ";
            for (int j = 0; j < table[i].length; j++) {
                String dataString = Double.toString(table[i][j]);
                if (table[i][j] == 0) {
                    // display 0 instead of 0.0
                    dataString = "0";
                }
                if (dataString.length() > 5) {
                    // round to 4 digits
                    dataString = dataString.substring(0, 5);
                } else {
                    // add before if cell has less than 4 digits to make columns line up
                    dataString = addSpacesBefore(dataString, 5 - dataString.length());
                }
                result += dataString;
                result += " ";
            }
            result += "\n";
        }
        return result;
    }

    // HELPER METHODS------------------------

    /**
     * get length of longest country name (used for table formatting)
     *
     * @return length of longest name
     */
    private int getLongestCountryNameLength() {
        int max = countries[0].length();
        for (String country : countries) {
            if (country.length() > max) {
                max = country.length();
            }
        }
        return max;
    }

    /**
     * add spaces in front of given string
     *
     * @param originalString original string
     * @param numSpaces      number of spaces to add
     * @return new string
     */
    private String addSpacesBefore(String originalString, int numSpaces) {
        String resultString = "";
        for (int i = 0; i < numSpaces; i++) {
            resultString += " ";
        }
        resultString += originalString;
        return resultString;
    }

    /**
     * add spaces after given string
     *
     * @param originalString original string
     * @param numSpaces      number of spaces to add
     * @return new string
     */
    private String addSpacesAfter(String originalString, int numSpaces) {
        String resultString = "";
        resultString += originalString;
        for (int i = 0; i < numSpaces; i++) {
            resultString += " ";
        }
        return resultString;
    }
}
