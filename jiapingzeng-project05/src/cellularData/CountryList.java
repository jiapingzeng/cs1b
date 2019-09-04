package cellularData;

/**
 * A list of CountryNodes that stores information of countries
 */
public class CountryList {

    /**
     * Reference to the first CountryNode
     */
    private CountryNode head;

    /**
     * size of the list
     */
    private int size;

    /**
     * Initialize list
     */
    CountryList() {
        size = 0;
    }

    /**
     * Adds a new Country to the list
     *
     * @param country Country to be added
     */
    void add(Country country) {
        CountryNode newNode = new CountryNode(country);
        if (size == 0) {
            head = newNode;
        } else {
            CountryNode current = head;
            for (int i = 0; i < size - 1; i++) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    /**
     * Add Country at specific index
     *
     * @param country Country to be added
     * @param index   index to insert Country at
     * @throws IndexOutOfBoundsException if index is lower than 0
     */
    void insertAtIndex(Country country, int index) throws IndexOutOfBoundsException {
        if (index < 0) throw new IndexOutOfBoundsException();
        else if (index > size) {
            System.out.println("Index out of boundary, adding to the end of list instead");
            add(country);
            return;
        } else if (index == 0) {
            CountryNode newNode = new CountryNode(country);
            CountryNode oldHead = head;
            head = newNode;
            head.setNext(oldHead);
        } else {
            CountryNode current = head.getNext();
            for (int i = 1; i < index; i++) {
                if (i == index - 1) {
                    CountryNode newNode = new CountryNode(country);
                    CountryNode oldNode = current.getNext();
                    current.setNext(newNode);
                    newNode.setNext(oldNode);
                }
                current = current.getNext();
            }
        }
        size++;
    }

    /**
     * Finds and returns the Country object at specific index
     * @param index index of the Country
     * @return Country object found at index
     * @throws IndexOutOfBoundsException if index is lower than 0 or higher than list size
     */
    Country getIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        CountryNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getCountry();
    }

    /**
     * Checks if a specified Country is in the list
     * @param country Country to look for
     * @return Country found
     */
    Country contains(Country country) {
        CountryNode current = head;
        for (int i = 0; i < size; i++) {
            Country currentCountry = current.getCountry();
            if (currentCountry.equals(country)) {
                return currentCountry;
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * Prints every Country in the list
     * @return string of Country information
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        CountryNode current = head;
        for (int i = 0; i < size; i++) {
            sb.append(current.getCountry());
            sb.append('\n');
            current = current.getNext();
        }
        return sb.toString();
    }

    /**
     * Returns size of list
     * @return size of list
     */
    int size() {
        return size;
    }
}
