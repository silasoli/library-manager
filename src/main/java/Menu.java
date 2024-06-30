import java.util.Scanner;

// A classe Menu representa o menu de interação com o sistema da biblioteca
public class Menu {
    private LibraryManager manager; // Referência para o gerenciador da biblioteca
    private Scanner scanner; // Scanner para entrada de dados do usuário

    // Construtor da classe Menu, recebe o gerenciador da biblioteca como parâmetro
    public Menu(LibraryManager manager) {
        this.manager = manager; // Inicializa o gerenciador da biblioteca
        this.scanner = new Scanner(System.in); // Inicializa o Scanner para entrada de dados
    }

    // Método para exibir e processar o menu principal
    public void displayMenu() {
        while (true) { // Loop infinito para exibir o menu continuamente
            showMainMenu(); // Exibe o menu principal
            int choice = getChoice(1, 8); // Obtém a escolha do usuário
            processChoice(choice); // Processa a escolha do usuário
        }
    }

    // Método para exibir o menu principal
    private void showMainMenu() {
        System.out.println("\nSistema de Gerenciamento de Biblioteca");
        System.out.println("1. Adicionar um novo livro");
        System.out.println("2. Emprestar um livro");
        System.out.println("3. Devolver um livro");
        System.out.println("4. Ver todos os livros");
        System.out.println("5. Ver lista de espera");
        System.out.println("6. Adicionar à lista de espera");
        System.out.println("7. Remover da lista de espera");
        System.out.println("8. Sair");
        System.out.print("Escolha uma opção: ");
    }

    // Método para obter a escolha do usuário dentro de um intervalo específico
    private int getChoice(int min, int max) {
        int choice;
        do {
            while (!scanner.hasNextInt()) { // Verifica se a entrada não é um número
                System.out.print("Entrada inválida. Por favor, digite um número: "); // Mensagem de erro
                scanner.next(); // Limpa a entrada inválida
            }
            choice = scanner.nextInt(); // Lê a escolha do usuário
            scanner.nextLine(); // Consume a nova linha deixada pelo nextInt()
            if (choice < min || choice > max) { // Verifica se a escolha está dentro do intervalo válido
                System.out.print("Escolha inválida. Por favor, digite um número entre " + min + " e " + max + ": "); // Mensagem de erro
            }
        } while (choice < min || choice > max); // Loop até obter uma escolha válida
        return choice; // Retorna a escolha válida
    }

    // Método para processar a escolha do usuário e executar a ação correspondente
    private void processChoice(int choice) {
        switch (choice) {
            case 1:
                addBookMenu(); // Chama o submenu para adicionar um novo livro
                break;
            case 2:
                lendBookMenu(); // Chama o submenu para emprestar um livro
                break;
            case 3:
                returnBookMenu(); // Chama o submenu para devolver um livro
                break;
            case 4:
                manager.showBooks(); // Exibe todos os livros no sistema
                break;
            case 5:
                showWaitlistMenu(); // Chama o submenu para exibir a lista de espera de um livro
                break;
            case 6:
                addToWaitlistMenu(); // Chama o submenu para adicionar à lista de espera de um livro
                break;
            case 7:
                removeFromWaitlistMenu(); // Chama o submenu para remover da lista de espera de um livro
                break;
            case 8:
                System.out.println("Encerrando..."); // Mensagem de saída
                manager.saveToFile(); // Salva os dados antes de sair
                System.exit(0); // Encerra o programa
        }
    }

    // Métodos similares para os outros submenus
    private void addBookMenu() {
        System.out.print("Título: "); // Solicita o título do livro
        String title = scanner.nextLine(); // Lê o título do livro
        System.out.print("Autor: "); // Solicita o autor do livro
        String author = scanner.nextLine(); // Lê o autor do livro
        System.out.print("Número de páginas: "); // Solicita o número de páginas do livro
        int pages = getIntInput(); // Lê o número de páginas do livro
        manager.addBook(title, author, pages); // Chama o método do gerenciador para adicionar o livro
    }

    // Métodos similares para os outros submenus
    private void lendBookMenu() {
        if (manager.showAvailableBooks()) {
            System.out.print("Informe o ID do livro para emprestar: ");
            int lendId = getIntInput();
            System.out.print("Nome do usuário: ");
            String userName = scanner.nextLine();
            System.out.print("Contato do usuário (telefone ou e-mail): ");
            String userContact = scanner.nextLine();
            manager.lendBook(lendId, userName, userContact);
        }
    }

    private void returnBookMenu() {
        if (manager.showLentBooks()) {
            System.out.print("Informe o ID do livro para devolver: ");
            int returnId = getIntInput();
            manager.returnBook(returnId);
        }
    }

    private void showWaitlistMenu() {
        System.out.print("Informe o ID do livro: ");
        int bookId = getIntInput();
        manager.showWaitlist(bookId);
    }

    private void addToWaitlistMenu() {
        System.out.print("Informe o ID do livro: ");
        int bookId = getIntInput();
        System.out.print("Nome do usuário: ");
        String userName = scanner.nextLine();
        System.out.print("Contato do usuário (telefone ou e-mail): ");
        String userContact = scanner.nextLine();
        manager.addToWaitlist(bookId, userName, userContact);
    }

    private void removeFromWaitlistMenu() {
        System.out.print("Informe o ID do livro: ");
        int bookId = getIntInput();
        System.out.print("Nome do usuário: ");
        String userName = scanner.nextLine();
        System.out.print("Contato do usuário (telefone ou e-mail): ");
        String userContact = scanner.nextLine();
        manager.removeFromWaitlist(bookId, userName, userContact);
    }

    private int getIntInput() {
        while (!scanner.hasNextInt()) { // Verifica se a entrada não é um número
            System.out.print("Entrada inválida. Por favor, digite um número: "); // Mensagem de erro
            scanner.next(); // Limpa a entrada inválida
        }
        int input = scanner.nextInt(); // Lê a entrada como um número inteiro
        scanner.nextLine(); // Consume a nova linha deixada pelo nextInt()
        return input; // Retorna o número inteiro válido
    }
}
