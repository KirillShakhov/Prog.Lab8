package clientfx.Commands.ConcreteCommands;


import clientfx.Commands.Command;
import clientfx.Commands.CommandReceiver;
import clientfx.Commands.SerializedCommands.Message;

/**
 * Конкретная команда обновления объекта.
 */
public class Update extends Command {
    private static final long serialVersionUID = 33L;
    transient private CommandReceiver commandReceiver;

    public Update (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public Update() {}

    @Override
    protected Message execute(String[] args) {
        if (args.length == 2) { return commandReceiver.update(args[1]); }
        else { System.out.println("Некорректное количество аргументов. Для справки напишите help."); }
        return null;
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда update. Синтаксис: update id – обновить значение элемента коллекции, id которого равен заданному.");
    }
}
