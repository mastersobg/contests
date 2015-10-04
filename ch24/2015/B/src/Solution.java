import javax.swing.*;
import javax.swing.tree.ExpandVetoException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author Ivan Gorbachev
 */
public class Solution {

    BufferedReader reader;
    PrintWriter writer;
    PrintWriter consoleWriter;
    BufferedReader consoleReader;
    final boolean NETWORK_MODE = true;

    final int width = 640;
    final int height = 480;
    final Object lock = new Object();

    int currentPos = 200;
    int currentSpeed = 0;
    double currentAngle = getAngle(width >> 1, height >> 1, 0, height);
    JFrame frame;

    public static void main(String []args) throws Exception {
        new Solution().go();
    }

    double getDiff(double alpha, double beta) {
      //  if (beta < )
        double diff = alpha - beta;
        if (diff > 0.0 || diff < -Math.PI) {
            if (diff < -Math.PI) {
                diff = Math.PI * 2 + diff;
            }
        }
        //diff = Math.min(diff, Math.PI * 2.0 + d)
        return diff;
    }

    private void gui() {
        frame = new JFrame();
//        JFrame f2 = new JFrame();
//        f2.setSize(new Dimension(100, 100));
        frame.setSize(new Dimension(width, height));
//        TextField textArea = new TextField("0");
//        textArea.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//                TextField tf = (TextField) e.getSource();
//                String text = tf.getText();
//                currentSpeed = parseSpeed(text);
//                write(currentSpeed, currentPos);
//            }
//        });
//        f2.add(textArea);
//        f2.show();
        frame.show();
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                double angle = getAngle(width >> 1, height >> 1, e.getX(), e.getY());
                synchronized (lock) {
                    consoleWriter.println("angle = " + degrees(angle));
                    consoleWriter.flush();
                }

                double zeroAngle = getAngle(width >> 1, height >> 1, 0, height);
                double diff = -getDiff(angle, zeroAngle);
                int prevPos = currentPos;
                currentPos = (int) (200.0 + diff / Math.PI * 100.0);

//                int cw = Math.abs(currentPos - prevPos);
//                int ccw = Math.abs(Math.min(currentPos, prevPos) - 100 + 300 - Math.max(prevPos, currentPos));
//                if (ccw < cw) {
//                    if (currentPos < 200) {
//                        currentPos += 200;
//                    } else {
//                        currentPos -= 200;
//                    }
//                }
//                consoleWriter.println(cw + " " + ccw);
//                consoleWriter.flush();

                write(currentSpeed, currentPos);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case 38 : {
                        currentSpeed = Math.min(100, currentSpeed + 5);
                        // up
                        break;
                    }
                    case 40: {
                        //down
                        currentSpeed = Math.max(0, currentSpeed - 5);
                        break;
                    }

                }
                draw();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                write(currentSpeed, currentPos);
            }
        });
        draw();
    }

    private void draw() {
        frame.getGraphics().clearRect(0, 0, width, height);
        char []arr = Integer.valueOf(currentSpeed).toString().toCharArray();
        frame.getGraphics().drawChars(arr, 0, arr.length, 250, 250);
    }

    int parseSpeed(String s) {
        try {
            return Integer.valueOf(s);
        } catch (Exception e) {
            return 0;
        }
    }
    double getAngle(int cx, int cy, int x, int y) {
        double dx = x - cx;
        double dy = cy - y;
        double alpha = Math.atan2(dy, dx);
        if (alpha < 0.0) {
            alpha += Math.PI * 2;
        }
        return alpha;
    }

    double degrees(double a) {
        return a / Math.PI * 180.0;
    }

    private void go() throws Exception {
        gui();
        consoleReader = new BufferedReader(new InputStreamReader(System.in));
        synchronized (lock) {
            consoleWriter = new PrintWriter(System.out);
        }
        if (NETWORK_MODE) {
            boolean establed = false;
            while (!establed) {
                try {
                    Socket socket = new Socket();
                    SocketAddress sa = new InetSocketAddress("10.0.107.1", 10001);
                    socket.connect(sa, 2000);
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    synchronized (lock) {
                        writer = new PrintWriter(socket.getOutputStream());
                    }
                    establed = true;
                } catch (Exception e) {
                    synchronized (lock) {
                        consoleWriter.println("Failed to establish connection");
                        consoleWriter.flush();
                    }
                }
            }
        }
        while (true) {
            String s = read();
            if ("Start".equals(s)) {
                System.out.println("GOOO!!!!!!!!!!!!!!!!!!!!!!!!");
                break;
            }
        }
    }

    String read() throws Exception {
        if (NETWORK_MODE)
            return reader.readLine();
        else return "Start";
    }

    void write(int speed, int position) {
        synchronized (lock) {
            consoleWriter.println("speed = " + format(speed) + " position=" + format(position));
            consoleWriter.println("---------------------------------------------------------------");
            consoleWriter.flush();
            if (NETWORK_MODE) {
                if (writer == null)
                    consoleWriter.println("network writer not initialized");
                else {
                    try {
                        writer.println("!" + format(speed) + " " + format(position));
                        writer.flush();
                        consoleWriter.println("Command successfully sent");
                    } catch (Exception e) {
                        consoleWriter.println("Failed to send command " + e.toString());
                    }
                }
            }
            consoleWriter.flush();
        }
    }

    String format(int n) {
        if (n < 10)
            return "00" + n;
        if (n < 100)
            return "0" + n;
        return n + "";
    }


}
