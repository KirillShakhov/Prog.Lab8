package lab.Commands.ConcreteCommands;


import lab.Commands.Command;
import lab.Commands.CommandReceiver;

/**
 * Конкретная команда получения информации о коллекции.
 */
public class Info extends Command {
    private static final long serialVersionUID = 33L;

    @Override
    public String execute(Object argObject) {
        CommandReceiver commandReceiver = new CommandReceiver();
        return commandReceiver.info();
    }
}
