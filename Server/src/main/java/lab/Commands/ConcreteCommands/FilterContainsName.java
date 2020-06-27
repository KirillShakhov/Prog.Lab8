package lab.Commands.ConcreteCommands;

import lab.Commands.Command;
import lab.Commands.CommandReceiver;
import lab.Commands.SerializedCommands.Message;

public class FilterContainsName extends Command {
    private static final long serialVersionUID = 33L;

    @Override
    public String execute(Object argObject) {
        String arg = ((Message)argObject).getArgs();
        if (arg.split(" ").length == 1) {
            CommandReceiver commandReceiver = new CommandReceiver();
            return commandReceiver.filterContainsName(arg);
        }
        else { return "Некорректное количество аргументов. Для справки напишите help."; }
    }
}
