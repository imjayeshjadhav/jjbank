package bank.management.system;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JButton deposite, withdraw, miniStatement, fast, pinChange, balanceQ, exit;
    String pinNumber;

    FastCash(String pinNumber){

        this.pinNumber=pinNumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2= i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text= new JLabel("Select Withdrawl Amount");
        text.setBounds(210, 300 ,700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD,16));
        image.add(text);

        deposite = new JButton ("Rs 100");
        deposite.setBounds(170,415,150,30);
        deposite.addActionListener(this);
        image.add(deposite);

        withdraw = new JButton ("Rs 500");
        withdraw.setBounds(355,415,150,30);
        withdraw.addActionListener(this);
        image.add(withdraw);

        fast = new JButton ("Rs 1000");
        fast.setBounds(170,450,150,30);
        fast.addActionListener(this);
        image.add(fast);

        miniStatement = new JButton ("Rs 2000");
        miniStatement.setBounds(355,450,150,30);
        miniStatement.addActionListener(this);
        image.add(miniStatement);

        pinChange = new JButton ("Rs 10000");
        pinChange.setBounds(355,485,150,30);
        pinChange.addActionListener(this);
        image.add(pinChange);

        balanceQ = new JButton ("Rs 5000");
        balanceQ.setBounds(170,480,150,30);
        balanceQ.addActionListener(this);
        image.add(balanceQ);

        exit = new JButton ("Back");
        exit.setBounds(355,520,150,30);
        exit.addActionListener(this);
        image.add(exit);

        setSize(900,900);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == exit){
            setVisible(false);
            new Transaction(pinNumber).setVisible(true);
        }
        else {
            String amount = ((JButton) ae.getSource()).getText().substring(3);

            Conn c = new Conn();

            try {
                ResultSet rs= c.s.executeQuery("select * from bank where pin= '"+pinNumber+"'");
                int balance = 0;

                while(rs.next()){
                    if (rs.getString("type").equals("Deposite")){
                        balance += Integer.parseInt(rs.getString ("amount")) ;
                    }else{
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }
                if (ae.getSource() != exit && balance < Integer.parseInt(amount)){
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }
                Date date = new Date();
                String query = "insert into bank values('"+pinNumber+"','"+date+"','Withdrawl','"+amount+"' )";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Rs " + amount +" Debited successfully");
                setVisible(false);
                new Transaction(pinNumber).setVisible(true);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        
    }
    public static void main(String[] args) {
        new FastCash("");
    }
}