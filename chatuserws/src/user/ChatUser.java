package user;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

import org.apache.axis2.AxisFault;

import room.ChatRoomStub.Subscribe;
import room.ChatRoomStub.SubscribeResponse;

import room.ChatRoomStub.Unsubscribe;
import room.ChatRoomStub.UnsubscribeResponse;

import room.ChatRoomStub.PostMessage;
import room.ChatRoomStub.PostMessageResponse;

import room.ChatRoomStub.GetMessage;
import room.ChatRoomStub.GetMessageResponse;

import room.ChatRoomStub;


public class ChatUser {
    private String title = "Logiciel de discussion en ligne";
    private String pseudo = null;
    
    ChatRoomStub chatRoomStub = null;
    Subscribe subscribe = new Subscribe();
    Unsubscribe unsubscribe = new Unsubscribe();
    PostMessage postMessage = new PostMessage();
    GetMessage getMessage = new GetMessage();
    
    SubscribeResponse subscribeResponse = new SubscribeResponse();
    UnsubscribeResponse unsubscribeResponse = new UnsubscribeResponse();
    PostMessageResponse postMessageResponse = new PostMessageResponse();
    GetMessageResponse getMessageResponse = new GetMessageResponse();
    
    private static final int SLEEP_TIME = 4;

    private JFrame window = new JFrame(this.title);
    private JTextArea txtOutput = new JTextArea();
    private JTextField txtMessage = new JTextField();
    private JButton btnSend = new JButton("Envoyer");

    
    public ChatUser() {
    	try {
			chatRoomStub = new ChatRoomStub();
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

    public void createIHM() {
        // Assemblage des composants
        JPanel panel = (JPanel) this.window.getContentPane();
        JScrollPane sclPane = new JScrollPane(txtOutput);
        panel.add(sclPane, BorderLayout.CENTER);
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(this.txtMessage, BorderLayout.CENTER);
        southPanel.add(this.btnSend, BorderLayout.EAST);
        panel.add(southPanel, BorderLayout.SOUTH);

        // Gestion des évènements
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                window_windowClosing(e);
            }
        });
        btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnSend_actionPerformed(e);
            }

        });
        txtMessage.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                if (event.getKeyChar() == '\n')
                    btnSend_actionPerformed(null);
            }
        });

        // Initialisation des attributs
        this.txtOutput.setBackground(new Color(220, 220, 220));
        this.txtOutput.setEditable(false);
        this.window.setSize(500, 400);
        this.window.setVisible(true);
        this.txtMessage.requestFocus();
    }

    public void requestPseudo() {
        this.pseudo = JOptionPane.showInputDialog(this.window, "Entrez votre pseudo : ", this.title,
                JOptionPane.OK_OPTION);
        if (this.pseudo == null)
            System.exit(0);
    }

    public void window_windowClosing(WindowEvent e) {
        try {
            unsubscribe.setPseudo(this.pseudo);
            unsubscribeResponse = chatRoomStub.unsubscribe(unsubscribe);
            System.err.println("User Unsubcribed : return value = "+ unsubscribeResponse.get_return());

        } catch (Exception e1) {
            System.err.println("ChatUser exception: " + e1.toString());
            e1.printStackTrace();
        }
        System.exit(-1);
    }

    public void btnSend_actionPerformed(ActionEvent e) {
        try {
            if (!this.txtMessage.getText().trim().equals("")) {
                postMessage.setPseudo(this.pseudo);
                postMessage.setMessage(this.txtMessage.getText());;
                postMessageResponse = chatRoomStub.postMessage(postMessage);
                System.err.println("Message Sent : return value = "+postMessageResponse.get_return());

                String message = " Vous avez dit : \n   "+this.txtMessage.getText()+"\n\n\n";
                this.displayMessage(message);
                this.txtMessage.setText("");
                this.txtMessage.requestFocus();
            }
        } catch (Exception e1) {
            System.err.println("ChatUser exception: " + e1.toString());
            e1.printStackTrace();
        }
    }

    public Integer displayMessage(String message) {
        txtOutput.setText(txtOutput.getText() + message);
        return 1;
    }

    public void start() {
        this.createIHM();
        this.requestPseudo();
        try {
        	subscribe.setPseudo(this.pseudo);
            subscribeResponse = chatRoomStub.subscribe(subscribe);
            System.err.println("User Subcribed : return value = "+subscribeResponse.get_return());
        } catch (Exception e) {
            System.err.println("ChatUser exception: " + e.toString());
            e.printStackTrace();
        }

        //BOUCLE POUR LA RECUPARATION DES MESSAGES NON LUS DANS LE chatroom
        while (true) {
            try {
				getMessage.setPseudo(this.pseudo);
				getMessageResponse = chatRoomStub.getMessage(getMessage);
				System.err.println("Got a message : return value = "+getMessageResponse.get_return());
				String message = getMessageResponse.get_return();
				TimeUnit.SECONDS.sleep(SLEEP_TIME);
                if(message != "") this.displayMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    
    public static void main(String[] args) {
        ChatUser user = new ChatUser();
        user.start();
    }
}

