package com.kalugin.net.view;

import com.kalugin.net.interactor.WeatherNetworkInteractor;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WeatherFxApp extends Application {
    private final WeatherNetworkInteractor weatherNetworkInteractor = new WeatherNetworkInteractor();
    private final Font font = Font.font("Arial", FontWeight.BOLD, 20);
    private final Font labelsFont = Font.font("Arial", FontWeight.NORMAL, 20);
    private String title = "Menu";
    private AnchorPane pane;
    private VBox vBox;
    private HBox hBox;
    public Button findButton;
    private TextField findTextField;

    private final EventHandler<ActionEvent> findEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (findButton == event.getSource()) {
                try {
                    Map<String, String> parameters =
                            weatherNetworkInteractor.get(findTextField.getText());
                    setParameters(parameters);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private void setParameters(Map<String, String> parameters) {
        ObservableList<Node> children = vBox.getChildren();
        vBox.getChildren().removeAll(children);

        if (parameters == null) {
            Label notFoundLabel = new Label("This city is not found!");
            notFoundLabel.setFont(labelsFont);
            vBox.getChildren().add(notFoundLabel);
        } else {
            Label cityLabel = new Label("City:" + "\t" + parameters.get("name"));
            cityLabel.setFont(labelsFont);
            vBox.getChildren().add(cityLabel);

            Label tempLabel = new Label("Temperature:" + "\t" + parameters.get("temp"));
            tempLabel.setFont(labelsFont);
            vBox.getChildren().add(tempLabel);

            Label minTempLabel = new Label("Min temperature:" + "\t" + parameters.get("temp_min"));
            minTempLabel.setFont(labelsFont);
            vBox.getChildren().add(minTempLabel);

            Label maxTempLabel = new Label("Max temperature:" + "\t" + parameters.get("temp_max"));
            maxTempLabel.setFont(labelsFont);
            vBox.getChildren().add(maxTempLabel);

            Label feelsLikeLabel = new Label("Feels like:" + "\t" + parameters.get("feels_like"));
            feelsLikeLabel.setFont(labelsFont);
            vBox.getChildren().add(feelsLikeLabel);

            Label descriptionLabel = new Label("Description:" + "\t" + parameters.get("description"));
            descriptionLabel.setFont(labelsFont);
            vBox.getChildren().add(descriptionLabel);

            Label pressureLabel = new Label("Pressure:" + "\t" + parameters.get("pressure"));
            pressureLabel.setFont(labelsFont);
            vBox.getChildren().add(pressureLabel);

            Label humidityLabel = new Label("Humidity:" + "\t" + parameters.get("humidity"));
            humidityLabel.setFont(labelsFont);
            vBox.getChildren().add(humidityLabel);

            Label windSpeedLabel = new Label("Wind speed:" + "\t" + parameters.get("wind_speed"));
            windSpeedLabel.setFont(labelsFont);
            vBox.getChildren().add(windSpeedLabel);
        }
    }

    @Override
    public void start(Stage stage) {
        pane = new AnchorPane();
        vBox = new VBox(15);
        vBox.setTranslateY(50);

        hBox = new HBox(10);

        findButton = new Button("Find");
        findButton.setOnAction(findEvent);
        findButton.setMaxWidth(500);
        findButton.setMaxHeight(500);
        findButton.setFont(font);

        findTextField = new TextField();
        findTextField.setMaxWidth(500);
        findTextField.setMaxHeight(500);
        findTextField.setFont(font);

        hBox.getChildren().add(findTextField);
        hBox.getChildren().add(findButton);

        AnchorPane.setTopAnchor(hBox, 5.0);
        AnchorPane.setLeftAnchor(hBox, 10.0);
        AnchorPane.setRightAnchor(hBox, 10.0);

        AnchorPane.setTopAnchor(vBox, 5.0);
        AnchorPane.setLeftAnchor(vBox, 10.0);
        AnchorPane.setRightAnchor(vBox, 10.0);

        pane.getChildren().add(hBox);
        pane.getChildren().add(vBox);

        Scene scene = new Scene(pane, 360, 400);
        stage.setScene(scene);
        stage.show();
    }
}
