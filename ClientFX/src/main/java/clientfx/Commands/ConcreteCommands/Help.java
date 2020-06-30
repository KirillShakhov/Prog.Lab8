package clientfx.Commands.ConcreteCommands;


import clientfx.Commands.Command;
import clientfx.Commands.CommandReceiver;
import clientfx.Commands.SerializedCommands.Message;

/**
 * Конкретная команда помощи.
 */
public class Help extends Command {
    private CommandReceiver commandReceiver;

    public Help (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected Message execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде help.");
        }
        commandReceiver.help();
        return null;
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда help – получить справку по доступным командам.");
    }
}
