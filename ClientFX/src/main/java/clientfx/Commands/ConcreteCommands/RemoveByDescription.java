package clientfx.Commands.ConcreteCommands;

import clientfx.Commands.Command;
import clientfx.Commands.CommandReceiver;
import clientfx.Commands.SerializedCommands.Message;

/**
 * Конкретная команда удаления по ID.
 */
public class RemoveByDescription extends Command {
    private static final long serialVersionUID = 33L;
    transient private CommandReceiver commandReceiver;

    public RemoveByDescription(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public RemoveByDescription() {
    }

    @Override
    protected Message execute(String[] args) {
        if (args.length > 1) {
            return commandReceiver.removeByDescription(args[1]);
        } else {
            System.out.println("Некорректное количество аргументов. Для справки напишите help.");
        }
        return null;
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда remove_by_description. Синтаксис: remove_by_description описание – удалить элемент из коллекции по его описанию.");
    }
}
