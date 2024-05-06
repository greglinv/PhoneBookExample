package views;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

public class RegisterView extends JFrame {

	// controls or components needed
	private JTextField usernameField;
	private JPasswordField passwordField, confirmPassword;
	private JButton registerButton, loginButton;

	public RegisterView() {

		setTitle("Register");
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// initialize the components
		usernameField = new JTextField(10);
		passwordField = new JPasswordField(10);
		confirmPassword = new JPasswordField(10);
		registerButton = new JButton("Register");
		loginButton = new JButton("Go back to login");

		JPanel panel = new JPanel(new GridLayout(5, 2));
		panel.add(new JLabel("Username: "));
		panel.add(usernameField);

		panel.add(new JLabel("Password: "));
		panel.add(passwordField);

		panel.add(new JLabel("Confirm password: "));
		panel.add(confirmPassword);

		// Add colors to the buttons
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

		panel.add(loginButton);
		panel.add(registerButton);

		add(panel);

		// settrueVisible();
		setLocationRelativeTo(null);
	}

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
		return new String(passwordField.getText());
	}

	public String getConfirmPassword() {
		return new String(confirmPassword.getText());
	}
}
