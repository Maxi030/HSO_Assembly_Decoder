import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class snakeGame extends JPanel implements ActionListener, KeyListener {

    int boardWith;
    int boardHeight;
    int tileSize = 25; //Einteilung in 25x25 Tiles
    //Snake
    Tile snakeHead;
    ArrayList<Tile> snakeBody;
    //Food
    Tile Food;
    Random randomobject;
    //GameLogik
    Timer gameLoop;
    int velocityX;
    int velocityY;


    private class Tile {     //Positionieren von Tiles(Food,Snake)
        int x;
        int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    snakeGame(int boardWith, int boardHeight) {                              //Konstruktor für das JPanel
        this.boardHeight = boardHeight;
        this.boardWith = boardWith;
        setPreferredSize(new Dimension(this.boardWith, this.boardHeight));
        setBackground(Color.black);
        snakeHead = new Tile(5, 5);
        Food = new Tile(10, 10);
        randomobject = new Random(); //Food
        placeFood();
        gameLoop = new Timer(100, (ActionListener) this);
        gameLoop.start();
        velocityX = 0;
        velocityY = 0;
        addKeyListener(this);
        setFocusable(true);
        snakeBody = new ArrayList<Tile>();
    }

    public void paintComponent(Graphics g) {                                                 //Zeichnen von Tiles
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        //Food
        g.setColor(Color.RED);
        g.fillRect(Food.x * tileSize, Food.y * tileSize, tileSize, tileSize);
        //SnakeHead
        g.setColor(Color.GREEN);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);
        //SnakeBody
        for (int i = 0; i <snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);
            g.fillRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize);
        }

    }

    public void placeFood() {                                            //Food random platzieren
        Food.x = randomobject.nextInt(boardWith / tileSize);
        Food.y = randomobject.nextInt(boardHeight / tileSize);
    }

    public boolean collission(Tile tile1, Tile tile2) {     //prüfen ob sich 2Tiles überschneiden
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    public void move() {                                //Bewegung der Schlange
        //food
        if (collission(snakeHead, Food)) {
            snakeBody.add(new Tile(Food.x, Food.y));
            placeFood();
        }
        //snakeBody
        for(int i= snakeBody.size()-1;i>0;i--){
            Tile snakePart =  snakeBody.get(i);
            if(i==0){
                snakePart.x= snakeHead.x;
                snakePart.y = snakeHead.y;
            }
            else{
                Tile prevSnakePart= snakeBody.get(i-1);
                snakePart.x=prevSnakePart.x;
                snakePart.y= prevSnakePart.y;
            }
        }
        //snakeHead
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

    }

    @Override
    public void actionPerformed(ActionEvent e) {    //GameLoop
        move();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) { //irrellevant

    }

    @Override
    public void keyPressed(KeyEvent e) {        //Snake steuern
        if (e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
            velocityX = 0;
            velocityY = -1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
            velocityX = 0;
            velocityY = 1;

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
            velocityX = 1;
            velocityY = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
            velocityX = -1;
            velocityY = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { //irrellevant

    }

}
