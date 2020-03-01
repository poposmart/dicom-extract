package dicom;

import com.pixelmed.dicom.AttributeList;
import com.pixelmed.dicom.DicomInputStream;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class App 
{

    private File file;
    private Scanner scn;
    private int length;
    private String[][] strs;

    public void meta(String filePath) {
        file = new File(filePath);
        try {
            DicomInputStream dic = new DicomInputStream(file);
            System.out.println(dic);
            AttributeList list = new AttributeList();
            list.read(dic);
            scn = new Scanner(list.toString());
            length = list.keySet().size();
             System.out.println(list.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String[][] getLine() {
        String test = "(0x0010,0x0010) PatientName VR=<PN> VL=<0x28> <Femoral trombenarterectomy^Case Report: >";
        System.out.println(Arrays.toString(test.split("[\\<\\:\\>]")));
        System.out.println(test.split("[\\<.\\>]")[3]);
        System.out.println(test.split("[\\<.\\>]")[2]);
        System.out.println(test.split("[\\<.\\>]")[1]);
        System.out.println(test.split("[\\<.\\>]")[0]);



        strs = new String[length+20][5];
        int i = 0;
       while(scn.hasNextLine()) {
           try {
               String line = scn.nextLine();
               strs[i][0] = "(" + line.split("[\\(\\)]")[1] + ")";
               strs[i][1] = line.split(" ")[1];
               strs[i][2] = line.split("[<>]")[1];
               strs[i][3] = line.split("[<>]")[3];
               strs[i][4] = line.split("[\\<\\:\\>]")[4];
           } catch (Exception e) {
               continue;
           }
           i++;

       }
       return strs;
    }



    public static void main(String[] args) {

    }
}
