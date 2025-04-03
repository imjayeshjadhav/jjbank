package bank.management.system;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PinChange extends JFrame implements ActionListener{

    JPasswordField pin, repin;
    JButton change,back;
    String pinNumber;
    PinChange(String pinNumber){

        this.pinNumber = pinNumber;

        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text = new JLabel("CHANGE YOUR PIN: ");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD , 16));
        text.setBounds(250,280,500,35);
        image.add(text);

        JLabel pinText = new JLabel("New PIN: ");
        pinText.setForeground(Color.WHITE);
        pinText.setFont(new Font("System", Font.BOLD , 16));
        pinText.setBounds(165,320,180,25);
        image.add(pinText);

        pin = new JPasswordField("");
        pin.setBounds(330, 320, 180, 25);
        pin.setFont(new Font("Raleway", Font.BOLD, 25));
        image.add(pin);

        JLabel rePinText = new JLabel("Re-Enter New PIN: ");
        rePinText.setForeground(Color.WHITE);
        rePinText.setFont(new Font("System", Font.BOLD , 16));
        rePinText.setBounds(165,360,180,25);
        image.add(rePinText);

        repin = new JPasswordField("");
        repin.setBounds(330, 360, 180, 25);
        repin.setFont(new Font("Raleway", Font.BOLD, 25));
        image.add(repin);

        change = new JButton("Change");
        change.setBounds(355,485,150,30);
        change.addActionListener(this);
        image.add(change);

        back = new JButton ("Back");
        back.setBounds(355, 520, 150,30);
        back.addActionListener(this);
        image.add(back);

        setTitle("PIN Change ");
        setSize(900,900);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae){

        if (ae.getSource() == change){
            try {
                String npin = pin.getText();
                String rpin = repin.getText();
    
                if (!npin.matches("\\d{4}")) {
                    JOptionPane.showMessageDialog(null, "PIN must be exactly 4 digits");
                    return;
                }
    
                if (!rpin.matches("\\d{4}")) {
                    JOptionPane.showMessageDialog(null, "Re-entered PIN must be exactly 4 digits");
                    return;
                }
                
                if (!npin.equals(rpin)){
                    JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                    return;
                }

                if (npin.equals("") ){
                    JOptionPane.showMessageDialog(null,"Please enter your new 4-digit PIN");
                }

                if (rpin.equals("") ){
                    JOptionPane.showMessageDialog(null,"Please Re-Enter new PIN");
                }

 
                Conn c = new Conn();
                String query1 = "update bank set pin = '"+rpin+"' where pin='"+pinNumber+"' ";
                String query2 = "update login set pin = '"+rpin+"' where pin='"+pinNumber+"' ";
                String query3 = "update signup3 set pin = '"+rpin+"' where pin='"+pinNumber+"' ";

                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                c.s.executeUpdate(query3);

                JOptionPane.showMessageDialog(null, "PIN changed successfully");

                setVisible(false);
                new Transaction(rpin).setVisible(true);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else{
            setVisible(false);
            new Transaction(pinNumber).setVisible(true);
        }
        
    }
    public static void main(String[] args) {
        new PinChange("").setVisible(true);
    }
}
