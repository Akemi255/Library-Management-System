import java.util.ArrayList;
import java.util.List;

    class Biblioteca {
    private List<Libro> libros;

    public Biblioteca() {
        libros = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public List<Libro> buscarLibros(String criterioBusqueda) {
        List<Libro> resultados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getTitulo().contains(criterioBusqueda) || libro.getAutor().contains(criterioBusqueda)) {
                resultados.add(libro);
            }
        }
        return resultados;
    }

    public void prestarLibro(Libro libro) {
        libro.prestar();
    }

    public void devolverLibro(Libro libro) {
        libro.devolver();
    }

    
}
