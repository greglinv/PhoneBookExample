package views;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginView extends JFrame {

	private JTextField usernameField;
	private JPasswordField passwordField;

	private JButton loginButton, registerButton;

	public LoginView() {
		setTitle("Login");
		setSize(300, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Initialize the components
		usernameField = new JTextField(10);
		passwordField = new JPasswordField(10);

		// create panel object with grid layout
		JPanel panel = new JPanel(new GridLayout(4, 2));

		// add the labels and corresponding fields
		panel.add(new JLabel("Username: "));
		panel.add(usernameField);
		panel.add(new JLabel("Password: "));
		panel.add(passwordField);

		// add the buttons to the panel
		registerButton = new JButton("Register");
		loginButton = new JButton("Login");

		// set the button colors
		Color c1 = new Color(43, 143, 178);
		loginButton.setOpaque(true);
		loginButton.setBorderPainted(false);
		loginButton.setBackground(c1);
		loginButton.setForeground(Color.white);

		Color c2 = new Color(43, 178, 96);
		registerButton.setOpaque(true);
		registerButton.setBorderPainted(false);
		registerButton.setBackground(c2);
		registerButton.setForeground(Color.white);

		panel.add(registerButton);
		panel.add(loginButton);

		add(panel); // add panel to the window

		setLocationRelativeTo(null);
		// setVisible(true); // make the window visible
	}

	// Add an action listener to the login button
	public void addLoginButtonListener(ActionListener listener) {
		loginButton.addActionListener(listener);
	}

	public void addRegisterButtonListener(ActionListener listener) {
		registerButton.addActionListener(listener);
	}

	public String getUsername() {
		return usernameField.getText();
	}

	public String getPassword() {
		return passwordField.getText();
	}
}
