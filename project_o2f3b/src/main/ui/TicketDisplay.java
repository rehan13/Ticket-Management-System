package ui;

import model.Booking;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
// Displays Tickets

public class TicketDisplay {
    private JFrame ticketWindow = new JFrame("Air Ticket Management System");
    private JPanel mainPanel = new JPanel();
    private JPanel body = new JPanel(new GridLayout(0,1));
    private Border raiseDetched;
    private JPanel topPanel = new JPanel();
    private static final int ROWS = 25;
    private static final int COLUMNS = 40;
    private JTextArea textArea = new JTextArea(ROWS, COLUMNS);
    private JPanel bottom = new JPanel(new GridBagLayout());
    private JButton buyAgain = new JButton("New Booking");
    private JButton exit = new JButton("Exit");
    private JButton save = new JButton("Save");
    private static final String JSON_STORE = "./data/ticketSystem.json";


    //Effects: initializes the ticket display page.
    private void ticketDisplay() {
        raiseDetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        ticketWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel.setBorder(raiseDetched);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        topPanel.setPreferredSize(new Dimension(680, 50));
        titleAdd();
        body.setPreferredSize(new Dimension(680, 450));
        bottom.setPreferredSize(new Dimension(680, 80));
        setTicketValues();
        setBottom();
        mainPanel.add(topPanel,BorderLayout.PAGE_START);
        mainPanel.add(body,BorderLayout.CENTER);
        mainPanel.add(bottom,BorderLayout.PAGE_END);
        //mainPanel.add( rowPanel, BorderLayout.CENTER);
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        setUserInputWindow();
    }

    //Modifies: This
    //Effects: Sets up the button on the ticket display page.

    public void setBottom() {
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.exitButtonAction(e);
            }
        });
        buyAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyAgainButton(e);
            }
        });
        saveOption();
        bottom.add(buyAgain);
        bottom.add(exit);
        bottom.add(save);
    }

    // Modifies: this
    // Effects: stores the ticket data that is generated.

    public void saveOption() {
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel loadOption = new JPanel();
                loadOption.add(new JLabel("Want to save data? "));
                int result = JOptionPane.showConfirmDialog(null, loadOption, "Alert!",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    Application.playSound("button.wav");
                    ArrayList<Booking> bookings = new ArrayList<Booking>();
                    bookings.add(Main.book);
                    TicketSystem ts = new TicketSystem("Ticket Management System", bookings);
                    ts.jsonWrite(ts);
                    JOptionPane.showMessageDialog(ticketWindow, "Save to " + JSON_STORE);
                } else {
                    JOptionPane.showMessageDialog(ticketWindow, "Unable to Save!!");

                }
            }
        });


    }


    //Effects: initializes the title of the ticket display page.

    public void titleAdd() {
        JLabel title = new JLabel("   Ticket   ");
        title.setFont(title.getFont().deriveFont(23f));
        title.setBorder(raiseDetched);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(title);
    }

    //Modifies: This
    //Effects: A button that allows users to book another ticket

    public void buyAgainButton(ActionEvent e) {
        Application.runDisplay();
        ticketWindow.setVisible(false);
        textArea.setText(" ");
    }

    //Modifies: This
    //Effects: Sets up the values of the tickets

    public void setTicketValues() {
        String ticket = "\n    Ticket No: " + Main.book.getBookingNo() + "    Price: " + Main.book.getPrice()
                + "    Flight No.:" + Main.book.getFlightNo() + "    Seat No.:" + Main.book.getSeatNo()
                + "    \n    Time: " + Main.book.getDate() + "    Destination: "
                + Main.book.getArrival() + "   Name: " + Main.book.getName() + "    Passport No.: "
                + Main.book.getPassportNo() + "    \n    Date of Birth: " + Main.book.getDateOfBirth()
                + "    Contact No: " + Main.book.getContactNo() + "    Emergency Contact No.: "
                + Main.book.getEmergencyContact() + "\n\n";
        Application.tickets.add(ticket);

        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        textArea.setFont(textArea.getFont().deriveFont(15f));
        for (String s: Application.tickets) {
            textArea.append(s);
        }
        body.add(textArea);

    }

    //Modifies: This
    //Effects: Sets up the user input window.

    public void setUserInputWindow() {
        ticketWindow.getContentPane().add(mainPanel);
        ticketWindow.pack();
        ticketWindow.setSize(680, 700);
        ticketWindow.setLocationRelativeTo(null);
        ticketWindow.setLocationByPlatform(true);
        ticketWindow.setVisible(true);
    }

    // Runs the Ticket display window.
    public static void runTicketDisplay() {
        EventQueue.invokeLater(new TicketDisplay()::ticketDisplay);
    }

}
