package bank.management.system;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Transaction extends JFrame implements ActionListener {

    JButton deposite, withdraw, miniStatement, fast, pinChange, balance, exit;
    String pinNumber;
    Transaction(String pinNumber){
        this.pinNumber=pinNumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2= i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text= new JLabel("Please select your Transaction");
        text.setBounds(210, 300 ,700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD,16));
        image.add(text);

        deposite = new JButton ("Deposite");
        deposite.setBounds(170,415,150,30);
        deposite.addActionListener(this);
        image.add(deposite);

        withdraw = new JButton ("Withdraw");
        withdraw.setBounds(355,415,150,30);
        withdraw.addActionListener(this);
        image.add(withdraw);

        fast = new JButton ("Fast Cash");
        fast.setBounds(170,450,150,30);
        fast.addActionListener(this);
        image.add(fast);

        miniStatement = new JButton ("Mini Statement");
        miniStatement.setBounds(355,450,150,30);
        miniStatement.addActionListener(this);
        image.add(miniStatement);

        pinChange = new JButton ("Change PIN ");
        pinChange.setBounds(355,485,150,30);
        pinChange.addActionListener(this);
        image.add(pinChange);

        balance = new JButton ("Balance");
        balance.setBounds(170,485,150,30);
        balance.addActionListener(this);
        image.add(balance);

        exit = new JButton ("Exit");
        exit.setBounds(355,520,150,30);
        exit.addActionListener(this);
        image.add(exit);

        setSize(900,900);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == exit){
            System.exit(0);
        }
        else if (ae.getSource() == deposite){
            setVisible(false);
            new Deposite(pinNumber).setVisible(true);
        }
        else if (ae.getSource() == withdraw){
            setVisible(false);
            new Withdraw(pinNumber).setVisible(true);
        }
        else if (ae.getSource() == fast){
            setVisible(false);
            new FastCash(pinNumber).setVisible(true);
        }
        else if (ae.getSource() == pinChange){
            setVisible(false);
            new PinChange(pinNumber).setVisible(true);
        }
        else if (ae.getSource() == balance){
            setVisible(false);
            new BalanceEnquiry(pinNumber).setVisible(true);
        }
        else if (ae.getSource() ==miniStatement){
            new MiniStatement(pinNumber).setVisible(true);
        }
    }
    public static void main(String[] args) {
        new Transaction("");
    }
}