/**********************************************
 Workshop 6
 Course: JAC 444 - Summer 2022
 Last Name: Himanshu
 First Name: Himanshu
 ID: 146109202
 Section: ZBB
 This assignment represents my own work in accordance with Seneca Academic Policy.
 Signature  Himanshu
 Date: 07/17/2022
 **********************************************/

package com.example.fx_project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class BMI extends Application {
    private final TableView<Data> table = new TableView<Data>();
    private final ObservableList<Data> data =
            FXCollections.observableArrayList(
                    new Data("Below 18.5", "Under weight"),
                    new Data("18.5-24.8", "Normal"),
                    new Data("25.0-29.0", "Overweight"),
                    new Data("30 and Above", "Obese")
            );

    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String bmiCategory(double bmi) {
        if (bmi < 18.5)
            return "Under weight ";
        else if (bmi < 24.8)
            return "Normal";
        else if (bmi < 29.5)
            return "Over weight";
        else
            return "Obese";
    }

    @Override
    public void start(Stage stage) {

        //choose Scale
        Label label = new Label("Choose Scale:");
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
        label.setFont(font);
        RadioButton CMKG = new RadioButton("CM/KG");
        RadioButton InchLb = new RadioButton("Inch/Lb");
        ToggleGroup radioGroup = new ToggleGroup();
        CMKG.setToggleGroup(radioGroup);
        InchLb.setToggleGroup(radioGroup);

        VBox typeVbox = new VBox();
        CMKG.setPadding(new Insets(15, 10, 10, 10));
        InchLb.setPadding(new Insets(15, 10, 10, 10));
        typeVbox.setPadding(new Insets(10, 10, 10, 10));
        typeVbox.setBackground(new Background(new BackgroundFill(Color.SILVER, CornerRadii.EMPTY, Insets.EMPTY)));
        typeVbox.setPrefWidth(100);
        typeVbox.getChildren().addAll(label, CMKG, InchLb);

        //Enter height and weight
        Label heightLbl = new Label("HEIGHT (in centimetre):");
        Label weightLbl = new Label("WEIGHT (in kilograms):");
        TextField heightTxt = new TextField();
        TextField weightTxt = new TextField();
        GridPane input = new GridPane();
        input.addRow(0, heightLbl, heightTxt);
        input.addRow(1, weightLbl, weightTxt);
        input.setHgap(10); //horizontal gap in pixels => that's what you are asking for
        input.setVgap(10);
        input.setPadding(new Insets(20, 20, 20, 20));
        input.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
        VBox choose_inputs = new VBox(20, typeVbox, input);

        //table data ~ bmi rage and status
        TableColumn bmi = new TableColumn("BMI");
        bmi.setMinWidth(150);
        bmi.setSortable(false);
        bmi.styleProperty().setValue("-fx-background-color: gray; -fx-min-height: 20; -fx-font-size: 14pt;");
        bmi.setCellValueFactory(
                new PropertyValueFactory<Data, String>("bmi"));

        TableColumn status = new TableColumn("Weight Status");
        status.setMinWidth(170);
        status.setSortable(false);
        status.styleProperty().setValue("-fx-background-color: gray; -fx-min-height: 20; -fx-font-size: 14pt;");
        status.setCellValueFactory(
                new PropertyValueFactory<Data, String>("weightStatus"));

        table.setMaxWidth(330);
        table.setMaxHeight(240);
        table.setItems(data);
        table.getColumns().addAll(bmi, status);

        table.setBackground(new Background(new BackgroundFill(Color.TAN, CornerRadii.EMPTY, Insets.EMPTY)));
        table.setSelectionModel(null);

        //status and button
        Label sms = new Label("");
        sms.setTextFill(Color.RED);
        sms.setFont(font);
        Button calculate = new Button("Calculate");
        calculate.setFont(font);
        calculate.setPadding(new Insets(10, 40, 10, 40));
        calculate.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));


        HBox top = new HBox();
        top.setSpacing(50);
        top.setPadding(new Insets(10, 50, 10, 40));
        top.getChildren().addAll(choose_inputs, table);
        top.setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY, Insets.EMPTY)));

        //sliders
        Label label_h = new Label("Height");
        label_h.setFont(font);
        Slider slider = new Slider(0, 300, 0);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(15);
        slider.setPadding(new Insets(10, 10, 10, 10));
        slider.setMinHeight(50);

        Label label_w = new Label("Weight");
        label_w.setFont(font);
        Slider w_slider = new Slider(0, 300, 0);
        w_slider.setShowTickMarks(true);
        w_slider.setShowTickLabels(true);
        w_slider.setMajorTickUnit(15);
        w_slider.setBlockIncrement(10);
        w_slider.setPadding(new Insets(10, 10, 10, 10));
        w_slider.setMinHeight(50);


        VBox box = new VBox(top, sms, calculate, label_h, slider, label_w, w_slider);
        box.setPadding(new Insets(10, 10, 10, 10));
        box.setSpacing(10);
        Scene scene = new Scene(box, 820, 540);


        //event change
        radioGroup.selectedToggleProperty().addListener(
                (observable, oldToggle, newToggle) -> {
                    if (newToggle == CMKG) {
                        heightLbl.setText("HEIGHT (in centimetre):");
                        weightLbl.setText("WEIGHT (in kilograms):");
                    } else if (newToggle == InchLb) {
                        heightLbl.setText("HEIGHT (in inches):");
                        weightLbl.setText("WEIGHT (in pounds):");
                    }
                }
        );

        //calculate event
        calculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sms.setText("");
                sms.setTextFill(Color.RED);
                String h = heightTxt.getText();
                String w = weightTxt.getText();
                if (h.trim().isEmpty() || w.trim().isEmpty()) {
                    sms.setText("Error: Height and weight is required");
                } else {
                    sms.setText("yes");

                    if (isNumeric(h) && isNumeric(w)) {
                        double m_height = Double.parseDouble(h);
                        double m_weight = Double.parseDouble(w);
                        if (m_weight >= 0 && m_height >= 0) {
                            if (CMKG.isSelected()) {
                                double m_BMI = (m_weight / (m_height * m_height)) * 10000;
                                sms.setText("BMI: " + String.format("%.2f", m_BMI) + ", Status: You are " + bmiCategory(m_BMI));
                                sms.setTextFill(Color.GREEN);
                                slider.setValue(m_height);
                                w_slider.setValue(m_weight);
                            } else if (InchLb.isSelected()) {
                                double m_BMI = 703 * (m_weight / (m_height * m_height));
                                sms.setText("BMI: " + String.format("%.2f", m_BMI) + ", Status:  You are " + bmiCategory(m_BMI));
                                sms.setTextFill(Color.GREEN);
                                slider.setValue(m_height);
                                w_slider.setValue(m_weight);
                            } else {
                                sms.setText("Error: choose scale please");
                            }
                        } else {
                            sms.setText("Error: Height and weight should be between 0-200");
                        }
                    } else {
                        sms.setText("Error: Height and weight should be a positive number");
                    }
                }
            }
        });

        stage.setTitle("BMI calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}