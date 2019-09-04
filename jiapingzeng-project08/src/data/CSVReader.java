package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Parses cellular data CSV files
 *
 * @author Jiaping Zeng
 */

public class CSVReader {

    /**
     * List of country names found in the file
     */
    private String[] countryNames;
    /**
     * List of years found in the file
     */
    private int[] yearLabels;
    /**
     * Cellular Data parsed from the file
     */
    private double[][] cellularDataTable;
    /**
     * Index of the country that is currently parsed
     */
    private int countryIndex;

    /**
     * Reads CSV from file
     *
     * @param filename name of CSV file to be read
     */
    public CSVReader(String filename) {
        try {
            File inFile = new File(filename);
            Scanner scanner = new Scanner(inFile);
            boolean hasNumberOfCountries = false;
            boolean hasYearLabels = false;
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (nextLine.startsWith("Number of countries")) {
                    int numberOfCountries = Integer.parseInt(nextLine.split(",")[1]);
                    countryNames = new String[numberOfCountries];
                    cellularDataTable = new double[numberOfCountries][];
                    countryIndex = 0;
                    hasNumberOfCountries = true;
                    continue;
                }
                if (nextLine.startsWith("Country Name")) {
                    parseYearLabels(nextLine);
                    hasYearLabels = true;
                    continue;
                }
                if (hasNumberOfCountries && hasYearLabels) {
                    // if both number of countries and year labels are found, proceed to parse data
                    if (!nextLine.isEmpty()) {
                        parseCountryData(nextLine);
                    }
                }
            }
            if (!hasNumberOfCountries || !hasYearLabels) {
                System.out.println("Invalid file: missing Number of countries or Year labels, exiting program");
                System.exit(0);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File " + filename + " not found, exiting program");
            System.exit(0);
        }
    }

    // ACCESSORS---------------------

    /**
     * Returns number of years included in the table
     *
     * @return number of years included in the table
     */
    public int getNumberOfYears() {
        return yearLabels.length;
    }

    /**
     * Returns list of country names found
     *
     * @return list of country names found
     */
    public String[] getCountryNames() {
        return countryNames;
    }

    /**
     * Returns list of years included in the table
     *
     * @return list of years included in the table
     */
    public int[] getYearLabels() {
        return yearLabels;
    }

    /**
     * Returns parsed cellular data table
     *
     * @return parsed cellular data table
     */
    public double[][] getParsedTable() {
        return cellularDataTable;
    }

    // HELPER METHODS-----------------

    /**
     * parse one line of CSV data
     *
     * @param countryData current line of CSV data
     */
    private void parseCountryData(String countryData) {
        String[] data;
        String countryName;
        if (countryData.startsWith("\"")) {
            int dataStart = countryData.indexOf("\"", 1) + 2;
            countryName = countryData.substring(0, dataStart - 1);
            data = countryData.substring(dataStart).split(",");
        } else {
            String[] split = countryData.split(",");
            countryName = split[0];
            data = Arrays.copyOfRange(split, 1, split.length);
            //data = subStringArray(split, 1, split.length);
        }
        countryNames[countryIndex] = countryName;
        cellularDataTable[countryIndex] = parseDoubleArray(data);
        countryIndex++;
    }

    /**
     * Parse year labels
     *
     * @param countryData current line of CSV data
     */
    private void parseYearLabels(String countryData) {
        String[] data = countryData.split(",");
        if (data.length > 1) {
            int[] parsed = new int[data.length - 1];
            for (int i = 1; i < data.length; i++) {
                try {
                    parsed[i - 1] = Integer.parseInt(data[i]);
                } catch (NumberFormatException e) {
                    System.out.println("Received invalid year label " + data[i] + ", exiting program");
                    System.exit(0);
                }
            }
            yearLabels = parsed;
        } else {
            System.out.println("Error: No year labels found");
        }
    }

    /**
     * Returns a portion of a string array
     *
     * @param array the original array
     * @param from  beginning index (inclusive)
     * @param to    ending index (exclusive)
     * @return the specified sub-array
     */
    private String[] subStringArray(String[] array, int from, int to) {
        int length = to - from;
        String[] result = new String[length];
        for (int i = from; i < to; i++) {
            result[i - from] = array[i];
        }
        return result;
    }

    /**
     * convert a string array into a double array
     * @param array the original array
     * @return double array parsed from the original array
     */
    private double[] parseDoubleArray(String[] array) {
        double[] result = new double[array.length];
        for (int i = 0; i < result.length; i++) {
            try {
                result[i] = Double.parseDouble(array[i]);
            } catch (NumberFormatException e) {
                System.out.println("Received invalid data " + array[i] + ", exiting program");
                System.exit(0);
            }
        }
        return result;
    }
}