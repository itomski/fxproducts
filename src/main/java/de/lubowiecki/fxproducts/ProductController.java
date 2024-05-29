package de.lubowiecki.fxproducts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final String HOME_PATH = System.getProperty("user.home") + "/";
    private static final String SER_FILE = HOME_PATH + "products.ser";

    private List<Product> produkte;

    @FXML // Macht diese Eigenschaft für die GUI sichtbar
    private TextField name; // name ist die id des Elementes in der View

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
    private Button editBtn;

    private int selectedIndex = -1;

    @FXML
    public void save() {

        // Neues leeres Produkt wird instanziert
        Product p = new Product();

        // Das Produkt wird mit den Daten aus dem Formular gefüllt
        p.setName(name.getText());
        p.setDescription(beschreibung.getText());
        p.setCount(anzahl.getValue());
        p.setAvailableSince(imBestandSeit.getValue());
        p.setPrice(Double.parseDouble(preis.getText()));

        if(selectedIndex >= 0) {
            produkte.set(selectedIndex, p); // Ersetzt das alte Produkt durch eine neue Version
            clearEdit();
        }
        else {
            produkte.add(p); // Das gefüllte Produkt wird der Liste der Produkte zugewiesen
        }
        saveToFile(); // Änderung in Datei speichern

        // Formular wird geleert
        clearFields();

        // Anzeige der Produkte wird erneuert
        printProduct();
    }

    @FXML
    public void edit() {

        if(selectedIndex >= 0) {
            clearEdit();
        }
        else {
            int index = ausgabe.getSelectionModel().getSelectedIndex();
            if (index >= 0) {
                selectedIndex = index;
                editBtn.setText("Bearbeitung aufheben");
                Product selected = produkte.get(index);
                name.setText(selected.getName());
                beschreibung.setText(selected.getDescription());
                anzahl.getValueFactory().setValue(selected.getCount());
                imBestandSeit.setValue(selected.getAvailableSince());
                preis.setText(selected.getPrice() + ""); // price ist ein double - String wird gebraucht
            }
        }
    }

    public void clearEdit() {
        selectedIndex = -1;
        ausgabe.getSelectionModel().clearSelection();
        editBtn.setText("Bearbeiten");
        clearFields();
    }

    @FXML
    public void delete() {
        // Ermitteln, welches Produkt in der Liste ausgewählt wurde
        int index = ausgabe.getSelectionModel().getSelectedIndex();

        // Ausgewähltes Produkt wird aus der Liste entfernt
        produkte.remove(index);
        saveToFile(); // Änderung in Datei speichern

        // Anzeige der Produkte wird erneuert
        printProduct();
    }

    private void printProduct() {

        ausgabe.getItems().clear(); // Alle Elemente Entfernen

        for(Product p : produkte) {

            StringBuilder row = new StringBuilder();
            // Das String für jedes Produkt wird zusammengestellt
            row.append(p.getName())
                    .append("\n")
                    .append(p.getDescription())
                    .append("\n")
                    .append(p.getCount())
                    .append("\n")
                    .append(String.format("%.2f €", p.getPrice()))
                    .append("\n")
                    .append(p.getAvailableSince().format(DATE_FMT))
                    .append("\n");

            // Produkte werden als Strings der sichtbaren Liste hinzugefügt
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

    private void saveToFile() {
        try(FileOutputStream fos = new FileOutputStream(SER_FILE);
            ObjectOutputStream out = new ObjectOutputStream(fos)) {
            // Produktliste wird serialisiert und in die Datei geschrieben
            // Serialisieren: Objekte in Text übersetzten
            out.writeObject(produkte);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadFromFile() {
        try(FileInputStream fis = new FileInputStream(SER_FILE);
            ObjectInputStream in = new ObjectInputStream(fis)) {
            // Datenwerden aus der Datei gelesen und deserialisiert
            // Deserialisieren: Text in Objekte übersetzen
            produkte = (List<Product>)in.readObject();
        }
        catch(Exception ex) {
            // Wenn Daten nicht gelesen werden konnten wird eine leere Produktliste bereitgestellt
            produkte = new ArrayList<>();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Arbeiten, die beim Start der Anwendung ausgeführt werden müssen
        loadFromFile(); // Daten aus der Datei laden
        printProduct(); // Ausgabe der Produkte
    }
}