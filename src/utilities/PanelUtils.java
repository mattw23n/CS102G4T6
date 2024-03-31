package utilities;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public final class PanelUtils {

    private PanelUtils(){

    }

    public static void initializeLabel(JLabel label, Font font, Color fgColor, Color bgColor){
        label.setFont(font);
        label.setForeground(fgColor);
        label.setBackground(bgColor);

    }
    
}
