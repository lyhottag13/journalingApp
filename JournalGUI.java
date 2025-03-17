import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JournalGUI extends JFrame implements ActionListener {
    private JFrame frame;
    private JLabel label;
    private JPanel textPanel, buttonPanel;
    private JButton previousButton, nextButton, saveButton, deleteButton, firstButton, finalButton;
    private JTextPane pane;
    private JournalList entryList;
    public JournalGUI() {
        frame = new JFrame("Journaling App");
        label = new JLabel("Write to your heart's content!");
        textPanel = new JPanel(new GridLayout(2, 1));
        buttonPanel = new JPanel();
        saveButton = new JButton("Save"); 
        previousButton = new JButton("Previous");
        nextButton = new JButton("Next");
        firstButton = new JButton("First");
        finalButton = new JButton("Final");
        deleteButton = new JButton("Delete");
        pane = new JTextPane();
        
        entryList = new JournalList();
        entryList.fillList();
        
        try {
            pane.setText(entryList.getTail());
        } catch (Exception e) {}
        
        label.setHorizontalAlignment(SwingConstants.CENTER);

        saveButton.addActionListener(this);
        previousButton.addActionListener(this);
        nextButton.addActionListener(this);
        deleteButton.addActionListener(this);
        firstButton.addActionListener(this);
        finalButton.addActionListener(this);

        frame.setLayout(new GridLayout(2, 1));
        frame.add(textPanel);
        frame.add(buttonPanel);
        textPanel.add(label);
        textPanel.add(pane);
        buttonPanel.add(saveButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(previousButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(finalButton);
        buttonPanel.add(firstButton);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(new Dimension(500, 500));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == saveButton) {
                JournalWriter.write(pane.getText());
                entryList.add(pane.getText());
                pane.setText("");
            } else {
                if (e.getSource() == finalButton || (pane.getText().equals("") && !entryList.getSelector().equals(""))) {
                    entryList.selectFinal();
                } else if (e.getSource() == previousButton) {
                    entryList.selectPrevious();
                } else if (e.getSource() == nextButton) {
                    entryList.selectNext();
                } else if (e.getSource() == firstButton) {
                    entryList.selectFirst();
                } else if (e.getSource() == deleteButton) {
                    entryList.delete();
                }
                pane.setText(entryList.getSelector());
            }
        } catch (Exception paneException) {
            pane.setText("");
        }
    }
}
