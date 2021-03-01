package ui;

import model.Flight;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
// Runs the actual program

public class Application extends JPanel {
    private static final String[] ITEMS = {"Calgary", "Montreal", "Toronto", "Vancouver"};
    private static final String[] seatType = {"Economic", "Business"};
    private static final int ROWS = 25;
    private static final int COLUMNS = 40;
    private JButton button = new JButton("Search");
    private JComboBox<String> myCombo = new JComboBox<>(ITEMS);
    private JComboBox<String> mySeatCombo = new JComboBox<>(seatType);
    private JTextArea textArea = new JTextArea(ROWS, COLUMNS);
    private Border blackLine;
    private Border raiseDetched;
    public static String select;
    public static ArrayList<Flight> flightList;
    public static ArrayList<String> tickets = new ArrayList<String>();
    private JButton exitButton = new JButton("Exit");
    private JFrame firstWindow = new JFrame("Air Ticket Management System");
    private JPanel mainPanel = new JPanel();
    private JPanel topPanel = new JPanel();
    private List<JRadioButton> list = new ArrayList<>();
    private JDatePickerImpl datePicker;
    private JPanel teamPanel = new JPanel(new GridLayout(20,1));
    private UtilDateModel model = new UtilDateModel();
    private ButtonGroup bg = new ButtonGroup();
    private JPanel okPanel = new JPanel(new GridBagLayout());
    private JButton okButton;
    private JButton loadButton = new JButton("Load");
    private static final String JSON_STORE = "./data/ticketSystem.json";

    //Effects: Displays the button and its name

    private void display() {
        initialization();
        okButton = new JButton(new AbstractAction("OK") {
            @Override
            public void actionPerformed(ActionEvent e) {
                okButtonAction(e);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitButtonAction(e);
            }
        });
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLoadOption();
            }
        });
        //okButton.setFont(okButton.getFont().deriveFont(10f));
        setOkPanel();

        setUpFirstWindow();

    }

    //Effects: initializes the panel

    public void initialization() {
        blackLine = BorderFactory.createLineBorder(Color.black);
        raiseDetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        firstWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        topPanel.setPreferredSize(new Dimension(680, 100));
        setTopPanel();
        mainPanel.add(topPanel,BorderLayout.PAGE_START);
        //mainPanel.add(topPanel);
        teamPanel.setPreferredSize(new Dimension(680, 450));
        mainPanel.add(new JScrollPane(teamPanel));

    }

    //Effects: Sets up the OK panel.

    public void setOkPanel() {
        okPanel.setPreferredSize(new Dimension(680, 80));
        topPanel.setBorder(blackLine);
        mainPanel.setBorder(raiseDetched);
        teamPanel.setBorder(blackLine);
        okPanel.setBorder(blackLine);
        okPanel.add(okButton);
        okPanel.add(exitButton);
        okPanel.add(loadButton);
        mainPanel.add(okPanel);
    }

    //Effects: Sets up the Top panel.

    public void setTopPanel() {
        topPanel.add(new JLabel("Destination: "));
        topPanel.add(myCombo);
        topPanel.add(new JLabel("Seat Type: "));
        topPanel.add(mySeatCombo);
        setDatePicker();

        topPanel.add(button);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonAction(evt);
            }
        });
    }

    //Effects: Sets up the Date Selector.

    public void setDatePicker() {
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        // Don't know about the formatter, but there it is...
        DateLabelFormatter date = new DateLabelFormatter();
        datePicker = new JDatePickerImpl(datePanel, date);
        topPanel.add(datePicker);
    }

    //Modifies: This
    //Effects: Puts out responses to button presses.

    public void buttonAction(ActionEvent evt) {
        playSound("button.wav");
        textArea.setText(" ");
        String s = datePicker.getJFormattedTextField().getText();
        String[] dateArray = s.split("-");
        Date d = Main.generateDateFormat(Integer.parseInt(dateArray[0]),Integer.parseInt(dateArray[1]),
                Integer.parseInt(dateArray[2]));
        Main.selectedFlights = Main.searchFlight(myCombo.getSelectedItem().toString(),
                mySeatCombo.getSelectedItem().toString(),d,flightList);
        if (!Main.selectedFlights.isEmpty()) {
            playSound("wining.wav");
        } else {
            JTextArea text = new JTextArea("No Flights Available");
            text.setFont(text.getFont().deriveFont(15f));
            text.setEditable(false);
            teamPanel.add(text);
        }
        for (String flight: Main.searchResult) {
            JRadioButton jrb = new JRadioButton(flight);
//            jrb.setBounds(0, 0, 120, 50);
            list.add(jrb);
            bg.add(jrb);
            teamPanel.add(jrb);
        }
        teamPanel.setBorder(BorderFactory.createTitledBorder("Please choose from bellow"));
    }

    //Modifies: This
    //Effects: Puts out responses to the "OK" button presses.

    public void okButtonAction(ActionEvent e) {
        select = " ";
        playSound("button.wav");
        for (JRadioButton jrb : list) {
            if (jrb.isSelected()) {
                JOptionPane.showMessageDialog(firstWindow, "You chose " + jrb.getText());
                select = jrb.getText();
            }
        }

        if (select.equals(" ")) {
            JOptionPane.showMessageDialog(firstWindow, "You didn't select any ticket");
        } else {
            playSound("button.wav");
            firstWindow.setVisible(false);
            SecondDisplay.runSecondDisplay();
        }
    }

    //Modifies: This
    //Effects: Sets up the starting panel

    public void setUpFirstWindow() {
        firstWindow.getContentPane().add(mainPanel);
        //f.add(mainPanel);
        firstWindow.pack();
        if (list.isEmpty()) {
            firstWindow.setSize(680, 700);
        } else {
            firstWindow.setSize(680, list.get(0).getHeight() * 16);
        }
        firstWindow.setLocationRelativeTo(null);
        firstWindow.setLocationByPlatform(true);
        firstWindow.setVisible(true);
    }

    //Modifies: This
    //Effects: Sets up the Load Button and pop up message

    public void setLoadOption() {
        JPanel loadOption = new JPanel();
        loadOption.add(new JLabel("Want to load previous data? "));
        int result = JOptionPane.showConfirmDialog(null, loadOption, "Alert!",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            playSound("button.wav");
            Main.load();
            JOptionPane.showMessageDialog(firstWindow, "Load to " + JSON_STORE);
        } else {

            JOptionPane.showMessageDialog(firstWindow, "Unable to Load");
        }
    }

    // Runs the entire GUI program

    public static void main(String[] args) {
        flightList = Main.flightsInit();
        EventQueue.invokeLater(new Application()::display);
    }

    //Modifies: This
    //Effects: Sets up the exit button and closes the application when exit is pressed.

    public static void exitButtonAction(ActionEvent e) {
        JPanel exitOption = new JPanel();
        exitOption.add(new JLabel("You really want to exit? "));
        int result = JOptionPane.showConfirmDialog(null, exitOption, "Alert!",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            System.exit(0);
        } else {
            System.out.println("Cancelled");
        }

    }

    //Modifies: This
    //Effects: Puts out responses to button click on the display.

    public static void runDisplay() {
        EventQueue.invokeLater(new Application()::display);
    }

    //Modifies: This
    //Effects: initializes the date format

    public static class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

        private String datePattern = "dd-MM-yyyy";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
        Calendar cal;

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return dateFormatter.format(Calendar.getInstance().getTime());
        }

    }

    //Modifies: This
    //Effects: initializes the sounds when buttons are pressed

    public static void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

}
