package bl;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class TableRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = (new DefaultTableCellRenderer())
                .getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        c.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 14));
        if(row%2 == 0) {
            c.setBackground(Color.GRAY);
        }
        
        return c;
    }

}
