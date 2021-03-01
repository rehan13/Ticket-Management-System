package ui;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
//Displays the second screen

public class SecondDisplay extends JPanel {

    private static final String[] genderStrings = {"Female", "Male", "Other"};
    private static final int ROWS = 25;
    private static final int COLUMNS = 40;
    private JButton doneButton = new JButton("Done");
    private JLabel space = new JLabel("      ");
    private JTextField name = new JTextField(COLUMNS - 15);
    private JTextField contact = new JTextField(COLUMNS - 15);
    private JTextField emergencyContact = new JTextField(COLUMNS - 15);
    private JTextField passportNo = new JTextField(COLUMNS - 15);
    private JComboBox<String> genderSelection = new JComboBox<>(genderStrings);
    private JTextField address = new JTextField(COLUMNS - 15);
    private JTextField email = new JTextField(COLUMNS - 15);
    private JTextArea textArea = new JTextArea(ROWS, COLUMNS);
    private JScrollPane scrollPane = new JScrollPane();
    private JFrame userInputWindow;
    private JPanel mainPanel;
    private JDatePickerImpl datePicker;
    private Border raiseDetched;
    private JPanel rowPanel = new JPanel(new GridLayout(25,1));

    //Effects: Sets up the user input screen
    private void userInputDisplay() {
        userInputWindow = new JFrame("Air Ticket Management System");
        raiseDetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        userInputWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("User Information");
        title.setFont(title.getFont().deriveFont(23f));
        rowPanel.add(title,BorderLayout.CENTER);
        setTextFields();
        rowPanel.add(space);
        rowPanel.add(doneButton,BorderLayout.CENTER);
        doneButton.setFont(doneButton.getFont().deriveFont(15f));
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doneButtonAction(e);
            }
        });
        mainPanel = new JPanel();
        mainPanel.setBorder(raiseDetched);
        // mainPanel.add(title);
        mainPanel.add(rowPanel, BorderLayout.CENTER);
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        setUserInputWindow();
    }

    //Effects:Runs the second input screen where the user inputs their personal information.

    public static void runSecondDisplay() {
        EventQueue.invokeLater(new SecondDisplay()::userInputDisplay);
    }

    //Modifies: This
    //Effects: sets up the input form for the user to fill up
    public void setUserInputWindow() {
        userInputWindow.getContentPane().add(mainPanel);
        userInputWindow.pack();
        userInputWindow.setSize(680, 700);
        userInputWindow.setLocationRelativeTo(null);
        userInputWindow.setLocationByPlatform(true);
        userInputWindow.setVisible(true);
    }

    //Modifies: This
    //Effects: Puts out responses to the "Done" button presses.

    public void doneButtonAction(ActionEvent e) {
        Application.playSound("button.wav");
        Main mainUi = new Main();
        String s = datePicker.getJFormattedTextField().getText();
        Main.setUserInfo(name.getText(),passportNo.getText(),s,address.getText(),
                contact.getText(),emergencyContact.getText(),genderSelection.getSelectedItem().toString(),
                email.getText());
        setUserInfo();

        userInputWindow.setVisible(false);
        TicketDisplay.runTicketDisplay();
    }

    //Modifies: This
    //Effects: Sets up the user information.

    public static void setUserInfo() {
        Main.userInfo = Main.getUserInfo();
        String[] selectArray = Application.select.split(" ");
        System.out.println(selectArray[29] + " " + selectArray[2]);
        Main.matchedFlight = Main.seatBooking(Main.selectedFlights,selectArray[29],selectArray[2]);
        Main.booking(Main.matchedFlight);
    }

    //Modifies: This
    //Effects:Sets up the text fields on the text boxes.

    public void setTextFields() {
        setName();
        setContact();
        setEmergencyContact();
        setDateOfBirth();
        setGenderSelection();
        setAddress();
        setEmail();
        setPassportNo();

    }

    //Modifies: This
    //Effects: Takes in the user's name.

    public void setName() {
        JLabel nameText = new JLabel("Name: ");
        nameText.setFont(nameText.getFont().deriveFont(15f));
        rowPanel.add(nameText);
        rowPanel.add(name);
    }

    //Modifies: This
    //Effects: Takes in the user's contact number

    public void setContact() {
        JLabel contactText = new JLabel("contact No.: ");
        contactText.setFont(contactText.getFont().deriveFont(15f));
        rowPanel.add(contactText);
        rowPanel.add(contact);
    }

    //Modifies: This
    //Effects: Takes in the user's emergency contact number

    public void setEmergencyContact() {
        JLabel emergencyText = new JLabel("Emergency Contact No.: ");
        rowPanel.add(emergencyText);
        emergencyText.setFont(emergencyText.getFont().deriveFont(15f));
        rowPanel.add(emergencyContact);
    }

    //Modifies: This
    //Effects: Takes in the user's date of birth

    public void setDateOfBirth() {
        JLabel dateOfBirth = new JLabel("Date of Birth: ");
        rowPanel.add(dateOfBirth);
        dateOfBirth.setFont(dateOfBirth.getFont().deriveFont(15f));
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        Application.DateLabelFormatter date = new Application.DateLabelFormatter();
        datePicker = new JDatePickerImpl(datePanel, date);
        rowPanel.add(datePicker);
    }

    //Modifies: This
    //Effects: Takes in the user's passport number

    public void setPassportNo() {
        JLabel passportText =  new JLabel("Passport No: ");
        rowPanel.add(passportText);
        passportText.setFont(passportText.getFont().deriveFont(15f));
        rowPanel.add(passportNo);
    }

    //Modifies: This
    //Effects: Takes in the user's gender input from a dropdown box

    public void setGenderSelection() {
        JLabel genderText = new JLabel("Gender: ");
        rowPanel.add(genderText);
        genderText.setFont(genderText.getFont().deriveFont(15f));
        rowPanel.add(genderSelection);
    }

    //Modifies: This
    //Effects: Takes in the user's address

    public void setAddress() {
        JLabel addressText = new JLabel("Address: ");
        rowPanel.add(addressText);
        addressText.setFont(addressText.getFont().deriveFont(15f));
        rowPanel.add(address);
    }

    //Modifies: This
    //Effects: Takes in the user's email

    public void setEmail() {
        JLabel emailText = new JLabel("Email: ");
        rowPanel.add(emailText);
        emailText.setFont(emailText.getFont().deriveFont(15f));
        rowPanel.add(email);
    }
}
