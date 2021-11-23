package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ladla", "cErE", (byte) 3);
        userService.saveUser("GefE", "FEFE", (byte) 6);
        userService.saveUser("WERweEW", "Dimin", (byte) 32);
        userService.saveUser("efwef", "ewfewef", (byte) 12);
    }
}