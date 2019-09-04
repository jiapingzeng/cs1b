package cellularData;

import java.util.Random;
import java.util.Scanner;

/**
 *  Tests the CountryList class by creating multiple objects of type CountryNode.
 *  Creates one object of type CSVReader class, which reads input from a CSV file. Uses
 *  the attributes stored in CSVReader object to create objects of type Country class.
 *  Then adds the newest country read into the list
 *
 * @author Foothill College, Jiaping Zeng
 */
public class TestCountryList
{
	/**
	 * Builds a random list of countries.
	 * @param allCountries      An array of Country objects
	 */
	private void testRandomListOfCountries(Country [] allCountries)
	{
		// Prompts the user for the number of countries they want to add to the list.
		Scanner keyboard = new Scanner(System.in);
		System.out.println("How many countries do you want to add to the list?");
		int requestedSize = Integer.parseInt( keyboard.nextLine() );

		// Build the list out of a random selection of countries.
		Random random = new Random();

		// TODO: Define the CountryNode class to hold the data for one Country object 
		//       and a reference to the next node.
		//       Define the CountryList class to be a singly list list of CountryNode objects.
		CountryList selectedCountries = new CountryList();

		for (int i = 0; i < requestedSize; i++)
		{
			// Selects a random index of the Country data array
			int selectedIndex = random.nextInt(allCountries.length);

			// TODO: Define the add() method in class such that it adds the randomly
			//       selected Country to the *end* of the list.
			Country countryToAdd = allCountries[selectedIndex];
			System.out.printf("Adding country with name %s to the end of the list.\n", countryToAdd.getName());
			selectedCountries.add(countryToAdd);
		}


		// TODO: Override the toString() method such that it traverses the list of nodes
		//       and prints every element in the list.
		// Note: To debug your list, do not comment this line out
		System.out.println("\nList of countries: " + selectedCountries);
		// Assuming the user selects to add 3 countries to the list, the output would be
		// List of countries: 
		// Brazil		46.31	53.11	63.67	78.55	87.54	100.88	119.00	125.00	135.30	138.95	
		// Germany		94.55	102.28	115.14	126.55	126.22	106.48	109.65	111.59	120.92	120.42	
		// Bangladesh	6.28	13.20	23.46	30.16	34.35	44.94	55.19	62.82	74.42	80.03

		// testing valid index
		try 
		{
			int index = 0;
			Country first = selectedCountries.getIndex(index);
			System.out.println ("\nfirst: " + first);
		} 
		catch (IndexOutOfBoundsException ex) 
		{
			System.out.println("ERROR: Index out of the range of values.");
		}
		try 
		{
			int index = selectedCountries.size()/2;
			Country middle = selectedCountries.getIndex(index);
			System.out.println ("middle: " + middle);
		} catch (IndexOutOfBoundsException ex) 
		{
			System.out.println("ERROR: Index out of the range of values.");
		}
		try 
		{
			int index = selectedCountries.size()-1;
			Country last = selectedCountries.getIndex(index);
			System.out.println ("last: " + last);
		} catch (IndexOutOfBoundsException ex) 
		{
			System.out.println("ERROR: Index out of the range of values.");
		}

		try 
		{
			int index = -100;
			Country last = selectedCountries.getIndex(index);
			System.out.println ("invalid: " + last);
		} catch (IndexOutOfBoundsException ex) 
		{
			System.out.println("ERROR: Index out of the range of values.");
		}

		System.out.println("\nWhat country do you want to search for?");
		String countryNameToFind = keyboard.nextLine();


		Country tmpCountry = new Country(countryNameToFind);
		Country foundCountry = selectedCountries.contains(tmpCountry);
		if (foundCountry != null)
		{
			System.out.println("Country " + countryNameToFind + " found with details:\n" + foundCountry);
		}
		else
		{
			System.out.println("Country " + countryNameToFind + " not found.");
		}


		// Testing adding to front
		CountryList list = new CountryList();
		list.add(new Country("a"));
		list.add(new Country("b"));
		list.add(new Country("c"));
		list.insertAtIndex(new Country("d"), 0);
		System.out.println(list);

		// Testing adding in the middle
		list = new CountryList();
		list.add(new Country("a"));
		list.add(new Country("b"));
		list.add(new Country("c"));
		list.insertAtIndex(new Country("d"), 2);
		System.out.println(list);

		// Testing adding to the end
		list = new CountryList();
		list.add(new Country("a"));
		list.add(new Country("b"));
		list.add(new Country("c"));
		list.insertAtIndex(new Country("d"), 3);
		System.out.println(list);

		// Testing adding out of bound
		list = new CountryList();
		list.add(new Country("a"));
		list.add(new Country("b"));
		list.add(new Country("c"));
		list.insertAtIndex(new Country("d"), 9999999);
		System.out.println(list);
	}

