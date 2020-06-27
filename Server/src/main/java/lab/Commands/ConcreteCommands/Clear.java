package lab.Commands.ConcreteCommands;


import lab.Commands.Command;
import lab.Commands.CommandReceiver;

/**
 * Конкретная команда очистки коллекции.
 */
public class Clear extends Command {
    private static final long serialVersionUID = 33L;

    @Override
    public String execute(Object argObject) {
        CommandReceiver commandReceiver = new CommandReceiver();
        return commandReceiver.clear();
    }
}
