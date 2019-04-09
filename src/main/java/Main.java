import org.itstep.model.Message;
import org.itstep.model.Room;
import org.itstep.model.User;
import org.itstep.services.AdminService;
import org.itstep.services.MessageService;
import org.itstep.services.RoomService;
import org.itstep.services.UserService;
import org.itstep.services.impl.dao.MessageServiceDaoImpl;
import org.itstep.services.impl.dao.RoomServiceDaoImpl;
import org.itstep.services.impl.dao.UserServiceDaoImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

public class Main {

    public static void main(String[] args) {
        RoomServiceDaoImpl roomService = RoomServiceDaoImpl.getInstance();
        UserServiceDaoImpl userService = UserServiceDaoImpl.getInstance();
        UserServiceDaoImpl adminService = UserServiceDaoImpl.getInstance();
        MessageServiceDaoImpl messageService = MessageServiceDaoImpl.getInstance();

//        Collection<User> user = adminService.getAllUsers();
//        for (User user1 : user) {
//            System.out.println(user1);
//        }
//----------------------------
//        User user = adminService.getUserById(3L);
//        System.out.println(user);
//--------------------------
//        User user = adminService.getUserByLoginAndName("va","ivan22");
//        System.out.println(user);
//---------------------------
//        User result = adminService.createUser("dima"
//                ,"dima123"
//                , "Dima"
//                , "Petrov");
//        System.out.println(result);
//------------------------
//------------------------
//        User result = adminService.changeUserName(11L,"Dimas");
//        System.out.println(result);
//--------------------------
//        Boolean user = adminService.deleteUser(11L);
//        System.out.println(user);
        //----------------------
//        User user = adminService.setAdmin(12L, true);
//        System.out.println(user);
//--------------------------
//        User result = userService.setNickname(12L, "DDDD");
//        System.out.println(result);

//-------------- room------------

//        Collection<Room> rooms = roomService.getAllPublicRoom();
//        for (Room room : rooms) {
//            System.out.println(room);
//        }
//----------------------
        Collection<User> user = roomService.getUsersFromRoom(
                roomService.getRoomFromCaption("Room_third"));
        for (User user1 : user) {
            System.out.println(user1);
        }
//---------------------
//        Room result = roomService.getRoomFromCaption("Main_Boltalka");
//        System.out.println(result);
//---------------------
//        // добавление комнаты
//        Room result = roomService.createRoom("Office",
//                "", adminService.getUserById(1L),
//                999L);
//        System.out.println(result);
//---------------------
//        String res = roomService.getDefaultRoomCaption();
//        System.out.println(res);
////-------------------

        // добавлнение пользователя в комнату
//        Boolean result = roomService.addUserToRoom(3,2);
//        System.out.println(result);
//-----------------------
//        Room result = roomService.changeCaption(
//                roomService.getRoomFromCaption("Reception")
//                ,"Room_third"
//        ,adminService.getUserById(2L));
//        System.out.println(result);
//--------------------
//        Boolean result = roomService.setMaxUser(
//                roomService.getRoomFromCaption("Room_third")
//               ,adminService.getUserById(2L)
//                ,10L);
//        System.out.println(result);
////----------------------
//        Boolean result = roomService.checkExistUserInToRoom(
//                roomService.getRoomFromCaption("Room_third")
//                        ,adminService.getUserById(3L));
//        System.out.println(result);
//----------------------
//        Boolean result = roomService.deleteRoom(
//                roomService.getRoomFromCaption("Office"),
//                adminService.getUserById(3L));
//        System.out.println(result);

//--------------message--------------
        // получить сообщения комнаты
//        Collection<Message> result = messageService.getMessagesFromRoom(
//                roomService.getRoomFromCaption("Second_boltalka"),1);
//        for (Message message : result) {
//            System.out.println(message);
//        }
//------------------------
//        Boolean result = messageService.sendTextMessage(
//                roomService.getRoomFromCaption("Second_boltalka"),
//                adminService.getUserById(2L),"ffkjh nlkm");
//        System.out.println(result);
//-------------------------
//        Boolean rs = messageService.changeMessage(6L,
//                adminService.getUserById(1L),"dhsadnkco");
//        System.out.println(rs);
//-----------------------
//

    }

}
