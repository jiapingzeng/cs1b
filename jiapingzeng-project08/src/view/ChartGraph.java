package view;

import data.DataModel;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Instantiates an JavaFX application which creates a model of the data.
 * Uses the model to instantiate an object of type  javafx.scene.chart.LineChart
 * via the GraphView class. Then sets up the scene with basic modification to
 * the stage.
 *
 * @author Foothill College, Jiaping Zeng
 */
public class ChartGraph extends Application
{	
	/**
	 * Called by launch method of Application
	 * @param stage: Stage
	 */
	@Override
	public void start(Stage stage) 
	{
		// initialize
		BorderPane pane = new BorderPane();
		DataModel cellularDataModel = new DataModel("resources/cellular.csv", "Cellular Data");
		DataModel schoolEnrollmentModel = new DataModel("resources/schoolEnrollment.csv", "School Enrollment");

		// set up graph
		GraphView graphView = new GraphView(cellularDataModel);
		graphView.update();
		pane.setCenter(graphView);

		// set up controls
		VBox vbox = new VBox();
		vbox.setStyle("-fx-border-color: gray; -fx-background-color: lightgray;");

		Text text = new Text("Select a set of data: ");
		text.setFont(new Font(15));
		vbox.getChildren().add(text);
		VBox.setMargin(text, new Insets(5, 5, 5, 5));

		ComboBox comboBox = new ComboBox(FXCollections.observableArrayList("Cellular Data", "School Enrollment"));
		comboBox.getSelectionModel().selectFirst();
		vbox.getChildren().add(comboBox);
		VBox.setMargin(comboBox, new Insets(5, 5, 5, 5));

		Button button = new Button("Update Chart");
		button.setOnAction((ActionEvent e) -> {
			graphView.clearGraph();
			switch (comboBox.getSelectionModel().selectedItemProperty().getValue().toString()) {
				case "Cellular Data":
					graphView.switchModel(cellularDataModel);
					break;
				case "School Enrollment":
					graphView.switchModel(schoolEnrollmentModel);
					break;
			}
			graphView.update();
		});
		vbox.getChildren().add(button);
		VBox.setMargin(button, new Insets(5, 5, 5, 5));

		Button change = new Button("See different countries");
		change.setOnAction((ActionEvent e) -> {
			graphView.clearGraph();
			graphView.changeData();
			graphView.update();
		});
		vbox.getChildren().add(change);
		VBox.setMargin(change, new Insets(5, 5, 5, 5));

		pane.setRight(vbox);

		// render
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.setTitle("GraphView Test");
		stage.show();
	}

	/**
	 * Launches a standalone JavaFx App
	 */
	public static void main(String[] args) 
	{
		launch();
	}
}