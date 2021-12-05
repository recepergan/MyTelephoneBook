package application.view;
// ekranımızla bilgilerimizi birbiriyle ilişkilendirecek olan class

import java.util.Optional;

import application.Main;
import application.model.Person;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {
	
	private Main main; // all data in main application
	
	@FXML // hangileri fxml bağlantılı teker teker göstermek zorunlu
	private TableView<Person> tblPerson;
	@FXML
	private TableColumn<Person, String> colFirstName;// neyle ilgili ve hangi kolon
	@FXML
	private TableColumn<Person, String> colLastName;
	
	@FXML
	private Label lblFirstName;
	@FXML
	private Label lblLastName;
	@FXML
	private Label lblTelephone;
	@FXML
	private Label lblCity;
	@FXML
	private Label lblStreet;
	@FXML
	private Label lblZIP;
	@FXML
	private Label lblBirthday;
	
	public PersonOverviewController() {
		super();
	}
	
	@FXML
	private void initialize() {
		colFirstName.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());// kişi listesini
		// yükle ve hazırla
		colLastName.setCellValueFactory(cellData -> cellData.getValue().getLastNameProperty());
		
		showPersonDetails(null);// ayrıntlı bilgileri sıfırla
		// seçili satırın özelliklerine bir changeListener ekliyor.
		tblPerson.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
	}
	
	/*
	 * Alternatif olarak önce br change listener yaratılıp addListener metoduna
	 * eklenebilir lambda expression kullanmak şstemeyenlere şiddetle önerilir
	 */
	ChangeListener<Person> kjkj = new ChangeListener<Person>() {
		@Override
		public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
			showPersonDetails(newValue);
		}
	};
	
	public void setMain(Main main) {
		this.main = main;
		tblPerson.setItems(main.getPeople());
		
	}
	
	private void showPersonDetails(Person person) {
		if (person == null) {
			lblFirstName.setText("");
			lblLastName.setText("");
			lblTelephone.setText("");
			lblCity.setText("");
			lblStreet.setText("");
			lblZIP.setText("");
			lblBirthday.setText("");
			
		} else {
			lblFirstName.setText(person.getFirstName());
			lblLastName.setText(person.getLastName());
			lblTelephone.setText(person.getTelephone());
			lblCity.setText(person.getStreet());
			lblStreet.setText(person.getCity());
			lblZIP.setText("" + person.getZip());
			lblBirthday.setText(person.getFormatDate(person.getBirthday()));
		}
	}
	
	@FXML
	private void delete() {
		int selected = tblPerson.getSelectionModel().getSelectedIndex();// hangi satırda olduğumuzu biliyoruz
		if (selected >= 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(this.main.getPrimary());
			
			Optional<ButtonType> type = alert.showAndWait();
			if (type.get().getText().equalsIgnoreCase("OK"))
				tblPerson.getItems().remove(selected); // aktif olan satırı listeden siliyor
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(this.main.getPrimary());
			alert.setTitle("Dikkat");
			alert.setHeaderText("kişi seçili değil");
			alert.setContentText("lütfen bir kişi seçiniz");
			alert.show();
		}
	}
	
}
