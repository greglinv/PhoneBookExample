package myutils;

import models.Contact;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;

public class CSVExporter {

	public static void exportContacts(List<Contact> contacts, String filePath) {
		try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
			pw.println("First Name,Last Name,Phone Number,Email");
			for (Contact contact : contacts) {
				pw.println(String.format("%s,%s,%s,%s", contact.getFirstname(), contact.getLastname(),
						contact.getPhoneNumber(), contact.getEmail()));
			}
			System.out.println("Contacts exported successfully to " + filePath);
		} catch (IOException e) {
			System.err.println("Error while exporting contacts: " + e.getMessage());
		}
	}
}
