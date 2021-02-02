
/**
 * ChatRoomRemoteExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */

package room;

public class ChatRoomRemoteExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1611626488814L;
    
    private room.ChatRoomStub.ChatRoomRemoteException faultMessage;

    
        public ChatRoomRemoteExceptionException() {
            super("ChatRoomRemoteExceptionException");
        }

        public ChatRoomRemoteExceptionException(java.lang.String s) {
           super(s);
        }

        public ChatRoomRemoteExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ChatRoomRemoteExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(room.ChatRoomStub.ChatRoomRemoteException msg){
       faultMessage = msg;
    }
    
    public room.ChatRoomStub.ChatRoomRemoteException getFaultMessage(){
       return faultMessage;
    }
}
    