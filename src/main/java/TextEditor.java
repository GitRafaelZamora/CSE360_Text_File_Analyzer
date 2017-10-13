import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor extends JPanel implements ActionListener {
    File file;
    JButton save = new JButton("Save");
    JButton saveC = new JButton("Save and Close");
    JTextArea text = new JTextArea(20, 40);

    public TextEditor(String s) {
        file = new File(s);
        save.addActionListener(this);
        saveC.addActionListener(this);
        if (file.exists()) {
            try {
                BufferedReader input = new BufferedReader(new FileReader(file));
                String line = input.readLine();
                while (line != null) {
                    text.append(line + "\n");
                    line = input.readLine();
                }
                input.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        add(save);
        add(saveC);
        add(text);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            FileWriter out = new FileWriter(file);
            out.write(text.getText());
            out.close();
            if (e.getSource() == saveC) {
                Login login = (Login) getParent();
                login.cl.show(login, "fb");
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
