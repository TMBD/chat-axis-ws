package room;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;


public class ChatRoom {

    private static HashMap<String, ArrayList<String[]>> listUsersMessage = new HashMap<String, ArrayList<String[]>>();


    public Integer subscribe(String pseudo) throws RemoteException {
        listUsersMessage.put(pseudo, new ArrayList<String[]>());
        System.err.println("User Subcribed");
        String [] message =new String[] { "", pseudo+ " a rejoint le salon..."};
        for (String keyString : listUsersMessage.keySet()) {
        	 if(!pseudo.equals(keyString)) listUsersMessage.get(keyString).add(message);
//            if(!pseudo.equals(keyString)) listUsersMessage.put(keyString, listUsersMessage.get(keyString).add(message));
        }
        return 1;
    }

    
    public Integer unsubscribe(String pseudo) throws RemoteException {
        listUsersMessage.remove(pseudo);
        System.err.println("User Unsubcribed");
        String [] message =new String[] { "", pseudo+ " a quitté le salon..."};
        for (String keyString : listUsersMessage.keySet()) {
        	 if(!pseudo.equals(keyString)) listUsersMessage.get(keyString).add(message);
        }
        return 1;
    }

//    public String[][] getMessage(String pseudo) throws RemoteException {
//        ArrayList<String[]> messageArrayList = listUsersMessage.get(pseudo);
//        String[][] messageArray = new String[messageArrayList.size()][2];
//        for (int i = 0; i < messageArray.length; i++) {
//			messageArray[i] = messageArrayList.get(i);
//		}
//        listUsersMessage.put(pseudo, new ArrayList<String[]>());
//        return messageArray;
//    }
    
    public String[][] getMessage(String pseudo) throws RemoteException {
//    	System.out.println("pseudo = "+pseudo);
//    	System.out.println("listUsersMessage = "+listUsersMessage.get(pseudo));
        ArrayList<String[]> messageArrayList = listUsersMessage.get(pseudo);
        String[][] messageArray = new String[messageArrayList.size()][2];
        for (int i = 0; i < messageArray.length; i++) {
			messageArray[i] = messageArrayList.get(i);
		}
        listUsersMessage.put(pseudo, new ArrayList<String[]>());
        
        if(messageArrayList.size() > 0) {
        	return messageArray;
        }else {
        	String[][] messageArrayAlt = new String[1][2];
        	messageArrayAlt[0][0] = "";
        	messageArrayAlt[0][1] = "";
        	return messageArrayAlt;
        }
//        
//        if(messageArrayList.size() > 0) {
//        	String[][] messageArray2 = new String[1][2];
//        	messageArray2[0][0] = "Modou";
//        	messageArray2[0][1] = "HELLLOOOOO";
//        	System.out.println(messageArray2[0][0]);
//        	System.out.println(messageArray2[0][1]);
//        	return messageArray2;
//        }else {
//        	System.out.println(messageArray[0][0]);
//        	System.out.println(messageArray[0][1]);
//        	return messageArray;
//        }
        
        
        //return messageArray;
    }

    
    public Integer postMessage(String pseudo, String message) {
        //message = " "+pseudo+" a dit : \n   "+message+"\n\n\n";
        String [] messageArray = new String[] { pseudo, message};
        for (String keyString : listUsersMessage.keySet()) {
        	if(!pseudo.equals(keyString)) listUsersMessage.get(keyString).add(messageArray);
            //if(!pseudo.equals(keyString)) listUsersMessage.put(keyString, listUsersMessage.get(keyString)+message);
        }
        
        return 1;
    }
    
}
