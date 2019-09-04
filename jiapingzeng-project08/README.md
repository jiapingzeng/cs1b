### Project folder:
jiapingzeng-project08/

### Description and explanation:

Couldn't decide how I would organize the way data is processed. Not sure if this iteration is the best approach, but I think it works well.

The program has two sets of data, cellular subscription and school enrollment. When the program is first run, the cellular data of 5 randomly selected countries are displayed. The user then has the option to switch to school enrollment data set, or to randomly select 5 new countries.

Initially, I used the old CSV parser from the previous projects, which returns an array of Country objects. However, the second data file I downloaded from data.worldbank.org did not provide the number of countries, so I decided to make a new parser that would return a LinkedList instead (spent a lot of my time on this part because I didn't know the CSV file had an invisible character at index 0). From there, I realized that it would be easier if I made the cellular data CSV parser also return LinkedList.

At some point, I stored SubscriptionYear and EnrollmentYear objects in Country, but realized that since both of them are just an int and a double, it would be easier to use one model (DataYear) to store both types, then specify which type while reading the files.

If I had more time I would've made a textbox which asks the user how many countries they want to display on the chart (right now it displays 5 countries, as set by a final int in DataModel).

### Submitted files:

#### package data:
* CellularDataCSVReader.java
    - parses cellular data CSV files, rewritten from original CSVReader.java used in previous projects. Now outputs a LinkedList of type Countries instead of an array
* SchoolEnrollmentCSVReader.java
    - parses school enrollment CSV files, very similar to CellularDataCSVReader. I could not find a way to parse two different CSV formats with one parser.
* DataYear.java
    - new type that stores numerical value (doubles) of annual data, replaces SubscriptionYear.java
* Country.java
    - modified to use DataYear instead of SubscriptionYear
* DataModel
    - Converts CSV files into data ready to be graphed
* CSVReader.java (obsolete)
    - original CSV parser that outputs an array of Countries, replaced by CellularDataCSVReader
* SubscriptionYear.java (obsolete, not changed from project 7)
    - type that stores annual data in previous projects, replaced by DataYear
* EnrollmentYear.java (obsolete)
    - my initial approach to handling two types of data, decided to use DataYear instead

#### package util:
* LinkedList.java (not changed from project 7)
* Node.java (not changed from project 7)

#### package view:
* ChartGraph.java
* CountrySelector.java (not modified)
* GraphView.java

#### other files
* screenshot1.jpg: display cellular data
* screenshot2.jpg: switch to display school enrollment data by selecting it from the dropdown menu
* screenshot3.jpg: display different countries within the same data by clicking a button
* schoolEnrollment.csv: CSV data of school enrollment percentage (obtained from http://data.worldbank.org)
* schoolEnrollment_test.csv: forged data file used to test parser
* README.md: description of submitted files