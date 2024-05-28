package de.lubowiecki.fxproducts;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductController {

    private List<Product> produkte = new ArrayList();

    @FXML // Macht diese Eigenschaft für die GUI sichtbar
    private TextField name;

    @FXML
    private TextArea beschreibung;

    @FXML
    private Spinner<Integer> anzahl;

    @FXML
    private DatePicker imBestandSeit;

    @FXML
    private TextField preis;

    @FXML
    private ListView<String> ausgabe;

    @FXML
    public void save() {
        Product p = new Product();
        p.setName(name.getText());
        p.setDescription(beschreibung.getText());
        p.setCount(anzahl.getValue());
        p.setAvailableSince(imBestandSeit.getValue());
        p.setPrice(Double.parseDouble(preis.getText()));
        produkte.add(p);
        clearFields(); // Formular wird geleert
        printProduct();
    }

    @FXML
    public void delete() {
        int index = ausgabe.getSelectionModel().getSelectedIndex();
        produkte.remove(index);
        printProduct();
    }

    private void printProduct() {

        ausgabe.getItems().clear(); // Alle Elemente Entfernen

        for(Product p : produkte) {

            StringBuilder row = new StringBuilder();
            row.append(p.getName())
                    .append("\n")
                    .append(p.getDescription())
                    .append("\n")
                    .append(p.getCount())
                    .append("\n")
                    .append(p.getPrice())
                    .append("\n")
                    .append(p.getAvailableSince())
                    .append("\n");

            ausgabe.getItems().add(row.toString());
        }
    }


    private void clearFields() {
        name.clear();
        beschreibung.clear();
        anzahl.getValueFactory().setValue(0);
        imBestandSeit.setValue(LocalDate.now());
        preis.clear();
    }
}