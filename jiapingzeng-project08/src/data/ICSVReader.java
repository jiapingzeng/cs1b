package data;

import util.LinkedList;

/**
 * An interface for different CSV readers
 */
public interface ICSVReader {
    /**
     * returns parsed data
     * @return parsed data
     */
    LinkedList<Country> getData();
}
