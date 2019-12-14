package pt.iade.cCollector.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pt.iade.cCollector.WindowManager;
import pt.iade.cCollector.models.User;


public class CreateCollectionViewController {
	User user;

	public CreateCollectionViewController(User user) {
		this.user = user;	
	}

	@FXML
    private Button addCollection;

    @FXML
    private Label welcomeText;
    
    public void initialize() {
    	
	}
    
    @FXML
    public void LogOut() {
    	WindowManager.backToMainWindow();
	}
    
    public void addCollection() {
    	WindowManager.openChooseCollectionView(user);
	}
    
    
}
