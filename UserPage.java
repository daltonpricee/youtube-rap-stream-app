import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Associated logic class for UserPage.form.
 * Displays the available artists music radios.
 */
public class UserPage {
    //Class variables
    private JList radioList;
    private JPanel userPanel;
    private JButton trippieReddButton;
    private JButton lilUziVertButton1;
    private JButton juiceWrldButton;
    private JButton XXXButton;
    private JButton loFiHipHopButton;
    private JButton lilPeepButton;
    private JButton goBackButton;
    private JLabel botLeft;
    private JLabel botMid;
    private JLabel botRight;
    private JLabel topLeft;
    private JLabel topMid;
    private JLabel topRight;
    private JLabel selectLabel;
    private JPanel backPanel;
    private JPanel otherPanel;
    private JPanel otherPanel2;
    private JPanel otherPanel3;
    private JPanel otherPanel4;
    private JButton backToHomePageButton;
    private JTable artist_table;
    private JButton saveButton;
    private String value;
    private PreparedStatement myStmt;
    private Statement st;
    private Connection myConn;
    private ResultSet myRs;
    private String url;
    private String query;
    private JFrame frame2;
    private Statement myStmt2;
    private ResultSet myRs2 = null;
    private String url2;
    private int urlIndex;

    /**
     * Constructor for the UserPage class.
     * Creates UserPage panel. Display when called.
     */
    public UserPage() throws SQLException {
        frame2 = new JFrame("Find your favorite artists!");
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(800, 700);
        userPanel.setBackground(Color.black);
        topLeft.setBackground(Color.black);
        topMid.setBackground(Color.black);
        topRight.setBackground(Color.black);
        otherPanel.setBackground(Color.black);
        botMid.setBackground(Color.black);
        botLeft.setBackground(Color.black);
        botRight.setBackground(Color.black);
        otherPanel2.setBackground(Color.black);
        otherPanel3.setBackground(Color.black);
        otherPanel4.setBackground(Color.black);
        backPanel.setBackground(Color.black);
        frame2.setVisible(true);
        frame2.add(userPanel);

        /**
         * ActionListener for GoBack button.
         * Returns back to HomePage, deletes UserPage instance.
         */
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.dispose();
                try {
                    new HomePage();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        /**
         * ActionListener for trippieReddButton button.
         * Opens corresponding URL for correct artist.
         */
        trippieReddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCorrespondingButton(1);
            }
        });

        /**
         * ActionListener for lilUziVertButton button.
         * Opens corresponding URL for correct artist.
         */
        lilUziVertButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCorrespondingButton(2);
            }
        });

        /**
         * ActionListener for juiceWrldButton button.
         * Opens corresponding URL for correct artist.
         */
        juiceWrldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCorrespondingButton(3);
            }
        });
        /**
         * ActionListener for lilPeepButton button.
         * Opens corresponding URL for correct artist.
         */
        lilPeepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCorrespondingButton(4);
            }
        });

        /**
         * ActionListener for loFiHipHopButton button.
         * Opens corresponding URL for correct artist.
         */
        loFiHipHopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCorrespondingButton(5);
            }
        });

        /**
         * ActionListener for XXXButton button.
         * Opens corresponding URL for correct artist.
         */
        XXXButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCorrespondingButton(7);
            }
        });
    }

    /**
     * Opens up for the correct artist Stream.
     * @param urlIndex the index of the URL in the DB.
     */
    public void openCorrespondingButton(int urlIndex) {
      int urlToUse = urlIndex;

        try {
            //Establish connection to DB
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/url_info",
                    "student", "student");

            //Create query and execute for hip correct URL from DB
            String query = "SELECT * from urls WHERE idurls = " + urlIndex;
            myStmt2 = myConn.createStatement();
            myRs2 = myStmt2.executeQuery(query);

            //See if RS returns anything and get info based on it.
            while (myRs2.next()) {
                url = myRs2.getString(3);
            }

            //Open url link in browser
            Desktop d = Desktop.getDesktop();
            d.browse(new URI(url));

        } catch (SQLException | URISyntaxException | IOException ex) {
            ex.printStackTrace();
        }
    };
}
