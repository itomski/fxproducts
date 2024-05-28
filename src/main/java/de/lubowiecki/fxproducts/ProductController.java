package de.lubowiecki.fxproducts;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    public void save() {
        Product p = new Product();
        p.setName(name.getText());
        p.setDescription(beschreibung.getText());
        p.setCount(anzahl.getValue());
        p.setAvailableSince(imBestandSeit.getValue());
        p.setPrice(Double.parseDouble(preis.getText()));
        produkte.add(p);
        clearFields(); // Formular wird geleert

        System.out.println();
        System.out.println(produkte);
    }

    private void clearFields() {
        name.clear();
        beschreibung.clear();
        anzahl.getEditor().clear();
        imBestandSeit.setValue(LocalDate.now());
        preis.clear();
    }
}