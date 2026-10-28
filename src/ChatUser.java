public class ChatUser {

    private final String name;
    private final ChatMediator mediator;

    public ChatUser(String name, ChatMediator mediator) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be empty.");
        }
        if (mediator == null) {
            throw new IllegalArgumentException("Mediator must not be null.");
        }
        this.name = name;
        this.mediator = mediator;
    }

    public String getName() {
        return name;
    }

    public void send(String message) {
        mediator.send(message, this);
    }

    public void receive(String senderName, String message) {
        System.out.println(name + " received from " + senderName + ": " + message);
    }

    boolean isMediatedBy(ChatMediator mediator) {
        return this.mediator == mediator;
    }
}
