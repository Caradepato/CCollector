package pt.iade.cCollector.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pt.iade.cCollector.WindowManager;
import pt.iade.cCollector.models.User;

/** This controls the Create Collection View 
 * This is the first screen a user will see upon registering
 * It welcomes the user and lets them select their first collection**/

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
    
    /** logs the user out
     * 
     */
    @FXML
    public void LogOut() {
    	WindowManager.backToMainWindow();
	}
    /**
     * Takes the user to the Choose Collection View
     */
    
    public void addCollection() {
    	WindowManager.openChooseCollectionView(user);
	}
    
    
}
