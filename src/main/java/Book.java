import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

// A classe Book representa um livro e implementa a interface Serializable para permitir a serialização dos objetos
public class Book implements Serializable {
    private static final AtomicInteger idCounter = new AtomicInteger(); // Um contador atômico para gerar IDs únicos para os livros
    private int id; // ID único do livro
    private String title; // Título do livro
    private String author; // Autor do livro
    private int pages; // Número de páginas do livro
    private boolean lent; // Indica se o livro está emprestado

    // Construtor da classe Book para criar novas instâncias de livros
    public Book(String title, String author, int pages) {
        // Incrementa o contador de IDs e atribui o valor ao ID do livro
        this.id = idCounter.incrementAndGet();
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.lent = false; // Inicialmente, o livro não está emprestado
    }

    // Métodos getters para acessar os atributos privados da classe Book
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    public boolean isLent() {
        return lent;
    }

    // Método para marcar o livro como emprestado
    public void lend() {
        this.lent = true;
    }

    // Método para marcar o livro como devolvido
    public void returnBook() {
        this.lent = false;
    }

    // Método estático para definir o contador de IDs de livros
    public static void setIdCounter(int newCounter) {
        idCounter.set(newCounter);
    }

    // Método estático para obter o próximo ID disponível para um novo livro
    public static int getNextBookId() {
        return idCounter.get();
    }

    // Sobrescreve o método toString() para fornecer uma representação em string do livro
    @Override
    public String toString() {
        return "==============================\n" +
                "Livro:\n" +
                "ID: " + id + "\n" +
                "Nome: " + title + "\n" +
                "Autor: " + author + "\n" +
                "Número de Páginas: " + pages + "\n" +
                "Status: " + (lent ? "Emprestado" : "Disponível") + "\n" +
                "==============================";
    }
}
