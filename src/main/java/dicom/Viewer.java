package dicom;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Viewer {
  private JButton button1;
  private JPanel mainPanel;
  private JTable table1;
  private JScrollPane scr;
  private JFrame frame;
  ArrayList<ArrayList<String>> strs = new ArrayList<>();

  public Viewer() {
    button1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Choose file with .dcm extension");
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          if (jfc.getSelectedFile().isDirectory()) {
            System.out.println("You selected the file: " + jfc.getSelectedFile());
          }
        }
      }
    });
  }

  public void setUpGUI() {
    frame = new JFrame("DICOM Project");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.getContentPane().add(mainPanel);
    frame.setBounds(50, 50, 600, 600);
    frame.pack();
    frame.setVisible(true);
  }

  public static void main(String [] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception ex) { ex.printStackTrace(); }
    new Viewer().setUpGUI();
  }

  private void createUIComponents() {
    App test = new App();
    test.meta("D:\\kulfiKaam\\DevelopmentCode\\extractor\\src\\main\\java\\dicom\\test1.dcm");
    String data[][] = test.getLine();
    //System.out.println(Arrays.toString(data));
    String column[]={"Tag ID","VR", "Length", "Description", "Value"};
    table1 = new JTable(data, column);
    scr = new JScrollPane(table1);
  }
}
