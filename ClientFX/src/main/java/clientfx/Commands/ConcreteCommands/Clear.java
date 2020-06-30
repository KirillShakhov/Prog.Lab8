package clientfx.Commands.ConcreteCommands;


import clientfx.Commands.Command;
import clientfx.Commands.CommandReceiver;
import clientfx.Commands.SerializedCommands.Message;

/**
 * Конкретная команда очистки коллекции.
 */
public class Clear extends Command {
    private static final long serialVersionUID = 33L;
    transient private CommandReceiver commandReceiver;

    public Clear (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public Clear() {}

    @Override
    protected Message execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде clear.");
        }
        return commandReceiver.clear();
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда clear – очистить коллекцию.");
    }
}
