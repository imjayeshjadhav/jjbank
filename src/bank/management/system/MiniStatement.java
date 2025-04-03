package bank.management.system;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class MiniStatement extends JFrame {

    String pinNumber;
    MiniStatement(String pinNumber)
    {
        this.pinNumber=pinNumber;

        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        //add(image);

        JLabel mini = new JLabel();
        mini.setBounds(20,140,400,200);
        add(mini);

        JLabel bank = new JLabel("JJ Bank");
        bank.setBounds(150,20,100,20);
        add(bank);

        JLabel card=  new JLabel();
        card.setBounds(20,80,300,20);
        add(card);

        JLabel balance = new JLabel();
        balance.setBounds(20,400,300,20);
        add(balance);

        try {
            Conn c= new Conn();
            ResultSet rs =c.s.executeQuery("select * from login where pin = '"+pinNumber+"'");
            while(rs.next()){
                card.setText("Card Number : "+ rs.getString("cardNumber").substring(0,4)+"XXXXXXXX"+ rs.getString("cardNumber").substring(12,16));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            Conn c = new Conn();
            int bal = 0;
        
            ResultSet rsAll = c.s.executeQuery("SELECT * FROM bank WHERE pin = '"+pinNumber+"'");  
            while (rsAll.next()) {
                if (rsAll.getString("type").equals("Deposite")) {
                    bal += Integer.parseInt(rsAll.getString("amount"));
                } else {
                    bal -= Integer.parseInt(rsAll.getString("amount"));
                }
            }
        
            // Fetch only last 5 transactions
            ResultSet rsLast5 = c.s.executeQuery("SELECT * FROM bank WHERE pin = '"+pinNumber+"' ORDER BY date DESC LIMIT 5");  
            StringBuilder statementText = new StringBuilder("<html>");
        
            while (rsLast5.next()) {
                statementText.append(rsLast5.getString("date"))
                             .append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                             .append(rsLast5.getString("type"))
                             .append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                             .append(rsLast5.getString("amount"))
                             .append("<br><br>");
            }
        
            balance.setText("Your current account balance is Rs " + bal);
            statementText.append("</html>");
            mini.setText(statementText.toString());
        
        } catch (Exception e) {
            System.out.println(e);
        }

        setTitle("Mini Statement");
        setSize(400,600);
        setLocation(20,20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new MiniStatement("");
    }
}