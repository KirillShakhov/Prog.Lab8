package lab.Commands.ConcreteCommands;


import lab.Commands.Command;
import lab.Commands.CommandReceiver;
import lab.Commands.SerializedCommands.Message;

/**
 * Конкретная команда добавления в коллекцию.
 */
public class Auth extends Command {
    private static final long serialVersionUID = 33L;

    @Override
    public String execute(Object argObject) {
        CommandReceiver commandReceiver = new CommandReceiver();
        return commandReceiver.auth(((Message) argObject).getArgs());
    }
}
