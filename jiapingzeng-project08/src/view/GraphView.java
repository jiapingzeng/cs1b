package view;

import data.Country;
import data.DataModel;
import data.DataYear;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * Displays a set of data with a LineChart
 */
public class GraphView extends LineChart {

    /**
     * x-axis of the chart
     */
    NumberAxis xAxis;

    /**
     * y-axis of the chart
     */
    NumberAxis yAxis;

    /**
     * data to be displayed
     */
    DataModel model;

    /**
     * displays data with a chart
     * @param model data to be displayed
     */
    public GraphView(DataModel model) {
        this(new NumberAxis(), new NumberAxis(), model);
    }

    /**
     * displays data with a chart
     * @param xAxis x-axis of chart
     * @param yAxis y-axis of chart
     * @param model data to be displayed
     */
    public GraphView(NumberAxis xAxis, NumberAxis yAxis, DataModel model) {
        super(xAxis, yAxis);
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.model = model;
        this.xAxis.setForceZeroInRange(false);
    }

    /**
     * converts a country into a series
     * @param country country to be read
     * @return series read
     */
    Series<Integer, Integer> seriesFromCountry(Country country) {
        XYChart.Series<Integer, Integer> series = new XYChart.Series<>();
        series.setName(country.getName());
        for (DataYear dataYear : country.getData()) {
            if (dataYear.getData() > 0) {
                series.getData().add(new XYChart.Data(dataYear.getYear(), dataYear.getData()));
            }
        }
        return series;
    }

    /**
     * update chart
     */
    void update() {
        this.xAxis.setLabel(model.getXAxisName());
        this.yAxis.setLabel(model.getYAxisName());
        for (Country country : model.getModel()) {
            this.getData().add(seriesFromCountry(country));
        }
    }

    /**
     * switch to a different set of data
     * @param newModel
     */
    void switchModel(DataModel newModel) {
        this.model = newModel;
    }

    /**
     * show different countries in the same data set
     */
    void changeData() {
        this.model.selectData();
    }

    /**
     * clear the chart
     */
    void clearGraph() {
        this.getData().removeAll(this.getData());
    }
}
