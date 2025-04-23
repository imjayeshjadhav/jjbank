package bank.management.system;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JButton login, clear,signUp;
    JTextField cardTextField;
    JPasswordField pinTextField;

    Login(){

        setLayout(null);

        setTitle("Bank");
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel (i3);
        label.setBounds(70,10,100, 100);
        add(label);

        JLabel text= new JLabel("Welcome to Bank");
        text.setFont(new Font("Osward",Font.BOLD ,38));
        text.setBounds(200, 40, 400, 40);
        add(text);

        JLabel cardno= new JLabel("Card No:");
        cardno.setFont(new Font("Raleway",Font.BOLD ,28));
        cardno.setBounds(120, 150, 150, 30);
        add(cardno);

        cardTextField = new JTextField();
        cardTextField.setBounds(300,150, 250, 30);
        cardTextField.setFont(new Font("Arial", Font.BOLD ,14));
        add(cardTextField);

        JLabel pin= new JLabel("PIN:");
        pin.setFont(new Font("Raleway",Font.BOLD ,28));
        pin.setBounds(120,220, 250, 30);
        add(pin);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(300,220, 250, 30);
        pinTextField.setFont(new Font("Arial", Font.BOLD ,14));
        add(pinTextField);

        login = new JButton("SIGN IN");
        login.setBounds(300,300,100,30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        clear = new JButton("CLEAR");
        clear.setBounds(430,300,100,30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);

        signUp = new JButton("SIGN UP");
        signUp.setBounds(300,350,230,30);
        signUp.setBackground(Color.BLACK);
        signUp.setForeground(Color.WHITE);
        signUp.addActionListener(this);
        add(signUp);

        getContentPane().setBackground(Color.white);

        setSize(800,480);
        setVisible(true);
        setLocation(350,200); //left , right
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == clear){
            cardTextField.setText("");
            pinTextField.setText("");
        }
        else if(ae.getSource() == login){
            Conn c = new Conn();
            String cardNumber = cardTextField.getText();
            String pinNumber = pinTextField.getText();
            String query = "select * from login where cardNumber = '"+cardNumber+"' and pin = '"+pinNumber+"' ";
            try {
                ResultSet rs =c.s.executeQuery(query);
                if (rs.next()){
                    setVisible(false);
                    new Transaction(pinNumber).setVisible(true);
                }
                else{
                    JOptionPane.showConfirmDialog(null, "Incorrect card Number or pin");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else if(ae.getSource()==signUp){
            setVisible(false);
            new SignUp().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}