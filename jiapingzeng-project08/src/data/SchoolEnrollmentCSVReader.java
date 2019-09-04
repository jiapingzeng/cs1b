package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import util.LinkedList;

/**
 * Parses a school enrollment CSV file and exports a LinkedList of Country objects
 */
public class SchoolEnrollmentCSVReader implements ICSVReader {

    /**
     * list of country containing school enrollment data
     */
    private LinkedList<Country> schoolEnrollmentData;

    /**
     * year labels
     */
    private int[] yearLabels;

    /**
     * Reads a CSV file and parses it
     * @param filename file to be read
     */
    public SchoolEnrollmentCSVReader(String filename) {
        schoolEnrollmentData = new LinkedList<>();
        try {
            File inFile = new File(filename);
            Scanner scanner = new Scanner(inFile);
            boolean hasYearLabels = false;
            while(scanner.hasNextLine()) {
                // apparently some csv files start with an unprintable empty control character, this removes that so code works properly
                String nextLine = scanner.nextLine().replaceAll("\\p{C}", "");

                if (nextLine.startsWith("Series Name")) {
                    // parse year labels
                    parseYearLabels(nextLine);
                    hasYearLabels = true;
                } else if (hasYearLabels && nextLine.startsWith("\"School enrollment, primary (% net)\"")) {
                    // parse data
                    Country country = parseCountryData(nextLine);
                    schoolEnrollmentData.add(country);
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
        // real data starts from char position 49
        line = line.substring(49);
        String countryName;
        String[] data;
        if (line.startsWith("\"")) {
            int dataStart = line.indexOf("\"", 1) + 2;
            countryName = line.substring(0, dataStart - 1);
            String[] split = line.substring(dataStart).split(",");
            data = Arrays.copyOfRange(split, 1, split.length);
            //data = subStringArray(split, 1);
        } else {
            String[] split = line.split(",");
            countryName = split[0];
            data = Arrays.copyOfRange(split, 2, split.length);
            //data = subStringArray(split, 2);
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
        // year label starts at position 4
        if (data.length > 4) {
            data = Arrays.copyOfRange(data, 4, data.length);
            //data = subStringArray(data, 4);
            int[] parsed = new int[data.length];
            for (int i = 0; i < data.length; i++) {
                try {
                    parsed[i] = Integer.parseInt(data[i].substring(0,4));
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
     * convert a string array into a double array
     * @param array the original array
     * @return double array parsed from the original array
     */
    private double[] parseDoubleArray(String[] array) {
        double[] result = new double[array.length];
        for (int i = 0; i < result.length; i++) {
            try {
                result[i] = Double.parseDouble(array[i].replace("..", "0"));
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
        return schoolEnrollmentData;
    }
}
