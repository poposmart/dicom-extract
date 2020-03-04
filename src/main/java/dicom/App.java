package dicom;

import com.pixelmed.dicom.AttributeTag;
import com.pixelmed.dicom.DicomDictionary;
import com.pixelmed.dicom.DicomDictionaryBase;
import com.pixelmed.dicom.DicomInputStream;
import com.pixelmed.dicom.Attribute;
import com.pixelmed.dicom.AttributeList;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class App {
  private JButton button1;
  private JPanel mainPanel;
  private JTable table1;
  private JScrollPane scr;
  private JFrame frame;
  String[][] data;
  DefaultTableModel contactTableModel;

  private File file;
  private Scanner scn;
  private int length;
  private String[][] strs;
  private Set set;
  private AttributeList list;

  public String[][] meta(String filePath) {
    file = new File(filePath);
    try {
      DicomInputStream dic = new DicomInputStream(file);
      System.out.println(dic);
      list = new AttributeList();
      list.setDecompressPixelData(false);
      list.read(dic);
      list.removeUnsafePrivateAttributes();
      scn = new Scanner(list.toString());
      length = list.keySet().size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    itStr();
    return strs;
  }


  private String[][] itStr() {
    strs = new String[list.size()][6]; int i =0;
    DicomDictionaryBase bse = new DicomDictionary();
    for(Map.Entry<AttributeTag, Attribute> entry : list.entrySet()){
      AttributeTag key = entry.getKey();
      Attribute value = entry.getValue();
      strs[i][0] = key.toString();
      strs[i][1] = value.getVRAsString();
      strs[i][2] = Integer.toString(value.getVM());
      strs[i][3] = Long.toString(value.getVL());
      strs[i][4] = bse.getFullNameFromTag(key);
      strs[i][5] = value.getSingleStringValueOrEmptyString();
      i++;
    }
    return strs;
  }

  public App() {
    button1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Choose file with .dcm extension");
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          System.out.println(jfc.getSelectedFile().getAbsolutePath());
          data = meta(jfc.getSelectedFile().getAbsolutePath());
          System.out.println("read!");
          String column[]={"Tag ID","VR","VM", "Length", "Description", "Value"};
          contactTableModel.setDataVector(data, column);
          contactTableModel.fireTableDataChanged();
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
      new App().setUpGUI();
    } catch (Exception ex) { ex.printStackTrace(); }
  }

  private void createUIComponents() {
    //
    // App test = new App();
    //test.meta("D:\\kulfiKaam\\DevelopmentCode\\extractor\\src\\main\\java\\dicom\\test1.dcm");
    //String data[][] = test.itStr();
    data = new String[0][6];
    String column[]={"Tag ID","VR","VM", "Length", "Description", "Value"};
    if (table1 == null) {
      table1 = new JTable() {
        public boolean isCellEditable(int nRow, int nCol) {
          return false;
        }
      };
    }
    contactTableModel = (DefaultTableModel) table1.getModel();
    contactTableModel.setDataVector(data, column);
    scr = new JScrollPane(table1);
  }

}
