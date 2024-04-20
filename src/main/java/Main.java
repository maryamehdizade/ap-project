import view.pages.Game;
import view.pages.GamePanel;
import view.pages.Menu;
import view.pages.Setting;

public class Main {
    public static void main(String[] args) throws Exception {
        new Menu(new GamePanel());
    }

}
