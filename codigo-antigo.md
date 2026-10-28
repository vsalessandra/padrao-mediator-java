# Código antigo — Sala de Chat (antes do Mediator)

Versão original do sistema, em que cada participante mantém referências diretas para os demais e encaminha as mensagens por conta própria.

## ChatUser.java

```java
import java.util.ArrayList;
import java.util.List;

public class ChatUser {

    private String name;
    private List<ChatUser> contacts = new ArrayList<>();

    public ChatUser(String name) {
        this.name = name;
    }

    public void addContact(ChatUser user) {
        contacts.add(user);
    }

    public void send(String message) {
        for (ChatUser contact : contacts) {
            contact.receive(name, message);
        }
    }

    public void receive(String senderName, String message) {
        System.out.println(name + " received from " + senderName + ": " + message);
    }
}
```

## Main.java

```java
public class Main {

    public static void main(String[] args) {

        ChatUser ana = new ChatUser("Ana");
        ChatUser bruno = new ChatUser("Bruno");
        ChatUser carla = new ChatUser("Carla");

        ana.addContact(bruno);
        ana.addContact(carla);
        bruno.addContact(ana);
        bruno.addContact(carla);
        carla.addContact(ana);
        carla.addContact(bruno);

        ana.send("Hello, everyone!");
        bruno.send("Hello, Ana!");
    }
}
```

## Problemas identificados

- **Acoplamento direto:** cada `ChatUser` precisa conhecer todos os outros participantes e manter sua própria lista de contatos.
- **Configuração frágil:** a entrada de uma nova pessoa exige atualizar várias listas (com N usuários, são N × (N − 1) chamadas a `addContact`). Uma configuração incompleta faz com que alguns participantes recebam mensagens e outros sejam esquecidos.
- **Regras espalhadas:** a lógica de encaminhamento fica distribuída entre os próprios usuários. Mudar a forma de comunicação exige alterar o comportamento de cada participante.
- **Sem validações:** nada impede contatos duplicados (mensagens recebidas em dobro), contatos nulos ou que o usuário adicione a si mesmo como contato.
