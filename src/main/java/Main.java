public class Main {
    public static void main(String[] args) {
        // Cria uma nova instância da classe LibraryManager
        LibraryManager manager = new LibraryManager();

        // Cria uma nova instância da classe Menu, passando a instância do LibraryManager como argumento
        Menu menu = new Menu(manager);

        // Chama o método displayMenu() na instância de Menu para exibir o menu e interagir com o usuário
        menu.displayMenu();
    }
}
