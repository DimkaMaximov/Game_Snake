import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Game_Field extends JPanel implements ActionListener {
    private final int SIZE = 576;
    private final int DOT_SIZE = 64;
    private final int ALL_DOTS = 100;
    private Image dot;
    private Image raspberry;
    private int raspberryX;
    private int raspberryY;
    private int[] x = new int [ALL_DOTS];
    private int[] y = new int [ALL_DOTS];
    private int dots;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;


    public Game_Field () {
        setBackground(Color.darkGray);
        loadImages();
        initGame();
        addKeyListener(new KeyField());
        setFocusable(true);
     }

    public void initGame() {
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 64 - i*DOT_SIZE;
            y[i] = 64;
        }
        timer = new Timer (200, this);
        timer.start ();
        createRaspberry ();
    }
    public void createRaspberry () {
        raspberryX = new Random().nextInt(10)*DOT_SIZE;
        raspberryY = new Random().nextInt(10)*DOT_SIZE;
    }

    public void loadImages (){
        ImageIcon iiR = new ImageIcon("RaspberryBig.png");
        raspberry = iiR.getImage();
        ImageIcon iiK = new ImageIcon("KatiaBig.png");
        dot = iiK.getImage();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            g.drawImage(raspberry, raspberryX, raspberryY, this);
            for (int i = 0; i < dots ; i++) {
                g.drawImage(dot, x[i], y[i], this);
            }
        }
        else {
            String str = "Game Over";
            //Font f = new Font ("Arial", 14, Font.BOLD);
            g.setColor(Color.RED);
            //g.setFont(f);
            g.drawString(str, 125, SIZE/2);
        }
    }

    public void move () {
        for (int i = dots; i > 0 ; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if (left) {
            x[0] -= DOT_SIZE;
        }
        if (right) {
            x[0] += DOT_SIZE;
        }
        if (up) {
            y[0] -= DOT_SIZE;
        }
        if (down) {
            y[0] += DOT_SIZE;
        }
    }

    public void checkRaspberry () {
        if  (x[0] == raspberryX && y[0] == raspberryY) {
            dots++;
            createRaspberry();
        }
    }

    public void checkCollisions () {
        for (int i = dots; i > 0 ; i--) {
            if (i > 4 && x[0] == x[i] && y[0] == y[i]) {
                inGame = false;
            }
        }
        if (x[0] > SIZE) {
            inGame = false;
        }
        if (x[0] < 0) {
            inGame = false;
        }
        if (y[0] > SIZE) {
            inGame = false;
        }
        if (y[0] < 0) {
            inGame = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkRaspberry ();
            checkCollisions ();
            move();
        }
        repaint(); // перерисовывает окно, для отображения изменений
    }
    class KeyField extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT && !right) {
                left = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_RIGHT && !left) {
                right = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_UP && !down) {
                up = true;
                left = false;
                right = false;
            }
            if (key == KeyEvent.VK_DOWN && !up) {
                down = true;
                left = false;
                right = false;
            }
        }
    }
}
