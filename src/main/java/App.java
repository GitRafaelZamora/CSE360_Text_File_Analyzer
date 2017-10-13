import javax.swing.*;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Text Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        Login login = new Login();
        frame.add(login);
        frame.setVisible(true);
        //hello
    }
}
