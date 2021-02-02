package room;


import java.rmi.RemoteException;
import java.util.HashMap;


public class ChatRoom {

    private static HashMap<String, String> listUsersMessage = new HashMap<String, String>();


    public Integer subscribe(String pseudo) throws RemoteException {
        listUsersMessage.put(pseudo, "");
        System.err.println("User Subcribed");
        String message = " "+pseudo+" a rejoint le salon...\n\n\n";
        for (String keyString : listUsersMessage.keySet()) {
            if(!pseudo.equals(keyString)) listUsersMessage.put(keyString, listUsersMessage.get(keyString)+ message);
        }
        return 1;
    }

    
    public Integer unsubscribe(String pseudo) throws RemoteException {
        listUsersMessage.remove(pseudo);
        String message = " "+pseudo+" a quitte le salon...\n\n\n";
        for (String keyString : listUsersMessage.keySet()) {
            if(!pseudo.equals(keyString)) listUsersMessage.put(keyString, listUsersMessage.get(keyString)+ message);
        }
        return 1;
    }

    public String getMessage(String pseudo) throws RemoteException {
        String message = listUsersMessage.get(pseudo);
        listUsersMessage.put(pseudo, "");
        return message;
    }

    
    public Integer postMessage(String pseudo, String message) {
        message = " "+pseudo+" a dit : \n   "+message+"\n\n\n";
        for (String keyString : listUsersMessage.keySet()) {
            if(!pseudo.equals(keyString)) listUsersMessage.put(keyString, listUsersMessage.get(keyString)+message);
        }
        
        return 1;
    }
    
}
