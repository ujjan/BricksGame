import java.awt.*;

public class BricksGenerator {

    public int bricks[][];

    public int brickWidth;
    public int brickHeight;

    public BricksGenerator(int rows, int cols){

        bricks = new int[rows][cols];

        for (int i = 0; i <bricks.length ; i++) {

            for (int j = 0; j <bricks[0].length ; j++) {

                bricks[i][j] = 1;
                
            }
        }

        brickWidth = 540/cols;
        brickHeight = 150/rows;

    }

    public void draw(Graphics2D graphics2D ){
        for (int i = 0; i <bricks.length ; i++) {

            for (int j = 0; j <bricks[0].length ; j++) {
                if(bricks[i][j] > 0){

                    graphics2D.setColor(Color.pink);
                    graphics2D.fillRect(j*brickWidth +80, i*brickHeight+50, brickWidth, brickHeight);

                    graphics2D.setStroke(new BasicStroke(3));
                    graphics2D.setColor(Color.darkGray);
                    graphics2D.drawRect(j*brickWidth +80, i*brickHeight+50, brickWidth, brickHeight);
                }

            }
        }

    }



    public void bricksValue(int value, int row, int col){

        bricks[row][col]= value;

    }
}
