package sample;

import actors.Houses;
import actors.HousesDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Calendar;
import java.util.List;

public class SalesReport {
    private static String[] propertyName = {"House_id", "Agent_id", "Owner_id", "Buyer_id", "Type", "ListDate", "DealingDate"
            , "SellingPrice", "Street", "City", "Zipcode", "Bedroom_no", "Square_feet" };
    private static String[] propertyLabel = {"House ID", "Agent ID", "Owner ID", "Buyer ID", "Type", "List Date", "Deal Date",
            "Price", "Street", "City", "Zipcode", "Bedrooms", "Square Feet"};
    private static HousesDAO contact = new HousesDAO();
    private static final GridPane gridPane = new GridPane();
    private static final Label lblName = new Label("Search by Name");
    private static final TextField searchField = new TextField();
    private static ObservableList<Houses> observableNames;
    private static FilteredList<Houses> filteredData;
    private static SortedList<Houses> sortedData;
    private static ListView<Houses> listView ;
    static TableView<Houses> contactTableView =
            new TableView<>();
    public SalesReport(long agent_id) {
        lblName.setTextFill(Color.web("#0076a3"));
        observableNames = FXCollections.observableArrayList
                (contact.getHousesForId(agent_id));
        filteredData = new FilteredList<>
                (observableNames, p -> true);
        sortedData = new SortedList<>(filteredData);
        listView = new ListView<>(sortedData);
    }
    public void display(long agent_id) {

        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.setTitle("Mi Casa del Rio");
        primaryStage.setMaximized(true);
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane,650,400,true);
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(lblName, 0, 0);
        gridPane.add(searchField, 0, 1);
        // Search TextField event handling
        /*searchField.textProperty()
                .addListener((observable, oldValue, newValue) ->
                        filteredData.setPredicate(str -> {
                            if (newValue == null || newValue.isEmpty())
                                return true;
                            if (str.getStreet().toLowerCase().contains
                                    (newValue.toLowerCase()))
                                return true;
                            return false;
                        }));
        listView.getSelectionModel().setSelectionMode
                (SelectionMode.SINGLE);
        listView.setPrefHeight(Integer.MAX_VALUE);*/
        // Sets a new cell factory to use in the ListView.
        // This throws away all old list cells and new ListCells
        // created with the new cell factory.
        listView.setCellFactory(listView-> {
            Tooltip tooltip = new Tooltip();
            ListCell<Houses> cell = new
                    ListCell<Houses>() {
                        protected void updateItem(Houses contactPerson,
                                                  Boolean empty) {
                            super.updateItem(contactPerson, empty);
                            if (contactPerson != null) {
                                setText(contactPerson.getStreet());
                                tooltip.setText(contactPerson.getCity());
                                setTooltip(tooltip);
                            } else
                                setText(null);
                        }
                    };
            return cell;
        });
        gridPane.add(listView, 0, 2);
        // Create and initializing TableView
        ObservableList<Houses> contactPeopleList
                = FXCollections.observableArrayList(contact.getHousesForId(agent_id));
        contactTableView.setItems(contactPeopleList);
        contactTableView.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY);
        for (int i = 0; i <
                propertyLabel.length; i++) {

            TableColumn<Houses, Object> col
                    = new TableColumn<>(propertyLabel[i]);
            col.setCellValueFactory(new
                    PropertyValueFactory<>(propertyName[i]));
            contactTableView.getColumns().add(col);

        }
        long totalSales = 0;
        long rent = 0;
        long sale = 0;
        long num_houses;
        long num_rent = 0;
        long num_sale = 0;
        long deal_2019 = 0;
        long deal_2018 = 0;
        long deal_2017 = 0;
        long deal_2020 = 0;
        List<Houses> sales = contact.getHousesForId(agent_id);
        num_houses = sales.size();

        for(int i = 0; i < sales.size(); i++){
            totalSales = totalSales+ sales.get(i).getSellingPrice();
            if(sales.get(i).getType().equals("R")){
                num_rent++;
                rent += sales.get(i).getSellingPrice();
            }
            else{
                num_sale++;
                sale+=sales.get(i).getSellingPrice();
            }
            switch(sales.get(i).getDealingDate().getYear()+1900){
                case 2017:
                    deal_2017++;
                    break;
                case 2018:
                    deal_2018++;
                    break;
                case 2019:
                    deal_2019++;
                    break;
                case 2020:
                    deal_2020++;
                    break;
            }
        }
        VBox report = new VBox(10);
        Label lbl = new Label("Total deals: "+ num_houses );
        Label lbl1 = new Label("Total Sales: "+ totalSales);
        Label lbl2= new Label("Rentals: "+ rent);
        Label lbl3 = new Label("Sales: "+ sale);
        Label lbl4 = new Label("Houses Rented: "+ num_rent);
        Label lbl5 = new Label("Houses Sold: "+ num_sale);
        Label lbl6 = new Label("Sale in 2017-18: "+ deal_2017);
        Label lbl7 = new Label("Sale in 2018-19: "+ deal_2018    );
        Label lbl8 = new Label("Sale in 2019-20: " +deal_2019);
        Label lbl9 = new Label("Sale in 2020-today: " +deal_2020);


        report.getChildren().addAll(lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9);
        Button close_btn = new Button("Close");
        close_btn.setOnAction(e-> primaryStage.close());
        HBox close_hbBtn = new HBox(close_btn);
        close_hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        close_hbBtn.setMargin(close_btn, new Insets(10, 10, 10, 10));
        borderPane.setCenter(contactTableView);
        borderPane.setBottom(close_hbBtn);
        borderPane.setLeft(report);
        // TableView will populate from the contactPeopleList
        // contactPeopleList will have value according to the
        // item selected in the ListView
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
