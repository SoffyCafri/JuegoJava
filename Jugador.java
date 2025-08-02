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
    private int ancho = 64; // ancho deseado
    private int alto = 64;  // alto deseado

    public Jugador(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            BufferedImage original = ImageIO.read(new File("assets/sirena.png"));
            imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = imagen.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(original, 0, 0, ancho, alto, null);
            g2d.dispose();
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
        return new Rectangle(x, y, ancho, alto); // usa las dimensiones escaladas
    }

    public void resetearPosicion() {
        x = 50;
        y = 50;
    }
}
