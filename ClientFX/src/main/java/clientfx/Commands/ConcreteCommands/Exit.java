package clientfx.Commands.ConcreteCommands;



import clientfx.Commands.Command;
import clientfx.Commands.CommandReceiver;
import clientfx.Commands.SerializedCommands.Message;

/**
 * Конкретная команда выхода.
 */
public class Exit extends Command {
    private CommandReceiver commandReceiver;

    public Exit (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public Exit() {}

    @Override
    protected Message execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде exit.");
        }
        return commandReceiver.exit();
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда exit – завершить программу (без сохранения в файл).");
    }
}
