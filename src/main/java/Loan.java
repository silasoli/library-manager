import java.io.Serializable;

// A classe Loan representa um empréstimo de um livro para um usuário
public class Loan implements Serializable {
    private String userName; // Nome do usuário que fez o empréstimo
    private String userContact; // Informações de contato do usuário
    private Book book; // Livro emprestado

    // Construtor da classe Loan para criar um novo empréstimo
    public Loan(String userName, String userContact, Book book) {
        this.userName = userName; // Define o nome do usuário
        this.userContact = userContact; // Define as informações de contato do usuário
        this.book = book; // Define o livro emprestado
    }

    // Métodos getters para obter os atributos do empréstimo
    public String getUserName() {
        return userName; // Retorna o nome do usuário
    }

    public String getUserContact() {
        return userContact; // Retorna as informações de contato do usuário
    }

    public Book getBook() {
        return book; // Retorna o livro emprestado
    }

    // Sobrescrita do método toString para exibir informações do empréstimo de forma legível
    @Override
    public String toString() {
        return "Loan{" +
                "Nome='" + userName + '\'' +
                ", Contato='" + userContact + '\'' +
                ", livro=" + book +
                '}';
    }
}
