package clientfx.Commands.ConcreteCommands;


import clientfx.Commands.Command;
import clientfx.Commands.CommandReceiver;
import clientfx.Commands.SerializedCommands.Message;

/**
 * Конкретная команда удаления по ID.
 */
public class RemoveByID extends Command {
    private static final long serialVersionUID = 33L;
    transient private CommandReceiver commandReceiver;

    public RemoveByID (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public RemoveByID() {}

    @Override
    protected Message execute(String[] args) {
        if (args.length > 1) {
            return commandReceiver.removeById(args[1]);
        }
        else { System.out.println("Некорректное количество аргументов. Для справки напишите help."); }
        return null;
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда remove_by_id. Синтаксис: remove_by_id id – удалить элемент из коллекции по его id.");
    }
}
