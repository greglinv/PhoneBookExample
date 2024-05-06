package controllers;

import views.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import java.io.*;
import models.*;
import myutils.CSVExporter;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.event.*;

public class ContactController {

	private ContactView contactView;

	public boolean isValidEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pattern = Pattern.compile(emailRegex);
		return pattern.matcher(email).matches();
	}

	public ContactController(ContactView cv) {

		this.contactView = cv;

		updateContactList();

		contactView.addAddButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Retrieve and trim contact information
				String firstName = contactView.getFirstName().trim();
				String lastName = contactView.getLastName().trim();
				String phoneNumber = contactView.getPhoneNumber().trim();
				String email = contactView.getEmail().trim(); // Assuming there is a method to get email

				// Validate input fields are not empty for required fields
				if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"All fields (First Name, Last Name, Phone Number) must be filled.", "Input Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Validate email format if provided
				if (!email.isEmpty() && !isValidEmail(email)) {
					JOptionPane.showMessageDialog(null, "Invalid email format.", "Input Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Create an object of a contact
				Contact contact = new Contact();
				contact.setFirstname(firstName);
				contact.setLastname(lastName);
				contact.setPhoneNumber(phoneNumber);
				contact.setEmail(email); // Set email, which may be empty

				ContactDataAccess contactData = new ContactDataAccess();
				if (contactData.addContact(contact)) {
					JOptionPane.showMessageDialog(null, "Contact added successfully");
					updateContactList();
				} else {
					JOptionPane.showMessageDialog(null, "An error occurred", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		contactView.addContactListListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				Contact contact = getSelectedContact();

				if (contact != null) {
					updateFields(contact);
				}
			}
		});

		// Update button click
		contactView.addUpdateButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Contact contact = getSelectedContact();

				String fn = contactView.getFirstName();
				String ln = contactView.getLastName();
				String pn = contactView.getPhoneNumber();
				String em = contactView.getEmail();

				contact.setFirstname(fn);
				contact.setLastname(ln);
				contact.setPhoneNumber(pn);
				contact.setEmail(em);

				if (new ContactDataAccess().updateContact(contact)) {
					JOptionPane.showMessageDialog(null, "Contact updated successfully");
					updateContactList();
				}

			}
		});

		contactView.addLogoutButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				UserDataAccess.currentUserId = 0;

				contactView.setVisible(false);

				LoginView lv = new LoginView();
				lv.setVisible(true);
				new LoginController(lv);

			}
		});

		contactView.addExportButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Contact> contacts = new ContactDataAccess().getContacts(); // Get the list of contacts
				String filePath = "contacts.csv"; // Define the path to save the CSV
				CSVExporter.exportContacts(contacts, filePath);

			}
		});

		contactView.addDeleteButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Contact selectedContact = getSelectedContact();
				if (selectedContact != null) {
					boolean deleted = new ContactDataAccess().deleteContact(selectedContact.getId());
					if (deleted) {
						JOptionPane.showMessageDialog(contactView, "Contact deleted successfully");
						updateContactList(); // Refresh the contact list
					} else {
						JOptionPane.showMessageDialog(contactView, "Failed to delete contact");
					}
				} else {
					JOptionPane.showMessageDialog(contactView, "Please select a contact to delete");
				}
			}
		});

	}

	private void updateContactList() {
		ContactDataAccess data = new ContactDataAccess();

		List<Contact> contacts = data.getContacts();

		contactView.setContactsToModel(contacts);
	}

	private Contact getSelectedContact() {
		Contact contact = null;

		int row = contactView.getContactList().getSelectedIndex();

		if (row != -1) {
			contact = new ContactDataAccess().getContacts().get(row);
		}
		return contact;
	}

	private void updateFields(Contact contact) {

		contactView.getFirstNameField().setText(contact.getFirstname());
		contactView.getLastNameField().setText(contact.getLastname());
		contactView.getPhoneNumberField().setText(contact.getPhoneNumber());
	}

}
