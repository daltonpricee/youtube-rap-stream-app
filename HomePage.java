import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

/**
 * Associated logic class for HomePage.form.
 * Main page of app and displays hip hop radio.
 */
public class HomePage {
    //Class variables
    private JPanel homePanel;
    private JLabel welcomeLabel;
    private JButton hiphopRaddioButton;
    private JButton otherArtistsButton;
    private JPanel radioPanel;
    private JPanel customizePanel;
    private JLabel hiphopLabel;
    private Statement myStmt = null;
    private Connection myConn = null;
    private ResultSet myRs = null;
    private String url;

    /**
     * Constructor for the HomePage class.
     * Creates HomePage panel. Display when called.
     * @throws SQLException
     */
    public HomePage() throws SQLException {
        JFrame frame = new JFrame("The Rap Corner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setVisible(true);
        frame.add(homePanel);

        /**
         * Action listener for when hip hop radio is selected.
         * Opens up the corresponding URL from the DB.
         * Opens up geneeric rap radio.
         * */
        hiphopRaddioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Establish connection to DB
                    Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/url_info",
                            "student", "student");

                    //Create query and execute for hip hop radio.
                    String query = "SELECT * from urls WHERE idurls = 6";
                    myStmt = myConn.createStatement();
                    myRs = myStmt.executeQuery(query);

                    //See if RS returns anything and get info based on it.
                    while (myRs.next()) {
                        url = myRs.getString(3);
                    }

                    //Open url link in browser
                    Desktop d = Desktop.getDesktop();
                    d.browse(new URI(url));

                } catch (SQLException | URISyntaxException | IOException ex) {
                    ex.printStackTrace();
                }
            };
        });

        /**
         * Action listener for the other artists button
         * Button opens up 2nd page of app with page of options.
         */
        otherArtistsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    new UserPage();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
