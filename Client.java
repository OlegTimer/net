
import java.io.*;
import java.util.*;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 

public class Client extends JFrame{
 public    ServerSocket server; 
    private static Socket clientSocket; 
    private static BufferedReader reader; 
    private static BufferedReader in; 
    private static BufferedWriter out; 


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
String ips="192.168.0.1";
public int servansw=0;
public int portnum=26000; //quake 1 check for servers


Client () 
{
f=new JFrame("client");
f.getContentPane().setBackground(Color.GREEN);
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f.setLayout(new GridLayout(8,1,1,1));

smallField = new JTextField("");
   
        smallField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                message();
            }
        });
 f.add(smallField);

label = new Label();
label.setText("GO client");
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


String str2 = "str2text";
try{
File myFile = new File("./input.txt");
Scanner scan = new Scanner(myFile);
String str=scan.nextLine();
str2=str;
ips=scan.nextLine();
    scan.close();
}
catch(Exception e)
{System.out.println(e);
str2="26000 is running by default. input.txt error";
ips= "ip not found. input.txt error";
}  


label3 = new Label();
label3.setText(ips);
    f.add(label3);
 

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
new Client();
    }

public void runs(){
 try {
            try {
                clientSocket = new Socket(ips, portnum); 
                reader = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

while(true){//cycle start


while (servansw<1){
   try{Thread.sleep(100);}catch(InterruptedException e){System.out.println(e);}   
}

  servansw=0;
String word = s;
                out.write(word + "\n"); 
                out.flush();

                String serverWord = in.readLine();
          label.setText("GO client");      
label2.setText("server responds: "+serverWord);
smallField.setText("");
redraw();

    // If users send 	quit (To end conversation).
               if (word.equals("quit")) {
                   out.write(">> OK");
                   out.newLine();
                   out.flush();
                   break;
               }

}//cycle end
            } finally { // в любом случае необходимо закрыть сокет и потоки
                System.out.println("Client closed");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
}//runs end

public void message(){
s=smallField.getText();
smallField.setText("WAIT");
label.setText("client says: "+s);
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
