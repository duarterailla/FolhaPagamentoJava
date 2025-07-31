package exceptions;

public class DependenteException extends RuntimeException {
        public DependenteException(String message) {
            super(message); // passar a mensagem ao construtor da classe pai
        }
    }

