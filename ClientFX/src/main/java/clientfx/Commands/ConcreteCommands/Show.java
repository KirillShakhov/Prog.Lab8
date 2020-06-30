package clientfx.Commands.ConcreteCommands;


import clientfx.Commands.Command;
import clientfx.Commands.CommandReceiver;
import clientfx.Commands.SerializedCommands.Message;

/**
 * Конкретная команда показа содержания коллекции.
 */
public class Show extends Command {
    private static final long serialVersionUID = 33L;
    transient private CommandReceiver commandReceiver;

    public Show (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public Show() {}

    @Override
    protected Message execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде show.");
        }
        return commandReceiver.show();
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда show – вывести все элементы коллекции в строковом представлении.");
    }
}
