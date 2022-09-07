/* 
path C:\Program Files (x86)\java\jdk1.7.0\bin
cd c:\1
javac Server.java
java Server
jar cmf manif.txt  Server.jar *.class
GUI SWING JAVA7 SOCKET CHAT
Put Server.java Client.Java input.txt in the same folder
You can compile or create .jar
input.txt line1 is port (26000 default for Quake1); second line is IP
run server, after - client. 
tested Socketchat on localhost and LAN (Win7-XP direct LAN cable):
port
26000
ip 
192.168.0.1
192.168.0.2
mask
255.255.0.0
The firewalls, antivirus can block, be careful
best, Oleg
*/

import java.io.*;
import java.util.*;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 

public class Server extends JFrame{
public  Socket clientSocket; 
 public    ServerSocket server; 
 public    BufferedReader in; 
  public   BufferedWriter out; 
public static BufferedReader reader;

JFrame jf;
JFrame f;
Label label;
Label label2;
Label label3;
Label label4;
Label label6;
Label label5;
public    JTextField smallField;
String s="test";
public int servansw=0;
public int portnum=26000; //quake 1 check for servers

Server () 
{
f=new JFrame("server");
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f.setLayout(new GridLayout(8,1,1,1));

smallField = new JTextField("WAIT FOR CLIENT");
   
        smallField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                message();
            }
        });
 f.add(smallField);

label = new Label();
label.setText("Wait for client");
    f.add(label);

label2 = new Label();
label2.setText("");
    f.add(label2);

label6 = new Label();
label6.setText("____");
    f.add(label6);

label5 = new Label();
label5.setText("(port and IP are in  input.txt)");
    f.add(label5);

String localip="loc";
InetAddress ip;
	  try {
		ip = InetAddress.getLocalHost();
		localip= "Current IP address : " + ip.getHostAddress();
	  } catch (UnknownHostException e) {
		System.err.println(e);
	  }

label3 = new Label();
label3.setText(localip);
    f.add(label3);

String str2 = "str2text";
try{
File myFile = new File("./input.txt");
Scanner scan = new Scanner(myFile);
String str=scan.nextLine();
str2=str;
    scan.close();
}
catch(Exception e)
{System.out.println(e);
str2="26000 is running by default. input.txt error";
}   

try {
int inttr = Integer.parseInt(str2);
portnum= inttr;
} 
catch (NumberFormatException e) {
str2="26000 is running by default. input.txt error";
}

label4 = new Label();
label4.setText("port "+str2);
    f.add(label4);
          
f.setSize(300,300);
f.setVisible(true);

runs();

}

    public static void main(String[] args) {
new Server();
    }


public void runs(){

try {
            try  {
                server = new ServerSocket(portnum); 
                clientSocket = server.accept(); 
                try { 
while (true) {//cycle start
 
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    String word = in.readLine(); 
                   
label.setText("client says: "+word);
smallField.setText("");
label2.setText("server GO");
redraw();

while (servansw<1){
   try{Thread.sleep(100);}catch(InterruptedException e){System.out.println(e);}   
}

servansw=0;
 word = s; 
                out.write(word + "\n"); 
                    out.flush(); 

               if (word.equals("quit")) {
                   out.write(">> OKserv");
                   out.newLine();
                   out.flush();
                   break;
               }

}//cycle end

                } finally { 
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Server is closed");
                    server.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }

} //run server end



public void message(){
s=smallField.getText();
smallField.setText("WAIT");
label2.setText("server responds: "+s);
redraw();

servansw=1;
}

public void redraw(){

f.add(smallField);
f.add(label);
f.add(label2);
f.add(label6);
f.add(label5);
f.add(label3);
f.add(label4);
f.setVisible(true);

}

}
