package lab.Commands.ConcreteCommands;


import lab.Commands.Command;
import lab.Commands.CommandReceiver;

/**
 * Конкретная команда показа содержания коллекции.
 */
public class Show extends Command {
    private static final long serialVersionUID = 33L;

    @Override
    public String execute(Object argObject) {
        CommandReceiver commandReceiver = new CommandReceiver();
        return commandReceiver.show();
    }
}
