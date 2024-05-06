package controllers;

import java.awt.event.*;
import javax.swing.JOptionPane;
import views.*;
import models.*;

public class LoginController {

	LoginView loginView;

	public LoginController(LoginView loginView) {

		this.loginView = loginView;

		loginView.addRegisterButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

//				RegisterView rv = new RegisterView();
//				rv.setVisible(true);
//				
				loginView.setVisible(false);

				RegisterView rv = new RegisterView();
				RegisterContoller rc = new RegisterContoller(rv);

				// rv.setLocationRelativeTo(null);

				rv.setVisible(true);
			}
		});

		loginView.addLoginButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// JOptionPane.showMessageDialog(null, "Login successful");

				UserDataAccess userData = new UserDataAccess();

				if (userData.loginUser(loginView.getUsername(), loginView.getPassword())) {
					loginView.setVisible(false);

					ContactView contactView = new ContactView();
					new ContactController(contactView);

					contactView.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Username or password does not match");
				}

			}
		});
	}
}
