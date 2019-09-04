package cellularData;

/**
 * Tests the CSVReader class, which reads input from a CSV file. Uses
 * the attributes stored in CSVReader object to fill the CellularData class.
 *
 * @author Foothill College, Jiaping Zeng
 */
public class TestCSVReader {
    /**
     * Uses a CSVReader to parse a CSV file.
     * Adds each parsed line to an instance of the CellularData class.
     */
    public static void main(String[] args) {
        // final String FILENAME = "resources/cellular_short_oneDecade.csv";

        final String FILENAME = "resources/cellular.csv";

        // final String FILENAME = "resources/fake_data.csv";

        // final String FILENAME = "resources/this_file_does_not_exist.weird_extension";

        CSVReader parser = new CSVReader(FILENAME);

        String[] countryNames = parser.getCountryNames();
        int[] yearLabels = parser.getYearLabels();
        double[][] parsedTable = parser.getParsedTable();

        CellularData datatable;
        int numRows = parsedTable.length;
        int numColumns = parser.getNumberOfYears();
        int startingYear = yearLabels[0];

        datatable = new CellularData(numRows, numColumns, startingYear);

        for (int countryIndex = 0; countryIndex < countryNames.length; countryIndex++) {
            double[] countryData = parsedTable[countryIndex];
            datatable.addCountry(countryNames[countryIndex], countryData);
        }

        System.out.println(datatable);
        double totalSubscriptions;
        int countryNum;
        int requestedStart = 0, requestedEndYear = 0;

        try {
            countryNum = 1;
            requestedStart = 2005;
            requestedEndYear = 2014;
            System.out.printf("Requesting subscriptions for \"%s\" between %d - %d. \n", countryNames[countryNum], requestedStart, requestedEndYear);
            totalSubscriptions = datatable.getNumSubscriptionsInCountryForPeriod(countryNames[countryNum], requestedStart, requestedEndYear);
            System.out.printf("Total subscriptions = %.2f \n\n", totalSubscriptions);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            countryNum = 2;
            requestedStart = 1960;
            requestedEndYear = 2014;
            System.out.printf("Requesting subscriptions for \"%s\" between %d - %d. \n", countryNames[countryNum], requestedStart, requestedEndYear);
            totalSubscriptions = datatable.getNumSubscriptionsInCountryForPeriod(countryNames[countryNum], requestedStart, requestedEndYear);
            System.out.printf("Total subscriptions = %.2f \n\n", totalSubscriptions);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            countryNum = 0;
            requestedStart = 1960;
            requestedEndYear = 2014;
            System.out.printf("Requesting subscriptions for \"%s\" between %d - %d. \n", countryNames[countryNum], requestedStart, requestedEndYear);
            totalSubscriptions = datatable.getNumSubscriptionsInCountryForPeriod(countryNames[countryNum], requestedStart, requestedEndYear);
            System.out.printf("Total subscriptions = %.2f \n\n", totalSubscriptions);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        // TODO: Comment this line if your test input has fewer than countryNum countries.

        try {
            countryNum = 100;
            requestedStart = 1950;
            requestedEndYear = 2014;
            System.out.printf("Requesting subscriptions for \"%s\" between %d - %d. \n", countryNames[countryNum], requestedStart, requestedEndYear);
            totalSubscriptions = datatable.getNumSubscriptionsInCountryForPeriod(countryNames[countryNum], requestedStart, requestedEndYear);
            System.out.printf("Total subscriptions = %.2f \n\n", totalSubscriptions);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }


        try {
            countryNum = 100;
            requestedStart = 5001;
            requestedEndYear = 5000;
            System.out.printf("Requesting subscriptions for \"%s\" between %d - %d. \n", countryNames[countryNum], requestedStart, requestedEndYear);
            totalSubscriptions = datatable.getNumSubscriptionsInCountryForPeriod(countryNames[countryNum], requestedStart, requestedEndYear);
            System.out.printf("Total subscriptions = %.2f \n\n", totalSubscriptions);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            countryNum = 100;
            requestedStart = -5000;
            requestedEndYear = 5000;
            System.out.printf("Requesting subscriptions for \"%s\" between %d - %d. \n", countryNames[countryNum], requestedStart, requestedEndYear);
            totalSubscriptions = datatable.getNumSubscriptionsInCountryForPeriod(countryNames[countryNum], requestedStart, requestedEndYear);
            System.out.printf("Total subscriptions = %.2f \n\n", totalSubscriptions);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }


        System.out.println("\nDone with TestCSVReader.\n");
    }
}
