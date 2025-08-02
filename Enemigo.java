import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Enemigo {
    private int x, y;
    private BufferedImage imagen;
    private int ancho = 64;
    private int alto = 64;

    public Enemigo(int x, int y, String nombreImagen) {
        this.x = x;
        this.y = y;
        try {
            BufferedImage original = ImageIO.read(new File("assets/" + nombreImagen));
            imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = imagen.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(original, 0, 0, ancho, alto, null);
            g2d.dispose();
        } catch (IOException e) {
            System.out.println("Error cargando imagen: " + nombreImagen);
        }
    }

    public void dibujar(Graphics g) {
        g.drawImage(imagen, x, y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }
}
