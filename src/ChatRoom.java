import java.util.ArrayList;
import java.util.List;

public class ChatRoom implements ChatMediator {

    private final List<ChatUser> users = new ArrayList<>();

    @Override
    public void register(ChatUser user) {
        if (user == null) {
            throw new IllegalArgumentException("User must not be null.");
        }
        if (!user.isMediatedBy(this)) {
            throw new IllegalArgumentException(user.getName() + " is associated with another mediator.");
        }
        if (isRegistered(user)) {
            System.out.println(user.getName() + " is already registered in the room.");
            return;
        }
        users.add(user);
        System.out.println(user.getName() + " joined the room.");
    }

    @Override
    public void send(String message, ChatUser sender) {
        if (sender == null || !isRegistered(sender)) {
            String name = sender == null ? "Unknown sender" : sender.getName();
            System.out.println("Message refused: " + name + " is not registered in the room.");
            return;
        }
        for (ChatUser user : users) {
            if (user != sender) {
                user.receive(sender.getName(), message);
            }
        }
    }

    private boolean isRegistered(ChatUser user) {
        for (ChatUser registered : users) {
            if (registered == user) {
                return true;
            }
        }
        return false;
    }
}
