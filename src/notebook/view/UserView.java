package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run() {
        Commands com;

        while (true) {
            String command = prompt("Введите команду: ");
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            switch (com) {
                case LIST:
                    List<User> users = userController.readAll();
                    System.out.println(users);
                    break;
                case CREATE:
                    String firstName = prompt("Имя: ");
                    String lastName = prompt("Фамилия: ");
                    String phone = prompt("Номер телефона: ");
                    User u = userController.createUser(firstName, lastName, phone);
//                    User u = createUser();
                    userController.saveUser(u);
                    break;
                case READ:
                    String id = prompt("Идентификатор пользователя: ");
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case UPDATE:
                    String userId = prompt("Enter user id: ");
                    String frstName = prompt("Имя: ");
                    String lstName = prompt("Фамилия: ");
                    String phn = prompt("Номер телефона: ");
                    userController.updateUser(userId, userController.createUser(frstName, lstName, phn));
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

//    private User createUser() {
//        String firstName = prompt("Имя: ");
//        String lastName = prompt("Фамилия: ");
//        String phone = prompt("Номер телефона: ");
//        return new User(firstName, lastName, phone);
//    }
}
