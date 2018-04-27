package bl;

import beans.Film;
import beans.FilmEnum;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class FilmTableModel extends AbstractTableModel {
    
    private List<Film> filme = new ArrayList<>();

    public void add(Film f) {
        filme.add(f);
        super.fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return filme.size();
    }

    @Override
    public int getColumnCount() {
        return FilmEnum.values().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Film f = filme.get(rowIndex);
        switch(FilmEnum.values()[columnIndex]) {
            case TITLE:
                return f.getTitle();
            case DESCRIPTION:
                return f.getDescription();
            case LANGUAGE:
                return f.getLanguage();
            case PRICE:
                return f.getPrice();
            case YEAR:
                return f.getYear();
            default:
                return "???";
        }
    }

    @Override
    public String getColumnName(int column) {
        return FilmEnum.values()[column].name();
    }

    public void clear() {
        filme.clear();
        super.fireTableDataChanged();
    }
    
    
    
}
