package com.mycompany.finalproject_flappybird_darren;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.io.*;
import javax.imageio.ImageIO;

/* Name: Darren Drew
 * Date: June 8th, 2022
 * Desc: Flap Burd 2: Game Logic and Graphics (what you see when you play)
 */
public class game extends JPanel implements ActionListener, KeyListener, MouseListener {

    //Variables
    final int DELAY = 1;
    Timer update_timer;
    Rectangle bird; //Bird Hitbox
    int height = 800;
    int width = 1200;
    int score = 0;
    int pipeCount = 0;
    int count = 3;
    int redCount = 0;
    int topGroundSpeed = 5;
    int topGroundLocation = 0;
    int topGroundDistance = 200;
    int[] pipeHeight = new int[4];
    int[] pipeLocation = new int[4];
    double vSpeed = 0;
    double gravity = 1; //Neg: Upside Down
    double jumpHeight = 18;//Neg: Upside Down
    double fallSpeed = 13;//Neg: Upside Down
    boolean jump = false;
    boolean start = false;
    boolean button = false;
    boolean cycle = true; //TEMP
    boolean die = false;
    boolean gOver = false;
    boolean upsideDown = false;
    int totalScore = 0;
    int highScore;
    String strHighScore;
    int codeEnter = 0;
    int codeStep = 0;
    boolean darrenAwesome = false;
    public Image texture;
    String filePath = "";

