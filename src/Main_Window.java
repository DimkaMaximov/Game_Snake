import javax.swing.*;
import java.awt.*;

public class Main_Window extends JFrame {
    public Main_Window () {
        setTitle("Малинопожиратель");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(650, 675);
        Toolkit toolkit = Toolkit.getDefaultToolkit(); // набор инструментов для работы с фреймами
        Dimension dimension = toolkit.getScreenSize(); // получает размер разрешения экрана
        setLocation(dimension.width / 10, dimension.height / 10); // установить появление окна от dimension
        add (new Game_Field());
        setVisible(true);

    }


    public static void main(String[] args) {
        Main_Window mw = new Main_Window();

    }
}
