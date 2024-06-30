import java.io.Serializable;
import java.util.*;

// A classe LibraryData representa os dados da biblioteca para serialização
public class LibraryData implements Serializable {
    private List<Book> library; // Lista de livros na biblioteca
    private List<Loan> loans; // Lista de empréstimos
    private Map<Book, Queue<String>> waitlistMap; // Mapa de lista de espera para livros
    private int nextBookId; // Próximo ID disponível para livros

    // Construtor da classe LibraryData para criar uma instância com os dados da biblioteca
    public LibraryData(List<Book> library, List<Loan> loans, Map<Book, Queue<String>> waitlistMap, int nextBookId) {
        this.library = library; // Inicializa a lista de livros
        this.loans = loans; // Inicializa a lista de empréstimos
        this.waitlistMap = waitlistMap; // Inicializa o mapa de lista de espera
        this.nextBookId = nextBookId; // Define o próximo ID disponível para livros
    }

    // Métodos getters para acessar os dados da biblioteca
    public List<Book> getLibrary() {
        return library; // Retorna a lista de livros
    }

    public List<Loan> getLoans() {
        return loans; // Retorna a lista de empréstimos
    }

    public Map<Book, Queue<String>> getWaitlistMap() {
        return waitlistMap; // Retorna o mapa de lista de espera
    }

    public int getNextBookId() {
        return nextBookId; // Retorna o próximo ID disponível para livros
    }
}
