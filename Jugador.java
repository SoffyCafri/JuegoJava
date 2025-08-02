import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Jugador {
    private int x, y, velocidad = 4;
    private int dx, dy;
    private BufferedImage imagen;

    public Jugador(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            imagen = ImageIO.read(new File("assets/jugador.png"));
        } catch (IOException e) {
            System.out.println("Error cargando jugador.png");
        }
    }

    public void mover() {
        x += dx;
        y += dy;
    }

    public void dibujar(Graphics g) {
        g.drawImage(imagen, x, y, null);
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> dy = -velocidad;
            case KeyEvent.VK_S -> dy = velocidad;
            case KeyEvent.VK_A -> dx = -velocidad;
            case KeyEvent.VK_D -> dx = velocidad;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_S -> dy = 0;
            case KeyEvent.VK_A, KeyEvent.VK_D -> dx = 0;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, imagen.getWidth(), imagen.getHeight());
    }

    public void resetearPosicion() {
        x = 50;
        y = 50;
    }
}