	/**
	 * Uses a CSV to parse a CSV file.
	 * Adds the data for each country to an array of Country objects.
	 * Adds a random selection of countries to a CountryList object.
	 */
	public static void main(String[] args)
	{
		// NOTE: Make sure to use relative path instead of specifying the entire path
		// (avoid entire path such as /Users/alicew/myworkspace/so_on_and_so_forth).
		final String FILENAME = "resources/cellular_short_oneDecade.csv";	// Directory path for Mac OS X

		// TODO: Make sure to test with the full input file as well
		//final String FILENAME = "resources/cellular.csv"; // Directory path for Mac OS X
		// final String FILENAME = "resources\\cellular.csv"; // Directory path for Windows OS (i.e. Operating System)

		// For debugging purposes
		final int NUM_COUNTRIES_TO_TEST = 4;			// Note: Include test cases in addition to 4    

		// Parses the CSV data file
		// NOTE: Handle all exceptions in the constructor.
		//       For full credit, do *not* throw exceptions to main. 
		CSVReader parser = new CSVReader(FILENAME);

		// In class CSVReader the accessor methods only return values of instance variables.
		String [] countryNames = parser.getCountryNames();
		int [] yearLabels = parser.getYearLabels();
		double [][] parsedTable = parser.getParsedTable();


		// Holds the data for all Country object read from the input data file.
		Country [] countries;

		// Initializes the to the number of entries read by CSVReader.
		countries = new Country[countryNames.length];

		// Note: If you are debugging, use this version instead to limit the number of countries
		// countries = new Country[NUM_COUNTRIES_TO_TEST];

		// Reference to a Country object
		Country current;

		// Go through each country name parsed from the CSV file.
		for (int countryIndex = 0; countryIndex < countries.length; countryIndex++)
		{
			int numberOfYears = yearLabels.length;   // OR numberOfYears = dataTable[countryIndex].length;

			// Create a Country object.
			current = new Country(countryNames[countryIndex], numberOfYears);

			// Go through each year of cellular data read from the CSV file.
			for (int yearIndex = 0; yearIndex < numberOfYears; yearIndex++)
			{
				double [] allSubscriptions = parsedTable[countryIndex];
				double countryData = allSubscriptions[yearIndex];
				current.addSubscriptionYear(yearLabels[yearIndex], countryData);
			}

			// Add the newly created country to the 1D array.
			countries[countryIndex] = current;
		}

		// Creates an object of our current application, for testing purposes.
		TestCountryList application = new TestCountryList();

		// Note: Initially, to test your output you may hard code the number of 
		//       countries added, and the array positions selected.
		//		 However, make sure to comment this out before submitting your work.
		//CountryList listOfCountries = application.debugListOfCountries(countries);

		// Tests the CountryLinkedList class by adding a random number of entries
		// from the array of Country objects.
		application.testRandomListOfCountries(countries);

		// Flush the error stream.
		System.err.flush();

		System.out.println("\nDone with TestCountryList.");
	}
}