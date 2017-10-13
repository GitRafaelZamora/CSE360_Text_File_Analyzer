import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;

public class Login extends JPanel implements ActionListener {
    // Labels and TextFields
    JLabel userLabel = new JLabel("Username: ");
    JTextField userTF = new JTextField();
    JLabel passL = new JLabel("Password: ");
    JPasswordField passTF = new JPasswordField();

    JPanel loginP = new JPanel(new GridLayout(3,2));
    JPanel panel = new JPanel();

    JButton login = new JButton("Login");
    JButton register = new JButton("Register");

    CardLayout cl;

    Login() {
        setLayout(new CardLayout());
        loginP.add(userLabel);
        loginP.add(userTF);
        loginP.add(passL);
        loginP.add(passTF);
        login.addActionListener(this);
        register.addActionListener(this);
        loginP.add(login);
        loginP.add(register);
        panel.add(loginP);
        add(panel, "login");
        cl = (CardLayout) getLayout();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            try {
                BufferedReader input = new BufferedReader(new FileReader("passwords.txt"));
                String pass = null;
                String line = input.readLine();
                while (line != null) {
                    StringTokenizer st = new StringTokenizer(line);
                    if (userTF.getText().equals(st.nextToken())) {
                        pass = st.nextToken();
                    }
                    line = input.readLine();
                }
                input.close();
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(new String(passTF.getPassword()).getBytes());
                byte byteData[] = md.digest();
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < byteData.length; i++) {
                    sb.append(Integer.toString((byteData[i] & 0xFF) + 0x100, 16).substring(1));
                }
                if (pass.equals(sb.toString())) {
                    add(new Browser(userTF.getText()), "fb");
                    cl.show(this, "fb");
                }
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getSource() == register) {
            add(new Register(), "register");
            cl.show(this, "register");
        }

    }
}
