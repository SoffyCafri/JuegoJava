import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Enemigo {
    private int x, y;
    private BufferedImage imagen;

    public Enemigo(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            imagen = ImageIO.read(new File("assets/enemigo.png"));
        } catch (IOException e) {
            System.out.println("Error cargando enemigo.png");
        }
    }

    public void dibujar(Graphics g) {
        g.drawImage(imagen, x, y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, imagen.getWidth(), imagen.getHeight());
    }
}
