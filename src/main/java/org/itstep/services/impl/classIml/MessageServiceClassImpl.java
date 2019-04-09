package org.itstep.services.impl.classIml;

import org.itstep.model.Message;
import org.itstep.model.Room;
import org.itstep.model.User;
import org.itstep.services.MessageService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

public class MessageServiceClassImpl implements MessageService {

    private static MessageServiceClassImpl messageServiceClass;

    private LongAdder counter = new LongAdder();

    private List<Message> messages = new ArrayList<>();

    private Map<Room, List<Message>> messageMap = new HashMap<>();

    private Boolean rs = false;

    private MessageServiceClassImpl() {
    }

    public static MessageServiceClassImpl getInstance() {
        if (messageServiceClass == null) {
            messageServiceClass = new MessageServiceClassImpl();
        }
        return messageServiceClass;
    }

    @Override
    public Boolean deleteMessage(Long messageId, User owner) {
        messageMap.forEach((r, lm) -> {
            Message delMes = lm.stream()
                    .filter(m -> m.getId().longValue() == messageId && (m.getUser().equals(owner) || owner.getAdmin()))
                    .findFirst()
                    .orElse(null);
            if (delMes != null) {
                rs = messageMap.get(r).remove(delMes);
            }
        });
        return rs;

    }

    @Override
    public Collection<Message> getMessagesFromRoom(Room room, Integer lastCount) {
        if (messageMap.get(room) == null) {
            return new ArrayList<>();
        }
        return messageMap.get(room)
                .stream()
                .skip(messageMap.get(room).size() >= lastCount ? messageMap.get(room).size() - lastCount : 0)
                //.limit(lastCount)
                .collect(Collectors.toList());

    }

    @Override
    public Boolean sendTextMessage(Room room, User user, String message) {
        if (!isValidUserAndRoom(user, room)) {
            return false;
        }
        counter.increment();
        Message newMessage = new Message()
                .setId(counter.sum())
                .setRoom(room)
                .setUser(user)
                .setMessage(message)
                .setMessageDate(LocalDateTime.now());

        if (!messageMap.containsKey(room)) {
            return messageMap.put(room, Collections.singletonList(newMessage)) == null;
        } else {
            List<Message> messages = new ArrayList<>();
            messages.addAll(messageMap.get(room));
            messages.add(newMessage);
            return messageMap.put(room, messages) != null;
        }

    }

    @Override
    public Boolean sendPicture(Room room, User user, byte[] picture) {
        return null;
    }

    @Override
    public Boolean changeMessage(Long messageId, User owner, String newMessage) {
        return null;
    }


    private Boolean isValidUserAndRoom(User user, Room room) {
        return room != null && user != null;
    }
}
