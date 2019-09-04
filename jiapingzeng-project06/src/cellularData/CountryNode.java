package cellularData;

/**
 * A node in a list containing a Country object
 */
class CountryNode {

    /**
     * Country object stored in the node
     */
    private Country country;

    /**
     * Reference to the next CountryNode in the list
     */
    private CountryNode next;

    /**
     * Creates a country node
     *
     * @param country Country to be presented by the node
     */
    CountryNode(Country country) {
        this.country = country;
    }

    /**
     * Returns Country
     *
     * @return Country
     */
    Country getCountry() {
        return country;
    }

    /**
     * Returns the next CountryNode in the list
     * @return next CountryNode in the list
     */
    CountryNode getNext() {
        return next;
    }

    /**
     * Set next CountryNode
     * @param next next CountryNode
     */
    void setNext(CountryNode next) {
        this.next = next;
    }
}