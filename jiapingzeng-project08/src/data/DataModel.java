package data;

import util.LinkedList;
import view.CountrySelector;

/**
 * Provides access to CSV data.
 * @author Foothill College, Bita M., Jiaping Zeng
 */
public class DataModel 
{
	/**
	 * model to be returned
	 */
	private Country[] model;

	/**
	 * name of x-axis
	 */
	private String xAxisName;

	/**
	 * name of y-axis
	 */
	private String yAxisName;

	/**
	 * data parsed from file
	 */
	private LinkedList<Country> data;

	/**
	 * size of data to be displayed each time
	 */
	private final int REQUESTED_SIZE = 5;

	/**
	 * creates a model with a file
	 * @param filename file to be read
	 * @param dataType type of CSV
	 */
	public DataModel(final String filename, String dataType)
	{
		parseCSVFile(filename, dataType);
		if (data != null) {
			selectData();
		} else {
			System.out.println("Unable to parse file, exiting program");
			System.exit(0);
		}
	}

	/**
	 * parses given CSV file
	 * @param filename file to be parsed
	 * @param dataType type of CSV
	 */
	private void parseCSVFile(final String filename, String dataType) {
		ICSVReader parser;
		switch (dataType) {
			case "Cellular Data":
				xAxisName = "Year";
				yAxisName = "Cellular subscriptions";
				parser = new CellularDataCSVReader(filename);
				break;
			case "School Enrollment":
				xAxisName = "Year";
				yAxisName = "School Enrollment (% net)";
				parser = new SchoolEnrollmentCSVReader(filename);
				break;
			default:
				// file is not a known type (dataType is incorrect)
				return;
		}
		data = parser.getData();
	}

	/**
	 * re-select data to be displayed
	 */
	public void selectData() {
		CountrySelector cs = new CountrySelector(data, REQUESTED_SIZE);
		model = convertToArray(cs.selectCountries());
	}

	/**
	 * converts a LinkedList of Country objects into an array
	 * @param countries list to be converted
	 * @return converted array
	 */
	private Country[] convertToArray(LinkedList<Country> countries) {
		Country[] converted = new Country[countries.size()];
		int i = 0;
		for (Country country : countries) {
			converted[i] = country;
			i++;
		}
		return converted;
	}

	/**
	 * returns parsed model
	 * @return parsed model
	 */
	public Country[] getModel() {
		return model;
	}

	/**
	 * returns x-axis name
	 * @return x-axis name
	 */
	public String getXAxisName() {
		return xAxisName;
	}

	/**
	 * returns y-axis name
	 * @return y-axis name
	 */
	public String getYAxisName() {
		return yAxisName;
	}
}
