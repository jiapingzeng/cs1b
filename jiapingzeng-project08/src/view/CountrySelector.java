package view;

import java.util.Random;
import data.Country;
import util.LinkedList;

/**
 * selects random countries from a large set of data to be displayed
 */
public class CountrySelector 
{
	private LinkedList<Country> selectedCountries;

	/**
	 * Builds a linked list of random countries
	 * @param allCountries      An array of Country objects
	 * @param requestedSize	The requested number of elements.
	 */
	public CountrySelector(Country[] allCountries, int requestedSize)
	{
		// Build the list out of a random selection of countries.
		Random random = new Random();

		// A singly linked list of country data.
		selectedCountries = new LinkedList<>();
		for (int i = 0; i < requestedSize; i++)
		{
			int selectedIndex = random.nextInt(allCountries.length);
			selectedCountries.add(allCountries[selectedIndex]);
		}
	}

	/**
	 * Selects from a LinkedList instead of an array
	 * @param allCountries list of Country objects
	 * @param requestedSize requested number of elements
	 */
	public CountrySelector(LinkedList<Country> allCountries, int requestedSize) {
		Random random = new Random();
		selectedCountries = new LinkedList<>();
		for (int i = 0; i < requestedSize; i++) {
			// not efficient
			int selectedIndex = random.nextInt(allCountries.size());
			selectedCountries.add(allCountries.getIndex(selectedIndex));
		}
	}

	/**
	 * Accessor method for randomly selected list of countries.
	 * @return util.LinkedList of Country objects.
	 */
	public LinkedList<Country> selectCountries()
	{
		return this.selectedCountries;
	}
}
