package pt.iade.cCollector.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pt.iade.cCollector.WindowManager;
import pt.iade.cCollector.models.User;
import pt.iade.models.dao.CCollectorDao;

/** This controls the log in screen**/
public class LoginController {

	@FXML
	private TextField userField;
	
	@FXML
	private PasswordField loginPass;

	
	String pass;
	String user;
	
	
	
	/** Compare entered pass with database pass **/	
	@FXML
	public void logInClick()  {
		pass = loginPass.getText();
		user = userField.getText();
		if(CCollectorDao.verifyPassword(user, pass)) {	
			WindowManager.openUserCollectionView(new User(user));
		}
		else {
			/** sends alert if log in fails **/
			Alert wrongPass = new Alert(AlertType.ERROR);
			wrongPass.setTitle("ERROR");
			wrongPass.setHeaderText("Log in failed");
			wrongPass.setContentText("Check user email, Password or internet connection");
			wrongPass.show();
		}
	}
	
	/** Registers a user using the data filled in to the login and password fields **/
	@FXML
	public void registerClick() {
		pass = loginPass.getText();
		user = userField.getText();
		System.out.print(loginPass.getText());
		/** Checks inputs, if empty sends an error box
		 * if the user exist (checked via the method CCollectorDao.userExists(user) sends an error box
		 * otherwise it registers a new user
		 */
		if(loginPass.getText().isEmpty()|| userField.getText().isEmpty()) {
			Alert userExistsAlert = new Alert(AlertType.ERROR);
			userExistsAlert.setTitle("ERROR");
			userExistsAlert.setHeaderText("Register failed");
			userExistsAlert.setContentText("Please enter an email and a password");
			userExistsAlert.show();			
		}else if(CCollectorDao.userExists(user)) {
			Alert userExistsAlert = new Alert(AlertType.ERROR);
			userExistsAlert.setTitle("ERROR");
			userExistsAlert.setHeaderText("Register failed");
			userExistsAlert.setContentText("Email already registered, log in instead");
			userExistsAlert.show();
		}else {
			CCollectorDao.registerNewUser(user, pass);
			WindowManager.openCreateCollectionView(new User(user));
		}

		}
	}

