package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SignUp extends JFrame implements ActionListener {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton registerButton, backButton;

    SignUp() {
        setTitle("Sign Up");

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(40, 30, 100, 30);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 30, 150, 30);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(40, 80, 100, 30);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 80, 150, 30);
        add(passwordField);

        registerButton = new JButton("Register");
        registerButton.setBounds(40, 140, 120, 30);
        registerButton.setBackground(Color.GREEN);
        registerButton.setForeground(Color.BLACK);
        registerButton.addActionListener(this);
        add(registerButton);

        backButton = new JButton("Back");
        backButton.setBounds(180, 140, 120, 30);
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        add(backButton);

        setLayout(null);
        setSize(350, 250);
        setLocation(500, 300);
        getContentPane().setBackground(new Color(180, 205, 205));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            try {
                conn c = new conn();
                String user = usernameField.getText();
                String pass = new String(passwordField.getPassword());

                if (user.isEmpty() || pass.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Fields cannot be empty");
                    return;
                }

                String checkUser = "SELECT * FROM login WHERE ID = '" + user + "'";
                ResultSet rs = c.statement.executeQuery(checkUser);

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Username already exists. Choose another.");
                } else {
                    String insertQuery = "INSERT INTO login (ID, PW) VALUES ('" + user + "', '" + pass + "')";
                    c.statement.executeUpdate(insertQuery);
                    JOptionPane.showMessageDialog(null, "Registration Successful!");
                    setVisible(false);
                    new Login();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == backButton) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new SignUp();
    }
}