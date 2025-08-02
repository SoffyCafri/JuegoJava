import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private Jugador jugador;
    private ArrayList<Enemigo> enemigos;
    private Rectangle meta;
    private Random random = new Random();
    private BufferedImage fondo;
    private BufferedImage imagenMeta;

    // Nombres de las imágenes disponibles
    private final String[] imagenesEnemigos = {"cocodrilo.png", "garza.png", "capibara.png"};

    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        setFocusable(true);
        addKeyListener(this);

        // Cargar la imagen de fondo
        try {
            fondo = ImageIO.read(new File("assets/fondo.png"));
        } catch (IOException e) {
            System.out.println("No se pudo cargar fondo.png");
            fondo = null;
        }

        // Cargar la imagen de la meta (caracola)
        try {
            imagenMeta = ImageIO.read(new File("assets/caracola.png"));
        } catch (IOException e) {
            System.out.println("No se pudo cargar caracola.png");
            imagenMeta = null;
        }

        jugador = new Jugador(50, 50);
        enemigos = new ArrayList<>();

        // Agrega enemigos iniciales con imágenes distintas
        enemigos.add(crearEnemigoAleatorio(300, 100));
        enemigos.add(crearEnemigoAleatorio(500, 300));

        // Meta inicial
        meta = new Rectangle(700, 500, 40, 40);

        timer = new Timer(16, this);
    }

    public void iniciarJuego() {
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar el fondo
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        // Dibujar la meta como imagen
        if (imagenMeta != null) {
            g.drawImage(imagenMeta, meta.x, meta.y, meta.width, meta.height, null);
        }

        // Opcional: dibujar contorno blanco alrededor de la meta
        g.setColor(Color.WHITE);
        g.drawRect(meta.x, meta.y, meta.width, meta.height);

        // Dibujar jugador y enemigos
        jugador.dibujar(g);

        for (Enemigo e : enemigos) {
            e.dibujar(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jugador.mover();

        for (Enemigo enemigo : enemigos) {
            if (jugador.getBounds().intersects(enemigo.getBounds())) {
                jugador.resetearPosicion();
            }
        }

        if (jugador.getBounds().intersects(meta)) {
            jugador.resetearPosicion();

            // Nueva posición de la meta
            meta.setLocation(random.nextInt(760), random.nextInt(560));

            // Agregar enemigos nuevos
            for (int i = 0; i < 2; i++) {
                int x = random.nextInt(760);
                int y = random.nextInt(560);
                enemigos.add(crearEnemigoAleatorio(x, y));
            }
        }

        repaint();
    }

    private Enemigo crearEnemigoAleatorio(int x, int y) {
        String nombreImagen = imagenesEnemigos[random.nextInt(imagenesEnemigos.length)];
        return new Enemigo(x, y, nombreImagen);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        jugador.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        jugador.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
