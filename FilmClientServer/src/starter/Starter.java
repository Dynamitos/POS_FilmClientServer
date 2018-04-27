package starter;

import gui.FilmClientGui;
import gui.FilmServerGUI;

public class Starter {
    
    public static void main(String[] args) {
        FilmServerGUI server = new FilmServerGUI();
        server.setVisible(true);
        FilmClientGui client1 = new FilmClientGui();
        client1.setVisible(true);
        FilmClientGui client2 = new FilmClientGui();
        client2.setVisible(true);
    }
    
}
