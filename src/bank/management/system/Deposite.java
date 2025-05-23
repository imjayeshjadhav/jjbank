package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Deposite extends JFrame implements ActionListener{

    JButton deposite, back;
    JTextField amount;
    String pinNumber;

    Deposite(String pinNumber){

        this.pinNumber = pinNumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2= i1.getImage().getScaledInstance(900,900 , Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel (i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text = new JLabel ("Enter the amount you want to deposite");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD,16));
        text.setBounds(170,400,400,20);
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD,22));
        amount.setBounds(170,350,320,25);
        image.add(amount);

        deposite= new JButton("Deposite");
        deposite.setBounds(355, 485, 150, 30);
        deposite.addActionListener(this);
        image.add(deposite);

        back= new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900,900);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed (ActionEvent ae){

        if (ae.getSource() == deposite){

            String samount = amount.getText();
            Date date = new Date();

            if (samount.equals("")){
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to deposite");
            }
            else{

                try {
                    Conn c = new Conn();
                    String query = " insert into bank values('"+pinNumber+"', '"+date+"','Deposite','"+samount+"')  ";
                    c.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Rs "+samount + " deposited successfully");  
                    setVisible(false);
                    new Transaction(pinNumber).setVisible(true);

                } catch (Exception e) {
                    System.out.println(e);
                }
                
            }

        }
        else if (ae.getSource()== back){
            new Transaction(pinNumber).setVisible(true);
        }
    }


    public static void main(String[] args) {
        new Deposite("");
    }
}