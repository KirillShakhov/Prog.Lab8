package lab.Commands.ConcreteCommands;


import lab.Commands.Command;
import lab.Commands.CommandReceiver;

public class Reorder extends Command {
    private static final long serialVersionUID = 33L;

    @Override
    public String execute(Object argObject) {
        CommandReceiver commandReceiver = new CommandReceiver();
        return commandReceiver.reorder();
    }
}
