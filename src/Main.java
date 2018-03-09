import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame();

        Game game = new Game();

        frame.setBounds(10, 10, 700,600);
        frame.setResizable(false);
        frame.setTitle("Bricks Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.setVisible(true);




    }
}
