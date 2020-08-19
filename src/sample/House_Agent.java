package sample;

import actors.Buyer;
import actors.Houses;
import actors.HousesDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;

public class House_Agent {
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
    private static ObservableList<Houses> contactPeopleList;

    static TableView<Houses> contactTableView =
            new TableView<>();
    public House_Agent(long agent_id) {
        lblName.setTextFill(Color.web("#0076a3"));
        observableNames = FXCollections.observableArrayList
                (contact.getHousesForId(agent_id));
        filteredData = new FilteredList<>
                (observableNames, p -> true);
        sortedData = new SortedList<>(filteredData);
        listView = new ListView<>(sortedData);
        contactPeopleList= FXCollections.observableArrayList(contact.getHousesForId(agent_id));
    }
    public void display(long agent_id) throws Exception{

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
        //House id entry
        TextField id = new TextField();
        id.setPromptText("House ID");
        TextField type = new TextField();
        type.setPromptText("Sale Type");
        TextField sale_price = new TextField();
        sale_price.setPromptText("Deal Amount");
        TextField buyer_id = new TextField();
        buyer_id.setPromptText("Buyer ID");
        DatePicker d = new DatePicker();
        Button buyer_btn = new Button("Buyers");
        Button close_btn = new Button("Close");
        Button update_btn = new Button("Update");
        close_btn.setOnAction(e-> primaryStage.close());
        update_btn.setOnAction(e-> {
                    LocalDate t = d.getValue();

                    try {
                        update_btn_clicked(Long.parseLong(id.getText()), Long.parseLong(buyer_id.getText()), Long.parseLong(sale_price.getText()), type.getText(), t, agent_id);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    buyer_id.clear();
                    id.clear();
                    sale_price.clear();
                    type.clear();

        });
        buyer_btn.setOnAction(e->{
            Buyer_Display b = new Buyer_Display();
            b.display();
        });
        HBox close_hbBtn = new HBox();
        close_hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        close_hbBtn.setPadding(new Insets(10,10,10,10));
        close_hbBtn.setSpacing(10);
        close_hbBtn.getChildren().addAll(id, type, buyer_id, sale_price, d, update_btn, buyer_btn, close_btn);
        borderPane.setCenter(contactTableView);
        borderPane.setBottom(close_hbBtn);

        // TableView will populate from the contactPeopleList
        // contactPeopleList will have value according to the
        // item selected in the ListView
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void update_btn_clicked(long house_id, long buyer_id, long sale_price, String type, LocalDate t, long agent_id) throws Exception{
        contact.updateHouses(house_id, buyer_id, sale_price, type, t);
        contactPeopleList.clear();
        contactTableView.setItems((contactPeopleList));
        contactPeopleList = FXCollections.observableArrayList(contact.getHousesForId(agent_id));
        contactTableView.setItems(contactPeopleList);
        contactTableView.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY);
        AlertBox.display("Entry Updated", "Your database has been updated.", "Happy Homes!");
    }
}
