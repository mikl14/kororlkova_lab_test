import Config.Config;
import Datas.Data_Buffer;
import Datas.Data_Reader;
import Datas.Data_Writer;
import Datas.Telemetry_data;
import Tm_dat.TM_base;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.ComponentView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI gui = new GUI();
            }
        });
    }
}