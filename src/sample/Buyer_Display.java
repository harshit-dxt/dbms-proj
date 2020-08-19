package sample;
import actors.*;
import actors.Agent_DAO;
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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Buyer_Display {
    private static String[] propertyName= {"Id", "Name"};
    private static String[] propertyLabel = {"Buyer ID", "Buyer Name"};
    private static Buyer_DAO buyer = new Buyer_DAO();
    private static final GridPane gridPane = new GridPane();
    private static final Label lblName = new Label("Search by Name");
    private static final TextField searchField = new TextField();
    private static ObservableList<Buyer> observableNames;
    private static FilteredList<Buyer> filteredData;
    private static SortedList<Buyer> sortedData;
    private static ListView<Buyer> listView;
    static TableView<Buyer> contactTableView =
            new TableView<>();
    static ObservableList<Buyer> contactPeopleList;
    public Buyer_Display() {
        lblName.setTextFill(Color.web("#0076a3"));
        observableNames = FXCollections.observableArrayList
                (buyer.getContacts());
        filteredData = new FilteredList<>
                (observableNames, p -> true);
        sortedData = new SortedList<>(filteredData);
        listView = new ListView<>(sortedData);
    }

    public void display() {

        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.setTitle("Mi Casa del Rio");
        primaryStage.setMaximized(true);
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 650, 400, true);
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(lblName, 0, 0);
        gridPane.add(searchField, 0, 1);
        // Search TextField event handling
       /* searchField.textProperty()
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
        listView.setCellFactory(listView -> {
            Tooltip tooltip = new Tooltip();
            ListCell<Buyer> cell = new
                    ListCell<Buyer>() {
                        protected void updateItem(Buyer contactPerson,
                                                  Boolean empty) {
                            super.updateItem(contactPerson, empty);
                            if (contactPerson != null) {
                                setText(contactPerson.getName());
                                tooltip.setText(contactPerson.getName());
                                setTooltip(tooltip);
                            } else
                                setText(null);
                        }
                    };
            return cell;
        });
        gridPane.add(listView, 0, 2);
         contactPeopleList
                = FXCollections.observableArrayList(buyer.getContacts());
        contactTableView.setItems(contactPeopleList);
        contactTableView.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY);
        for (int i = 0; i <
                propertyLabel.length; i++) {

            TableColumn<Buyer, Object> col
                    = new TableColumn<>(propertyLabel[i]);
            col.setCellValueFactory(new
                    PropertyValueFactory<>(propertyName[i]));
            contactTableView.getColumns().add(col);

        }
        Button add_btn = new Button("Add");
        TextField id = new TextField();
        id.setPromptText("Buyer ID");
        TextField name = new TextField();
        name.setPromptText("Name");
        add_btn.setOnAction(e->{
            try {
                addButtonClicked(Long.parseLong(id.getText()), name.getText());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        Button close_btn = new Button("Close");
        close_btn.setOnAction(e -> primaryStage.close());
        HBox close_hbBtn = new HBox();
        close_hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        close_hbBtn.setPadding(new Insets(10,10,10,10));
        close_hbBtn.setSpacing(10);
        close_hbBtn.getChildren().addAll(id, name, add_btn, close_btn);
        borderPane.setCenter(contactTableView);
        borderPane.setBottom(close_hbBtn);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void addButtonClicked(long agent_id, String name) throws Exception{
        buyer.addBuyers(agent_id, name);
        contactPeopleList.clear();
        contactTableView.setItems((contactPeopleList));
        contactPeopleList = FXCollections.observableArrayList(buyer.getContacts());
        contactTableView.setItems(contactPeopleList);
        contactTableView.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY);
        AlertBox.display("Entry Updated", "Your database has been updated.", "More People, More Fun!");
    }
}