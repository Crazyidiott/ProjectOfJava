package view;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InstructionFrame extends JFrame {
    public InstructionFrame(){
        this.setSize(500,600);
        this.setLocationRelativeTo(null);
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setFont(new Font(null, Font.PLAIN, 15));
        List<String> fileData = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("Rules.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileData.add(line);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String str:fileData){textArea.append(str+"\n");}
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.setContentPane(scrollPane);
        this.setVisible(true);

    }

}
