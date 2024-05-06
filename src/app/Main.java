package app;

import views.*;
import controllers.*;

public class Main {

	public static void main(String args[]) {

		LoginView loginView = new LoginView();
		LoginController loginController = new LoginController(loginView);
		loginView.setVisible(true);

	}
}
