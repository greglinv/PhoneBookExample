package controllers;

import java.awt.event.*;
import javax.swing.JOptionPane;
import views.*;
import models.*;

public class RegisterContoller {

	private RegisterView registerView;

	public RegisterContoller(RegisterView rv) {
		this.registerView = rv;

		registerView.addRegisterButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = registerView.getUsername().trim();
				String password = registerView.getPassword().trim();

				// Check for empty fields
				if (username.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Username and Password cannot be empty.", "Registration Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				User user = new User();
				user.setUsername(username);
				user.setPassword(password);

				// Attempt to register the user
				if (new UserDataAccess().registerUser(user)) {
					JOptionPane.showMessageDialog(null, "Registered successfully", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Registration failed", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		registerView.addLoginButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				LoginView lv = new LoginView();
				LoginController lc = new LoginController(lv);

				// lv.setLocationRelativeTo(null);
				lv.setVisible(true);

				registerView.setVisible(false);
			}
		});
	}
}
