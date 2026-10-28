public class Main {

    public static void main(String[] args) {

        ChatMediator room = new ChatRoom();

        ChatUser ana = new ChatUser("Ana", room);
        ChatUser bruno = new ChatUser("Bruno", room);
        ChatUser carla = new ChatUser("Carla", room);

        System.out.println("=== Registering Ana, Bruno and Carla ===");
        room.register(ana);
        room.register(bruno);
        room.register(carla);

        System.out.println("\n=== Registering Bruno again ===");
        room.register(bruno);

        System.out.println("\n=== Ana sends a message ===");
        ana.send("Hello, everyone!");

        System.out.println("\n=== Diego joins the room ===");
        ChatUser diego = new ChatUser("Diego", room);
        room.register(diego);

        System.out.println("\n=== Bruno sends a message ===");
        bruno.send("Hello, Ana!");

        System.out.println("\n=== Eva (associated with the room, not registered) sends a message ===");
        ChatUser eva = new ChatUser("Eva", room);
        eva.send("Can anyone hear me?");
    }
}
