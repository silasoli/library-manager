import java.io.*;
import java.util.*;

// A classe LibraryManager é responsável por gerenciar a biblioteca, incluindo livros, empréstimos e listas de espera
public class LibraryManager {
    private List<Book> library; // Lista de livros na biblioteca
    private List<Loan> loans; // Lista de empréstimos realizados
    private Map<Book, Queue<String>> waitlistMap; // Mapeia livros para listas de espera de empréstimos
    private final String DATA_FILE = "library_data.dat"; // Nome do arquivo de dados para salvar/carregar

    // Construtor da classe LibraryManager, inicializa as listas e carrega os dados
    // do arquivo, se disponível
    public LibraryManager() {
        library = new ArrayList<Book>();
        loans = new ArrayList<Loan>();
        waitlistMap = new HashMap<Book, Queue<String>>();
        loadFromFile();
    }

    // Método para adicionar um novo livro à biblioteca
    public void addBook(String title, String author, int pages) {
        Book newBook = new Book(title, author, pages); // Cria um novo livro
        library.add(newBook); // Adiciona o livro à lista de livros na biblioteca
        waitlistMap.put(newBook, new LinkedList<String>()); // Inicializa a lista de espera para o livro
        saveToFile(); // Salva os dados no arquivo
    }

    // Método para realizar o empréstimo de um livro
    public void lendBook(int id, String userName, String userContact) {
        Book book = findBookById(id); // Encontra o livro pelo ID
        if (book == null) {
            System.out.println("Livro com ID " + id + " não encontrado."); // Mensagem se o livro não for encontrado
            return;
        }
        if (book.isLent()) {
            addToWaitlist(id, userName, userContact); // Adiciona à lista de espera se o livro já estiver emprestado
            return;
        }
        book.lend(); // Marca o livro como emprestado
        Loan loan = new Loan(userName, userContact, book); // Cria um objeto Loan para registrar o empréstimo
        loans.add(loan); // Adiciona o empréstimo à lista de empréstimos
        System.out.println(userName + " emprestado " + book.getTitle()); // Mensagem de sucesso
        saveToFile(); // Salva os dados no arquivo
    }

    // Método para adicionar à lista de espera de um livro
    public void addToWaitlist(int bookId, String userName, String userContact) {
        Book book = findBookById(bookId); // Encontra o livro pelo ID
        if (book != null) {
            Queue<String> waitlist = waitlistMap.get(book); // Obtém a lista de espera para o livro
            waitlist.add(userName + " (" + userContact + ")"); // Adiciona o usuário à lista de espera
            System.out.println(userName + " adicionado a lista de espera " + book.getTitle()); // Mensagem de sucesso
        } else {
            System.out.println("Livro com ID " + bookId + " não encontrado."); // Mensagem se o livro não for encontrado
        }
    }

    // Método para devolver um livro emprestado
    public void returnBook(int id) {
        Book book = findBookById(id); // Encontra o livro pelo ID
        if (book == null) {
            System.out.println("Livro com ID " + id + " não encontrado."); // Mensagem se o livro não for encontrado
            return;
        }
        if (!book.isLent()) {
            System.out.println(book.getTitle() + " livr não está emprestado"); // Mensagem se o livro não estiver
                                                                               // emprestado
            return;
        }
        book.returnBook(); // Marca o livro como devolvido
        loans.removeIf(loan -> loan.getBook().equals(book)); // Remove o empréstimo da lista de empréstimos
        Queue<String> waitlist = waitlistMap.get(book); // Obtém a lista de espera para o livro
        if (!waitlist.isEmpty()) {
            // Se houver usuários na lista de espera, empresta o livro para o próximo da
            // lista
            String nextBorrower = waitlist.poll();
            book.lend();
            String[] borrowerDetails = nextBorrower.split(" \\(");
            Loan loan = new Loan(borrowerDetails[0], borrowerDetails[1].replace(")", ""), book);
            loans.add(loan);
            System.out.println(nextBorrower + " emprestado " + book.getTitle() + " da lista de espera"); // Mensagem de
                                                                                                         // sucesso
        } else {
            System.out.println(book.getTitle() + " está disponível"); // Mensagem se o livro estiver disponível
        }
        saveToFile(); // Salva os dados no arquivo
    }

    // Métodos para mostrar diferentes informações sobre os livros e empréstimos na
    // biblioteca
    public void showBooks() {
        if (library.isEmpty()) {
            System.out.println("Nenhum livro cadastrado no sistema.");
        } else {
            for (Book book : library) {
                System.out.println(book);
            }
        }
    }

    public boolean showAvailableBooks() {
        boolean foundAvailableBooks = false;
        for (Book book : library) {
            if (!book.isLent()) {
                System.out.println(book);
                foundAvailableBooks = true;
            }
        }
        if (!foundAvailableBooks) {
            System.out.println("Não há livros disponíveis para empréstimo.");
        }
        return foundAvailableBooks;
    }

    public boolean showLentBooks() {
        boolean foundLentBooks = false;
        for (Book book : library) {
            if (book.isLent()) {
                System.out.println(book);
                foundLentBooks = true;
            }
        }
        if (!foundLentBooks) {
            System.out.println("Nenhum livro emprestado atualmente.");
        }
        return foundLentBooks;
    }

    public void showWaitlist(int id) {
        Book book = findBookById(id);
        if (book == null) {
            System.out.println("Livro com ID " + id + " não encontrado.");
            return;
        }
        Queue<String> waitlist = waitlistMap.get(book);
        if (waitlist.isEmpty()) {
            System.out.println("Nenhum tomador na lista de espera para " + book.getTitle());
            return;
        }
        System.out.println("Lista de espera para " + book.getTitle() + ":");
        for (String borrower : waitlist) {
            System.out.println(borrower);
        }
    }

    public void removeFromWaitlist(int id, String userName, String userContact) {
        Book book = findBookById(id);
        if (book == null) {
            System.out.println("Livro com ID " + id + " não encontrado.");
            return;
        }
        Queue<String> waitlist = waitlistMap.get(book);
        String borrowerToRemove = userName + " (" + userContact + ")";
        if (waitlist.remove(borrowerToRemove)) {
            System.out.println(userName + " removido da lista de espera " + book.getTitle());
        } else {
            System.out.println(userName + " não está na lista de espera " + book.getTitle());
        }
    }

    // Método privado para encontrar um livro na biblioteca pelo ID
    private Book findBookById(int id) {
        return library.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
    }

    // Método para carregar os dados do arquivo de dados
    public void loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            LibraryData data = (LibraryData) in.readObject(); // Lê os dados do arquivo
            library = data.getLibrary(); // Atualiza a lista de livros
            loans = data.getLoans(); // Atualiza a lista de empréstimos
            waitlistMap = data.getWaitlistMap(); // Atualiza o mapa de lista de espera
            Book.setIdCounter(data.getNextBookId()); // Atualiza o contador de IDs de livros
        } catch (IOException e) {
            System.out.println("Failed to load data: " + e.getMessage()); // Mensagem de erro ao carregar dados
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load data: " + e.getMessage()); // Mensagem de erro ao carregar dados
        }
    }

    // Método para salvar os dados no arquivo de dados
    public void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            LibraryData data = new LibraryData(library, loans, waitlistMap, Book.getNextBookId()); // Cria um objeto
                                                                                                   // LibraryData com os
                                                                                                   // dados a serem
                                                                                                   // salvos
            out.writeObject(data); // Escreve os dados no arquivo
        } catch (IOException e) {
            System.out.println("Failed to save data: " + e.getMessage()); // Mensagem de erro ao salvar dados
        }
    }
}
