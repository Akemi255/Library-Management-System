import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class InterfazBiblioteca extends JFrame {
    private Biblioteca biblioteca;

    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JButton btnAgregar;
    private JTextField txtBusqueda;
    private JButton btnBuscar;
    private JList<Libro> lstResultados;
    private JButton btnPrestar;
    private JButton btnDevolver;

    public InterfazBiblioteca() {
        biblioteca = new Biblioteca();
        initComponents();
    }

    private void initComponents() {
        // Configurar la interfaz
        setTitle("Library Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 400));

        // Panel de ingreso de libros
        JPanel panelAgregar = new JPanel();
        panelAgregar.setLayout(new GridBagLayout()); // Cambiar el layout a GridBagLayout

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelAgregar.add(new JLabel("Título:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        txtTitulo = new JTextField(40); // Duplicar el ancho del campo de entrada de título
        panelAgregar.add(txtTitulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panelAgregar.add(new JLabel("Autor:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        txtAutor = new JTextField(40); // Duplicar el ancho del campo de entrada de autor
        panelAgregar.add(txtAutor, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        btnAgregar = new JButton("Agregar libro");
        panelAgregar.add(btnAgregar, gbc);

        // Panel de búsqueda de libros
        JPanel panelBuscar = new JPanel();
        panelBuscar.setLayout(new FlowLayout());
        txtBusqueda = new JTextField(30);
        panelBuscar.add(new JLabel("Buscar:"));
        panelBuscar.add(txtBusqueda);
        btnBuscar = new JButton("Buscar libros");
        panelBuscar.add(btnBuscar);

        // Panel de resultados y acciones
        JPanel panelResultados = new JPanel();
        panelResultados.setLayout(new BorderLayout());
        lstResultados = new JList<>();
        JScrollPane scrollPane = new JScrollPane(lstResultados);
        panelResultados.add(scrollPane, BorderLayout.CENTER);

        JPanel panelAcciones = new JPanel();
        panelAcciones.setLayout(new FlowLayout());
        btnPrestar = new JButton("Prestar");
        btnDevolver = new JButton("Devolver");
        panelAcciones.add(btnPrestar);
        panelAcciones.add(btnDevolver);

        panelResultados.add(panelAcciones, BorderLayout.SOUTH);

        // Agregar paneles a la ventana
        add(panelAgregar, BorderLayout.NORTH);
        add(panelBuscar, BorderLayout.CENTER);
        add(panelResultados, BorderLayout.SOUTH);

        // Eventos
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarLibro();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarLibros();
            }
        });

        btnPrestar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prestarLibro();
            }
        });

        btnDevolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                devolverLibro();
            }
        });

        

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    



    private void agregarLibro() {
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        if (!titulo.isEmpty() && !autor.isEmpty()) {
            Libro libro = new Libro(titulo, autor);
            biblioteca.agregarLibro(libro);
            JOptionPane.showMessageDialog(this, "Libro agregado correctamente.");
            limpiarCamposAgregar();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa el título y el autor del libro.");
        }
    }

    private void limpiarCamposAgregar() {
        txtTitulo.setText("");
        txtAutor.setText("");
    }

    private void buscarLibros() {
        String criterioBusqueda = txtBusqueda.getText();
        if (!criterioBusqueda.isEmpty()) {
            List<Libro> resultados = biblioteca.buscarLibros(criterioBusqueda);
            lstResultados.setListData(resultados.toArray(new Libro[0]));
        } else {
            JOptionPane.showMessageDialog(this, "Ingresa un criterio de búsqueda.");
        }
    }

    private void prestarLibro() {
        Libro libroSeleccionado = lstResultados.getSelectedValue();
        if (libroSeleccionado != null) {
            biblioteca.prestarLibro(libroSeleccionado);
            JOptionPane.showMessageDialog(this, "Libro prestado correctamente.");
            buscarLibros();
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un libro de los resultados.");
        }
    }

    private void devolverLibro() {
        Libro libroSeleccionado = lstResultados.getSelectedValue();
        if (libroSeleccionado != null) {
            biblioteca.devolverLibro(libroSeleccionado);
            JOptionPane.showMessageDialog(this, "Libro devuelto correctamente.");
            buscarLibros();
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un libro de los resultados.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InterfazBiblioteca();
            }
        });
    }
}
