
/**
 * ChatRoomCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */

    package room;

    /**
     *  ChatRoomCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ChatRoomCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ChatRoomCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ChatRoomCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for postMessage method
            * override this method for handling normal response from postMessage operation
            */
           public void receiveResultpostMessage(
                    room.ChatRoomStub.PostMessageResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from postMessage operation
           */
            public void receiveErrorpostMessage(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for subscribe method
            * override this method for handling normal response from subscribe operation
            */
           public void receiveResultsubscribe(
                    room.ChatRoomStub.SubscribeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from subscribe operation
           */
            public void receiveErrorsubscribe(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for unsubscribe method
            * override this method for handling normal response from unsubscribe operation
            */
           public void receiveResultunsubscribe(
                    room.ChatRoomStub.UnsubscribeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from unsubscribe operation
           */
            public void receiveErrorunsubscribe(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getMessage method
            * override this method for handling normal response from getMessage operation
            */
           public void receiveResultgetMessage(
                    room.ChatRoomStub.GetMessageResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMessage operation
           */
            public void receiveErrorgetMessage(java.lang.Exception e) {
            }
                


    }
    