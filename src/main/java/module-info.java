module de.lubowiecki.fxproducts {
    requires javafx.controls;
    requires javafx.fxml;


    opens de.lubowiecki.fxproducts to javafx.fxml;
    exports de.lubowiecki.fxproducts;
}