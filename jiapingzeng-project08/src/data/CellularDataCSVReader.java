package data;

import util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Parses a cellular data CSV file and exports a LinkedList of Country objects
 */
public class CellularDataCSVReader implements ICSVReader {

    /**
     * list of country containing cellular data
     */
    private LinkedList<Country> cellularData;

    /**
     * year labels
     */
    private int[] yearLabels;

    /**
     * Reads a CSV file and parses it
     * @param filename file to be read
     */
    public CellularDataCSVReader(String filename) {
        cellularData = new LinkedList<>();
        try {
            File inFile = new File(filename);
            Scanner scanner = new Scanner(inFile);
            boolean hasYearLabels = false;
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine().replaceAll("\\p{C}}", "");
                if (nextLine.startsWith("Country Name")) {
                    parseYearLabels(nextLine);
                    hasYearLabels = true;
                } else if (hasYearLabels && !nextLine.startsWith("Number of countries") && !nextLine.isEmpty()) {
                    Country country = parseCountryData(nextLine);
                    cellularData.add(country);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + filename + " not found, exiting program");
            System.exit(0);
        }
    }

    /**
     * converts a line of CSV country data into a Country object
     * @param line line to be parsed
     * @return Country parsed
     */
    private Country parseCountryData(String line) {
        String[] data;
        String countryName;
        if (line.startsWith("\"")) {
            int dataStart = line.indexOf("\"", 1) + 2;
            countryName = line.substring(0, dataStart - 1);
            data = line.substring(dataStart).split(",");
        } else {
            String[] split = line.split(",");
            countryName = split[0];
            data = Arrays.copyOfRange(split, 1, split.length);
        }
        Country parsedCountry = new Country(countryName);
        double[] parsedData = parseDoubleArray(data);
        for (int i = 0; i < yearLabels.length; i++) {
            parsedCountry.addDataYear(yearLabels[i], parsedData[i]);
        }
        return parsedCountry;
    }

    /**
     * converts a line of CSV year labels into an array
     * @param line line to be parsed
     */
    private void parseYearLabels(String line) {
        String[] data = line.split(",");
        if (data.length > 1) {
            data = Arrays.copyOfRange(data, 1, data.length);
            int[] parsed = new int[data.length];
            for (int i = 0; i < data.length; i++) {
                try {
                    parsed [i] = Integer.parseInt(data[i].substring(0,4));
                } catch (NumberFormatException e) {
                    System.out.println("Received invalid year label " + data[i] + ", exiting program");
                    System.exit(0);
                }
            }
            yearLabels = parsed;
        } else {
            System.out.println("Invalid file: Missing year labels, exiting program");
            System.exit(0);
        }
    }

    /**
     * converts a string array to a double array
     * @param array string array to be parsed
     * @return parsed double array
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

    /**
     * returns parsed data
     * @return parsed data
     */
    @Override
    public LinkedList<Country> getData() {
        return cellularData;
    }
}
