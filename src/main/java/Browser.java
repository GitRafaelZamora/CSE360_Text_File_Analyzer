import sun.jvm.hotspot.ui.Editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Browser extends JPanel implements ActionListener {
    JLabel label = new JLabel("File List: ");
    JButton newFile = new JButton("New File");
    JButton open = new JButton("Open");
    JTextField newFileTF = new JTextField(10);
    ButtonGroup bg;
    File directory;

    Browser(String dir) {
        directory = new File(dir);
        directory.mkdir();
        JPanel fileList = new JPanel(new GridLayout(directory.listFiles().length + 3, 1));
        fileList.add(label);
        bg = new ButtonGroup();
        for (File file : directory.listFiles()) {
            JRadioButton radio = new JRadioButton(file.getName());
            radio.setActionCommand(file.getName());
            bg.add(radio);
            fileList.add(radio);
        }
        JPanel newPanel = new JPanel();
        newPanel.add(newFileTF);
        newPanel.add(newFile);
        newFile.addActionListener(this);
        open.addActionListener(this);
        fileList.add(open);
        fileList.add(newPanel);
        add(fileList);
    }

    public void actionPerformed(ActionEvent e) {
        Login login = (Login) getParent();

        if (e.getSource() == open) {
            login.add(new TextEditor(directory.getName() + "\\" + bg.getSelection().getActionCommand()), "editor");
            login.cl.show(login, "editor");
        }
        if (e.getSource() == newFile) {
            String file = directory.getName() + "\\" + newFileTF.getText() + ".txt";
            if (newFileTF.getText().length() > 0 && !(new File(file).exists())) {
                login.add(new TextEditor(file) , "editor");
                login.cl.show(login, "editor");
            }
        }

    }
}
