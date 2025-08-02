import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private Jugador jugador;
    private ArrayList<Enemigo> enemigos;
    private Rectangle meta;
    private Random random = new Random();

    // Nombres de las imágenes disponibles
    private final String[] imagenesEnemigos = {"cocodrilo.png", "garza.png", "capibara.png"};

    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

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

        jugador.dibujar(g);

        for (Enemigo e : enemigos) {
            e.dibujar(g);
        }

        // Dibujar la meta
        g.setColor(Color.GREEN);
        g.fillRect(meta.x, meta.y, meta.width, meta.height);
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

            // Mueve la meta a nueva posición aleatoria
            meta.setLocation(random.nextInt(760), random.nextInt(560));

            // Agrega más enemigos aleatorios
            for (int i = 0; i < 2; i++) {
                int x = random.nextInt(760);
                int y = random.nextInt(560);
                enemigos.add(crearEnemigoAleatorio(x, y));
            }
        }

        repaint();
    }

    // Método para crear un enemigo con imagen aleatoria
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
