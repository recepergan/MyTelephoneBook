package application;

import java.io.IOException;

import application.model.Person;
import application.view.PersonOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	private Stage primary;
	private BorderPane rootPane;
	private ObservableList<Person> people;
	
	public ObservableList<Person> getPeople() {
		if (this.people == null) {
			this.people = FXCollections.observableArrayList();
		}
		return this.people;
	}
	
	@Override
	public void start(Stage primaryStage) {
		initPrimaryStage(primaryStage);
		initRootLayout();
		initTelephoneEntries();
		showPersonOverView();
		
	}
	
	private void initTelephoneEntries() {
		this.getPeople().add(new Person("Babür", "Somer", "533 437 9929", null, null, 0, null));
		this.getPeople().add(new Person.Builder().firstName("Ali").lastName("Ergül").build());
		this.getPeople().add(new Person.Builder().firstName("Çaðlayan").lastName("Kaya").build());
		this.getPeople().add(new Person.Builder().firstName("Çaðrý").lastName("Türkmen").build());
		this.getPeople().add(new Person.Builder().firstName("Canan").lastName("Havva").build());
		this.getPeople().add(new Person.Builder().firstName("Görkem").lastName("Sönmez").build());
		this.getPeople().add(new Person.Builder().firstName("Gülten").lastName("Ulukal").build());
		this.getPeople().add(new Person.Builder().firstName("Mert Can").lastName("Aydýn").build());
		this.getPeople().add(new Person.Builder().firstName("Melih").lastName("Dumanlý").build());
		this.getPeople().add(new Person.Builder().firstName("Mustafa").lastName("Öztürk").build());
		this.getPeople().add(new Person.Builder().firstName("Onur").lastName("Gündoðdu").build());
		this.getPeople().add(new Person.Builder().firstName("Recep").lastName("Ergan").build());
		this.getPeople().add(new Person.Builder().firstName("Sercan").lastName("Üstün").build());
		this.getPeople().add(new Person.Builder().firstName("Burak").lastName("Güneþ").city("Samatya").build());
		
		for (Person person : people) {
			System.out.println(person);
			
		}
		
	}
	
	private void showPersonOverView() {
		FXMLLoader loader = new FXMLLoader(); // herhangi bir scene ekranda göstermek için FXML yüklüyoruz
		loader.setLocation(getClass().getResource("view/PersonOverview.fxml")); // fxml dosyası nerede belirtiyoruz
		try {
			AnchorPane personOverview = (AnchorPane) loader.load();// fxml i okuyoruz
			this.rootPane.setCenter(personOverview); // rootpane borderpane cinsinden old.sağ/sol /orta/yukarı/asağı
														// bulunmakta
			// ortaya personoverview ekliyor
			PersonOverviewController controller = loader.getController(); // loader içinde ön yüz bilgileri var
			// bunu yaptığımız zaman hangi controller kullanılacağını belirtmiş oluyoruz//
			// FXML dosyası içinde tanımlı controlerl
			// dinamik
			// bir şekilde sınıfımıza dahil ediyoruz
			controller.setMain(this);// controller a main in içerdiği bilgiler iletiliyor
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initPrimaryStage(Stage primaryStage) {
		this.primary = primaryStage;
		this.primary.getIcons().add(new Image(getClass().getResourceAsStream("resources/TelephoneBook.jpg")));
		this.primary.setTitle("Benim Güzel Telefon Rehberim");
	}
	
	private void initRootLayout() {
		try {
			this.rootPane = (BorderPane) FXMLLoader.load(getClass().getResource("view/TelephoneBook.fxml"));
			Scene scene = new Scene(this.rootPane, 600, 400);
			scene.getStylesheets().add(getClass().getResource("view/application.css").toExternalForm());
			this.primary.setScene(scene); // o platformun içine o sahneyi yüklüyor.
			// primaryStage.initStyle(StageStyle.UNDECORATED); (bu komut temiz bir sayfa
			// getiriyor
			// stage : platform scene : sahne stage 1 tane scene 1 den fazla olabilir
			
			this.primary.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public Stage getPrimary() {
		// TODO Auto-generated method stub
		return this.primary;
	}
}
