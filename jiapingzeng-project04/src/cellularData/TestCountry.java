package cellularData;
/**
 *  Tests the Country class by creating multiple objects.
 *  Creates one object of type CSVReader class, which reads input from a CSV file. Uses
 *  the attributes stored in CSVReader object to create objects of type Country class.
 *
 * @author Foothill College, Jiaping Zeng
 */
class TestCountry
{
	/**
	 * Displays the names of countries. 
	 * Each country index is output along with the country's index in the array.
	 * @param countries	array of Country objects
	 */
	private void displayCountryNames(Country[] countries)
	{
		String countryNames = "";
		int counter = 0;

		for (int i = 0; i < countries.length; i++)
		{
			// Concatenates the name of countries.
			countryNames += " " + countries[i].getName();

			// uses a ternary operator to prettify the output
			countryNames += (counter+1) % 5 == 0 ? "\n" : ",";

			counter++;
		}

		System.out.println("\nCountry Names:" + countryNames + "\n");
	}


	/**
	 * Includes test examples for class Country.
	 */
	public static void main(String[] args) 
	{
		//final String FILENAME = "resources/cellular_short_oneDecade.csv";

		final String FILENAME = "resources/cellular.csv";

		// Parses the CSV data file
		// NOTE: Handle all exceptions in the constructor.
		//       For full credit, do *not* throw exceptions to main. 
		CSVReader parser = new CSVReader(FILENAME);

		// In class CSVReader the accessor methods only return values of instance variables.
		String [] countryNames = parser.getCountryNames();
		int [] yearLabels = parser.getYearLabels();
		double [][] parsedTable = parser.getParsedTable();		


		Country[] countries;

		countries = new Country[countryNames.length];				 

		// Reference to a Country object
		Country current;

		// Go through each country name parsed from the CSV file.
		for (int countryIndex = 0; countryIndex < countries.length; countryIndex++)
		{
			int numberOfYears = yearLabels.length;   // OR numberOfYears = dataTable[countryIndex].length;

			// Create a Country object
			current = new Country(countryNames[countryIndex], numberOfYears);

			// Go through each year of cellular data read from the CSV file.
			for (int yearIndex = 0; yearIndex < numberOfYears; yearIndex++)
			{
				double[] allSubscriptions = parsedTable[countryIndex];
				double countryData = allSubscriptions[yearIndex];

				current.addSubscriptionYear(yearLabels[yearIndex], countryData);
			}

			// test toString() method
			System.out.println(current);

			// add the newly created country to the 1D array
			countries[countryIndex] = current;
		}

		// Provides methods for testing the data table of Country objects.
		TestCountry application = new TestCountry();

		// Displays the name of each Country object
		application.displayCountryNames(countries);
		// Given the cellular_short_oneDecade.csv file, the output is:
		// Country Names: Bangladesh, Bahamas, The, Brazil, Germany,


		// Tests finding a country and retrieving subscriptions between a requested period 
		// 
		double totalSubscriptions;
		int countryNum;
		int requestedStart = 0, requestedEndYear = 0;
		
		try 
		{
			countryNum = 0;
			requestedStart = 1960;
			requestedEndYear = 2014;
			System.out.printf("Requesting subscriptions for \"%s\" between %d - %d. \n", countryNames[countryNum], requestedStart, requestedEndYear);
			totalSubscriptions = countries[countryNum].getNumSubscriptionsForPeriod(requestedStart,requestedEndYear);
			System.out.printf("Total subscriptions = %.2f \n\n", totalSubscriptions);
		}
		catch (IllegalArgumentException ex) 
		{
			System.out.println(ex.getMessage());
		}
		// Given the full cellular.csv file, the output is:
		// Requesting subscriptions for "Afghanistan" between 1960 - 2014. 
		// Total subscriptions = 420.07 

		try 
		{
			countryNum = 100;
			requestedStart = 1950;
			requestedEndYear = 2014;
			System.out.printf("Requesting subscriptions for \"%s\" between %d - %d. \n", countryNames[countryNum], requestedStart, requestedEndYear);
			totalSubscriptions = countries[countryNum].getNumSubscriptionsForPeriod(requestedStart,requestedEndYear);
			System.out.printf("Total subscriptions = %.2f \n\n", totalSubscriptions);
		} 
		catch (IllegalArgumentException ex) 
		{
			System.out.println(ex.getMessage());
		}
		// Given the full cellular.csv file, the output is:
		// Requesting subscriptions for "Hong Kong SAR, China" between 1950 - 2014. 
		// Illegal Argument Request of year range 1950-2014. Valid period for Hong Kong SAR, China is 1960 to 2014.
		// Total subscriptions between 1960 - 2014 = 2561.16 
		//
		// NOTE: For a more user friendly output adjust the invalid requested range to a valid range.
		//       Then, inform the user of the adjusted date and the total subscriptions.

		try 
		{
			countryNum = 200;
			requestedStart = 2010;
			requestedEndYear = 2014;
			System.out.printf("Requesting subscriptions for \"%s\" between %d - %d. \n", countryNames[countryNum], requestedStart, requestedEndYear);
			totalSubscriptions = countries[countryNum].getNumSubscriptionsForPeriod(requestedStart,requestedEndYear);
			System.out.printf("Total subscriptions = %.2f \n\n", totalSubscriptions);
		}
		catch (IllegalArgumentException ex) 
		{
			System.out.println(ex.getMessage());
		}
		// Given the full cellular.csv file, the output is:
		// Requesting subscriptions for "Rwanda" between 2010 - 2014. 
		// Total subscriptions = 243.14

		// Testing adding extra data
		Country fakeCountry = new Country("F@k3C0untry1", 3);
		System.out.println(fakeCountry);
		System.out.println(fakeCountry.getNumSubscriptionsForPeriod(1, 3));

		fakeCountry.addSubscriptionYear(1, 1);
		fakeCountry.addSubscriptionYear(2, 1);
		fakeCountry.addSubscriptionYear(3, 1);
		fakeCountry.addSubscriptionYear(4, 1);
		System.out.println(fakeCountry.getNumSubscriptionsForPeriod(1, 3));


		// Testing adding country data in different order
		fakeCountry = new Country("F@k3C0untry2", 3);
		System.out.println(fakeCountry);
		System.out.println(fakeCountry.getNumSubscriptionsForPeriod(1, 3));

		fakeCountry.addSubscriptionYear(1, 1);
		fakeCountry.addSubscriptionYear(3, 1);
		fakeCountry.addSubscriptionYear(2, 1);
		System.out.println(fakeCountry.getNumSubscriptionsForPeriod(1, 3));

		// Testing adding non-continuous data
		fakeCountry = new Country("F@k3C0untry3", 3);
		System.out.println(fakeCountry);
		System.out.println(fakeCountry.getNumSubscriptionsForPeriod(1, 3));

		fakeCountry.addSubscriptionYear(1, 1);
		fakeCountry.addSubscriptionYear(100, 1);
		fakeCountry.addSubscriptionYear(101, 1);
		System.out.println(fakeCountry.getNumSubscriptionsForPeriod(1, 3));
	}
}
