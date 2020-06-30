package clientfx.Commands.ConcreteCommands;


import clientfx.Commands.Command;
import clientfx.Commands.CommandReceiver;
import clientfx.Commands.SerializedCommands.Message;

/**
 * Конкретная команда удаления объектов, меньше заданного.
 */
public class RemoveLower extends Command {
    private static final long serialVersionUID = 33L;
     transient private CommandReceiver commandReceiver;

    public RemoveLower(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public RemoveLower() {}

    @Override
    protected Message execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде remove_lower.");
        }
        return commandReceiver.removeLower();
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда remove_lower – удалить из коллекции все элементы, меньше, чем заданный.");
    }
}
