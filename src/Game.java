
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements KeyListener, ActionListener{


    private boolean play = false;
    private int score = 0;
    private int numberOfBricks = 21;
    private Timer time;
    private int delay = 8;


    private int playerX = 310;
    private int ballX = 120;
    private int ballY = 350;

    private int ballXDir = -1;
    private int ballYDir = -2;

    private BricksGenerator bricksGenerator;


    public Game(){
        bricksGenerator = new BricksGenerator(3,7);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time = new Timer(delay, this);
        time.start();
    }

    public void paint(Graphics g){




        //Background
        g.setColor(Color.darkGray);
        g.fillRect(1, 1, 692, 592);


        // drawing bricks by calling draw method of BricksGenerator class

        bricksGenerator.draw((Graphics2D)g);


        //borders

        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);


        //paddle

        g.setColor(Color.yellow);
        g.fillRect(playerX, 550, 100, 8);

        //Ball
        g.setColor(Color.green);
        g.fillOval(ballX, ballY, 20, 20);


        // score

        g.setColor(Color.black);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(""+score, 590, 30);

        // Game over

        if(ballY > 570){

            play = false;
            ballYDir = 0;
            ballXDir = 0;

            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over Score is: "+score, 190, 300);


            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter To Play Again", 230, 350);

        }
        // you won
        if(numberOfBricks <= 0){

            play = false;
            ballYDir = 0;
            ballXDir = 0;

            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Won! Score is: "+score, 190, 300);


            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter To Play Again", 230, 350);


        }


        g.dispose();

    }





    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){

            if(playerX >= 600){
                playerX = 600;
            }else{
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX < 10){
                playerX = 10;
            }else{
                moveLeft();
            }

        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER){

            if(!play){

                play = true;
                ballX = 120;
                ballY = 350;
                ballXDir = -1;
                ballYDir = -2;
                playerX = 310;
                score = 0;
                numberOfBricks = 21;

                bricksGenerator = new BricksGenerator(3, 7);
                repaint();

            }
        }

    }

    public void moveRight(){
        play = true;
        playerX += 20;
    }

    public void moveLeft(){
        play = true;
        playerX -= 20;
    }


    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {

        time.start();

        if(play){


            if(new Rectangle(ballX, ballY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))){


                ballYDir = -ballYDir;
            }


           A: for (int i = 0; i <bricksGenerator.bricks.length ; i++) {

                for (int j = 0; j <bricksGenerator.bricks[0].length ; j++) {


                    if(bricksGenerator.bricks[i][j] > 0 ){

                        int brickXPos = j * bricksGenerator.brickWidth + 80;
                        int brickYPos = i * bricksGenerator.brickHeight + 50;
                        int brickWidth = bricksGenerator.brickWidth;
                        int brickHeight = bricksGenerator.brickHeight;

                        Rectangle rec = new Rectangle(brickXPos,brickYPos, brickWidth, brickHeight);
                        Rectangle ballRec = new Rectangle(ballX, ballY,20,20);
                        Rectangle brickRec = rec;


                        if(ballRec.intersects(brickRec)){

                            bricksGenerator.bricksValue(0, i, j);

                            numberOfBricks--;
                            score += 5;


                            if(ballX+19 <= brickRec.x || ballX+1 >= brickRec.x + brickWidth){

                                ballXDir = -ballXDir;
                            }else {
                                ballYDir = -ballYDir;
                            }

                            break A;
                        }
                    }

                }

            }

            ballX += ballXDir;
            ballY += ballYDir;

            if(ballX < 0){
                ballXDir = -ballXDir;
            }
            if(ballY < 0){
                ballYDir = -ballYDir;
            }
            if(ballX > 670){
                ballXDir = -ballXDir;
            }

        }


        repaint();

    }
}
