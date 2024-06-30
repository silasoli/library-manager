import java.io.Serializable;

// A classe Borrower representa um usuário que toma emprestado livros da biblioteca
public class Borrower implements Serializable {
    private static final long serialVersionUID = 1L; // Número de versão da classe para serialização
    private static int nextId = 1; // Variável estática para gerar IDs sequenciais

    private int id; // ID do mutuário
    private String name; // Nome do mutuário
    private String contactInfo; // Informações de contato do mutuário

    // Construtor da classe Borrower para criar um novo mutuário
    public Borrower(String name, String contactInfo) {
        this.id = nextId++; // Atribui um ID único ao mutuário e incrementa o próximo ID
        this.name = name; // Define o nome do mutuário
        this.contactInfo = contactInfo; // Define as informações de contato do mutuário
    }

    // Métodos getters para obter os atributos do mutuário
    public int getId() {
        return id; // Retorna o ID do mutuário
    }

    public String getName() {
        return name; // Retorna o nome do mutuário
    }

    // Método setter para atualizar o nome do mutuário
    public void setName(String name) {
        this.name = name; // Define o novo nome do mutuário
    }

    public String getContactInfo() {
        return contactInfo; // Retorna as informações de contato do mutuário
    }

    // Método setter para atualizar as informações de contato do mutuário
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo; // Define as novas informações de contato do mutuário
    }

    // Sobrescrita do método toString para exibir informações do mutuário de forma legível
    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + name + ", Informações de contato: " + contactInfo;
    }
}
