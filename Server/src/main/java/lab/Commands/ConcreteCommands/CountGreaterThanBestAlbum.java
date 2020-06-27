package lab.Commands.ConcreteCommands;


import lab.BasicClasses.Album;
import lab.Commands.Command;
import lab.Commands.CommandReceiver;
import lab.Commands.SerializedCommands.Message;

public class CountGreaterThanBestAlbum extends Command {
    private static final long serialVersionUID = 33L;

    @Override
    public String execute(Object argObject) {
        Album album = ((Message)argObject).getMusicBand().getBestAlbum();
        CommandReceiver commandReceiver = new CommandReceiver();
        return commandReceiver.countGreaterThanBestAlbum(album);
    }
}
