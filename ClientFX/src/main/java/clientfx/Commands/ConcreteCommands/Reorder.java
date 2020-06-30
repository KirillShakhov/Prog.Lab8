package clientfx.Commands.ConcreteCommands;


import clientfx.Commands.Command;
import clientfx.Commands.CommandReceiver;
import clientfx.Commands.SerializedCommands.Message;

import java.io.Serializable;

/**
 * Конкретная команда получения информации о коллекции.
 */
public class Reorder extends Command implements Serializable {
    private static final long serialVersionUID = 33L;
    transient private CommandReceiver commandReceiver;

    public Reorder() {}

    public Reorder (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected Message execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде info.");
        }
        return commandReceiver.reorder();
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда reorder – отсортировать коллекцию в обратном порядке.");
    }
}
