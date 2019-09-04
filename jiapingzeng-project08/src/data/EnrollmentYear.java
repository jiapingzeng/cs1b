package data;

/**
 * School enrollment data of a specific year
 *
 * @author Jiaping Zeng
 */

public class EnrollmentYear {

    /**
     * year
     */
    private int year;

    /**
     * number of enrollments in the year
     */
    private double enrollments;

    /**
     * stores number of enrollments in a particular year
     * @param year year
     * @param enrollments number of subscriptions
     */
    public EnrollmentYear(int year, double enrollments) {
        this.year = year;
        this.enrollments = enrollments;
    }

    /**
     * clones from another EnrollmentYear
     * @param enrollmentYear EnrollmentYear to clone from
     */
    public EnrollmentYear(EnrollmentYear enrollmentYear) {
        year = enrollmentYear.getYear();
        enrollments = enrollmentYear.getEnrollments();
    }

    /**
     * returns number of enrollments as a string
     * @return number of enrollments as a string
     */
    @Override
    public String toString() {
        return Double.toString(enrollments);
    }

    // ACCESSORS----------------------

    /**
     * returns year
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * set year
     * @param year year of data to be stored
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * returns number of enrollments in the year
     * @return number of enrollments
     */
    public double getEnrollments() {
        return enrollments;
    }

    /**
     * set number of enrollments in the year
     * @param enrollments number of enrollments
     */
    public void setEnrollments(double enrollments) {
        this.enrollments = enrollments;
    }
}
