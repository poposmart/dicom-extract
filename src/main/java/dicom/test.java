package dicom;

import javax.swing.*;
import java.awt.*;

public class test {
  private JFrame frame;
  private JLabel title = new JLabel("DICOM MetaData Viewer");
  private JPanel mainPanel;
  private JButton browse;

  public void setUpGui() {
    frame = new JFrame("DICOM MetaData");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GridLayout layout = new GridLayout();
    mainPanel = new JPanel(layout);
    mainPanel.add(title);
    browse = new JButton("Browse");
    mainPanel.add(browse);
    frame.getContentPane().add(mainPanel);

    frame.setVisible(true);
  }

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception ex) { ex.printStackTrace(); }
    new test().setUpGui();
  }
}
