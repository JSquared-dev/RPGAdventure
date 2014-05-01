package event;

public class CommandEvent implements Event<CommandListener> {

    public Command command;
    
    public CommandEvent(Command nCommand) {
        command = nCommand;
    }

    @Override
    public void notify(CommandListener event) {
        // TODO Auto-generated method stub
        event.executeCommand(command);
    }
}