    public game() {
        Random random = new Random();
        setPreferredSize(new Dimension(width, height));
        bird = new Rectangle(width / 5, height / 2, 20, 20);
        update_timer = new Timer(DELAY, this);
        update_timer.start();
        //Static Variables
        pipeLocation[0] = width;
        pipeLocation[1] = width + 400;
        pipeLocation[2] = width + 800;
        pipeLocation[3] = width + 1200;
        pipeHeight[0] = random.nextInt((height - 150) / 4 + 25, ((height - 150) / 4) * 3) - 135;
        pipeHeight[1] = random.nextInt((height - 150) / 4 + 25, ((height - 150) / 4) * 3) - 135;
        pipeHeight[2] = random.nextInt((height - 150) / 4 + 25, ((height - 150) / 4) * 3) - 135;
        try {
            FileReader fr = new FileReader("highscore.txt");
            BufferedReader br = new BufferedReader(fr);
            strHighScore = br.readLine();
            highScore = Integer.valueOf(strHighScore);
            br.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    //===== GUI =========
    @Override
    public void paintComponent(Graphics g) { //Main Method
        Random random = new Random();
        //Colours
        super.paintComponent(g);
        Color birdColor = new Color(240, 233, 40);
        if (die == true || gOver == true) {
            if (redCount <= 100) {
                birdColor = new Color(240, 73, 40);
                redCount++;
            }
        } //DARREN
        if (darrenAwesome == true) {
            try {
                if (upsideDown == false) {
                    filePath = "src/main/resources/darren.png";
                } if (upsideDown == true){
                    filePath = "src/main/resources/darrenDown.png";
                }
                if (die == true || gOver == true) {
                    if (redCount <= 200) {
                        filePath = "src/main/resources/darrenRed.png";
                        redCount++;
                    }
                }
                texture = ImageIO.read(new File(filePath));
            } catch (Exception e) {
            }
        }
        //Colours
        Color skyColor = new Color(110, 190, 201);
        Color titleColor = new Color(247, 247, 165);
        Color topGroundColor = new Color(139, 201, 60);
        Color topGroundColor2 = new Color(115, 191, 46);
        Color groundColor = new Color(212, 211, 146);
        Color pipeColor = new Color(98, 178, 36);
        //Background
        g.setColor(skyColor);
        g.fillRect(0, 0, width, height);
        g.setColor(groundColor);
        g.fillRect(0, height - 100, width, 100);
        g.setColor(topGroundColor);
        g.fillRect(0, height - 150, width, 50);
        g.setColor(topGroundColor2);
        g.fillRect(topGroundLocation, height - 150, width / 12, 50);
        g.fillRect(topGroundLocation + 200, height - 150, width / 12, 50);
        g.fillRect(topGroundLocation + 400, height - 150, width / 12, 50);
        g.fillRect(topGroundLocation + 600, height - 150, width / 12, 50);
        g.fillRect(topGroundLocation + 800, height - 150, width / 12, 50);
        g.fillRect(topGroundLocation + 1000, height - 150, width / 12, 50);
        g.fillRect(topGroundLocation + 1200, height - 150, width / 12, 50);
        //Pipes
        if (start == true && die == false) {
            g.setColor(pipeColor);
            for (int loop = 0; loop < 4; loop++) {
                pipeLocation[loop] -= 3;
            }
            if (cycle == true) {
                pipeHeight[count] = random.nextInt((height - 150) / 4 + 25, ((height - 150) / 4) * 3) - 135;
                cycle = false;
                count++;
                if (count == 4) {
                    count = 0;
                }
            }
            for (int loop = 0; loop < 4; loop++) {
                g.fillRect(pipeLocation[loop], 0, width / 12, pipeHeight[loop]);//top
                g.fillRect(pipeLocation[loop], pipeHeight[loop] + 250, width / 12, (height - 150) - (pipeHeight[loop] + 100) - 150);//bottom
                if (pipeLocation[loop] <= -100) {
                    pipeLocation[loop] = width + 300;
                    cycle = true;
                }
            }
        } else {
            g.setColor(pipeColor);
            for (int loop = 0; loop < 4; loop++) {
                g.fillRect(pipeLocation[loop], 0, width / 12, pipeHeight[loop]);//top
                g.fillRect(pipeLocation[loop], pipeHeight[loop] + 250, width / 12, (height - 150) - (pipeHeight[loop] + 100) - 150);//bottom
                if (pipeLocation[loop] <= -100) {
                    pipeLocation[loop] = width + 300;
                    cycle = true;
                }
            }
        }
        //Bird
        g.setColor(birdColor);
        if (darrenAwesome == false) {
            g.fillRect(bird.x, bird.y, 50, 50);
        }
        //Bird Max Height
        if (bird.y < -200) {
            bird.y = -200;
        }
        //Conditions
        if (start == false && gOver == false) {
            //Title
            g.setFont(new Font("Comic Sans MS", 1, 100));
            g.setColor(titleColor);
            g.drawString("Click To Start", width / 4 - 50, height / 2 - 100);
        } else if (start == true && die == false) {
            topGroundLocation -= topGroundSpeed;
            if (topGroundLocation == -200) {
                topGroundLocation = 0;
            }
        }
        if (start == true) { //Falling
            vSpeed += gravity;
            bird.y += vSpeed;
            if (vSpeed > fallSpeed && upsideDown == false) {
                vSpeed = fallSpeed;
            } else if (vSpeed > fallSpeed && upsideDown == true && die == true) {
                vSpeed = fallSpeed;
            }
        }
        if (jump == true && die == false) { //Jump
            vSpeed = -jumpHeight;
            jump = false;
        }
        if (bird.y >= height - 150 - 50) { //Hit Ground - GAME OVER
            die = true;
        }
        for (int yLoop = 0; yLoop < 50; yLoop++) {//Hit Pipe - GAME OVER
            for (int xLoop = 0; xLoop < 50; xLoop++) {
                if ((bird.y + yLoop <= pipeHeight[pipeCount] || bird.y + yLoop >= pipeHeight[pipeCount] + 250) && (bird.x + xLoop >= pipeLocation[pipeCount] && bird.x + xLoop <= pipeLocation[pipeCount] + 100)) {
                    die = true;
                    gravity = 1; //Neg: Upside Down
                    jumpHeight = 18;//Neg: Upside Down
                    fallSpeed = 13;//Neg: Upside Down
                }
            }
        } //UPSIDE DOWN GAME OVER
        if (bird.y < -200 && upsideDown == true) {
            die = true;
            gravity = 13; //Neg: Upside Down
            fallSpeed = 13;//Neg: Upside Down
        } //GAME OVER
        if (die == true && bird.y >= height - 150 - 50) {
            if (start == true) {
                totalScore = score;
                if (totalScore > highScore) {
                    highScore = totalScore;
                    try {
                        FileWriter fw = new FileWriter("highscore.txt");
                        PrintWriter pw = new PrintWriter(fw);
                        pw.println(highScore);
                        pw.close();
                    } catch (IOException e) {
                    }
                }
            }
            gameOver(g);
        }
        //Score
        if (bird.x > pipeLocation[pipeCount] + 100 && start == true) {
            score++;
            pipeCount++;
            if (pipeCount == 4) {
                pipeCount = 0;
            }
        }
        if (gOver == false) {
            g.setFont(new Font("Comic Sans MS", 1, 100));
            g.setColor(titleColor);
            g.drawString("" + score, width / 2 - 50, height / 2 - 300);
        } //RED
        //Darren
        if (darrenAwesome == true) {
            g.drawImage(texture, bird.x, bird.y, null);
        }
    }

    //===== OUTPUTS =========
    public void press() {
        jump = true;
        start = true;
        gOver = false;
        redCount = 0;
    }

    public void release() {
        jump = false;
    }

    public void gameOver(Graphics g) {
        //Title
        Color titleColor = new Color(247, 247, 165);
        g.setFont(new Font("Comic Sans MS", 1, 75));
        g.setColor(titleColor);
        g.drawString("Score: " + totalScore, width / 3 + 20, height / 2 - 250);
        try {
            FileReader fr = new FileReader("highscore.txt");
            BufferedReader br = new BufferedReader(fr);
            strHighScore = br.readLine();
            br.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
        g.drawString("High Score: " + strHighScore, width / 4 + 50, height / 2 - 150);
        g.drawString("Click to Play Again", width / 5 + 20, height / 2 - 50);
        g.drawString("Press ESC for Menu", width / 5, height / 2 + 50);
        //Restart
        bird = new Rectangle(width / 5, height - 150 - 50, 20, 20);
        count = 3;
        pipeCount = 0;
        cycle = true;
        start = false;
        die = false;
        gOver = true;
        if (button == true) {
            bird = new Rectangle(width / 5, height / 2, 20, 20);
            jump = true;
            start = true;
            gOver = false;
            pipeLocation[0] = width;
            pipeLocation[1] = width + 400;
            pipeLocation[2] = width + 800;
            pipeLocation[3] = width + 1200;
            score = 0;
        }
    }
    //===== KEYBINDS =========

    @Override
    public void keyReleased(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
            release();
            button = false;
        }
        if (keyCode == KeyEvent.VK_ESCAPE) {
            menu open = new menu();
            open.setVisible(true);
            open.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            update_timer.stop();
            ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
        }
        if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
            release();
            button = false;
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
            if (button == false) {
                press();
            }
            button = true;
            gravity = 1; //Neg: Upside Down
            jumpHeight = 18;//Neg: Upside Down
            fallSpeed = 13;//Neg: Upside Down
            upsideDown = false;
        }
        if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
            if (button == false) {
                press();
            }
            gravity = -1; //Neg: Upside Down
            jumpHeight = -18;//Neg: Upside Down
            fallSpeed = -13;//Neg: Upside Down
            upsideDown = true;
            button = true;
            if (die == true) {
                gravity = 1; //Neg: Upside Down
                jumpHeight = 18;//Neg: Upside Down
                fallSpeed = 13;//Neg: Upside Down
            }
        }
        // Darren.
        switch (keyCode) {
            case KeyEvent.VK_D:
                codeEnter(0, true);
                break;
            case KeyEvent.VK_A:
                codeEnter(1, false);
                break;
            case KeyEvent.VK_R:
                if (codeStep == 3) {
                    codeEnter(3, false);
                }
                if (codeStep == 2) {
                    codeEnter(2, false);
                }
                break;
            case KeyEvent.VK_E:
                codeEnter(4, false);
                break;
            case KeyEvent.VK_N:
                codeEnter(5, false);
                if (codeStep == 6) {
                    darrenAwesome = true;
                }
                break;
        }
    }

    // Darren.
    public void codeEnter(int step, Boolean first) {
        if (codeStep == step) {
            codeStep += 1;
        } else {
            codeStep = 0;
        }

        if (first) {
            codeStep = 1;
        }
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }

    //===== MOUSEBINDS =========
    @Override
    public void mousePressed(MouseEvent event) {
        press();
        button = true;
        gravity = 1; //Neg: Upside Down
        jumpHeight = 18;//Neg: Upside Down
        fallSpeed = 13;//Neg: Upside Down
        upsideDown = false;

    }

    @Override
    public void mouseReleased(MouseEvent event) {
        release();
        button = false;
    }

    @Override
    public void mouseClicked(MouseEvent event) {
    }

    @Override
    public void mouseExited(MouseEvent event) {
    }

    @Override
    public void mouseEntered(MouseEvent event) {
    }

}
