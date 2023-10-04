import javax.swing.*;

public class Snake {
    public static void main(String[] args){
        int boardWidth = 600;
        int boardHeight = boardWidth;

        JFrame frame =new JFrame("Snake");                  //Frame wird erzeugt
        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        snakeGame snakeGame = new snakeGame(boardWidth,boardHeight);    //Jpanel wird erzeugt und über den JFrame gelegt
        frame.add(snakeGame);
        frame.pack();
        snakeGame.requestFocus();
    }
}
