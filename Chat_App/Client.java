import java.awt.*;
import java.awt.event.*;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.text.*;
import java.util.Calendar;
import java.net.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class Client implements ActionListener{
    JTextField text;
    static JPanel a1;
    static Box vertical = Box.createVerticalBox();
   static JFrame f= new JFrame();
   static DataOutputStream dout;
    Client(){//Constructor for frame

        f.setLayout(null);

        JPanel p1= new JPanel();
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,70);//to pass coordinates
        p1.setLayout(null);
        f.add(p1);


        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("icons\\3.png"));
        Image i2= img1.getImage().getScaledInstance(70, 70,Image.SCALE_DEFAULT);//to sacle image
        ImageIcon i3= new ImageIcon(i2);
        
        JButton back = new JButton(i3);
        back.setBounds(0,20,30,30);
        back.setBackground(new Color(45,45,48));
        p1.add(back);

        ImageIcon img2 = new ImageIcon(ClassLoader.getSystemResource("icons\\1.png"));
        Image i4= img2.getImage().getScaledInstance(150, 150,Image.SCALE_DEFAULT);//to sacle image
        ImageIcon i5= new ImageIcon(i4);
        
        JLabel profile = new JLabel(i5);
        profile.setBounds(20,-15,100,100);
        p1.add(profile);

        ImageIcon img3 = new ImageIcon(ClassLoader.getSystemResource("icons\\video1.png"));
        
        Image i6= img3.getImage().getScaledInstance(60, 60,Image.SCALE_DEFAULT);//to sacle image
        ImageIcon i7= new ImageIcon(i6);
        JButton video = new JButton(i7);
        video.setBounds(310,15,40,40);
        video.setBackground(new Color(7,94,84));
        p1.add(video);

        ImageIcon img4 = new ImageIcon(ClassLoader.getSystemResource("icons\\phone1.png"));
        Image i8= img4.getImage().getScaledInstance(60, 60,Image.SCALE_DEFAULT);//to sacle image
        ImageIcon i9= new ImageIcon(i8);
        JButton phone = new JButton(i9);
        phone.setBounds(360,15,40,40);
        phone.setBackground(new Color(7,94,84));
        p1.add(phone);

        ImageIcon img5 = new ImageIcon(ClassLoader.getSystemResource("icons\\3icon.png"));
        Image i10= img5.getImage().getScaledInstance(30, 30,Image.SCALE_DEFAULT);//to sacle image
        ImageIcon i11= new ImageIcon(i10);
        
        JButton morevert = new JButton(i11);
        morevert.setBounds(420,20,20,30);
        morevert.setBackground(new Color(7,94,84));
        p1.add(morevert);

        JLabel name = new JLabel("Bully");
        name.setBounds(110,22,1000,25);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("San-serif",Font.BOLD,18));
        p1.add(name);
        JLabel status = new JLabel("Active Now");
        status.setBounds(115,42,1000,25);
        status.setForeground(Color.white);
        status.setFont(new Font("San-serif",Font.PLAIN,10));
        p1.add(status);

        a1= new JPanel();
        a1.setBounds(5,75,440,570);
        f.add(a1);

        text = new JTextField();
        text.setBounds(10,655,370,40);
        text.setFont(new Font("San-serif",Font.PLAIN,16));
        text.setBackground(new Color(62,62,66));
        f.add(text);

        ImageIcon send = new ImageIcon(ClassLoader.getSystemResource("icons\\send.png"));
        Image s= send.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT);//to sacle image
        ImageIcon s1= new ImageIcon(s);
        
        JButton sen = new JButton(s1);
        sen.setBounds(390,654,40,40);
        f.add(sen);
        sen.addActionListener(this);

        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
        });

        

        f.setSize(450,700);
        f.setLocation(800,50);
        f.setUndecorated(true);

       f.getContentPane().setBackground(Color.white);//


        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    
    @override
    public void actionPerformed(ActionEvent ae) {
        try{
        String out=text.getText();
        JLabel output = new JLabel(out);

        JPanel p2 = formatLabel(out);
        
        
        a1.setLayout(new BorderLayout());
        JPanel right = new JPanel(new BorderLayout());
        right.add(p2,BorderLayout.EAST);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));

        a1.add(vertical,BorderLayout.PAGE_START);

        dout.writeUTF(out);
        
        text.setText("");

        f.repaint();
        f.invalidate();
        f.validate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static JPanel formatLabel(String out){
        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel output = new JLabel(out);
        output.setFont(new Font("Tahoma",Font.PLAIN,16));
        output.setBackground(new Color(7,94,84));
        output.setForeground(Color.WHITE);
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));
        panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");

        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        return panel;

    }
    public static JPanel formatLabel1(String out){
        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel output = new JLabel(out);
        output.setFont(new Font("Tahoma",Font.PLAIN,16));
        output.setForeground(Color.WHITE);
        output.setBackground(new Color(62,62,66));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));
        panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");

        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        return panel;

    }

    public static void main(String[] args) {
        new Client();//annonymous object

        try{
            Socket s = new Socket("127.0.0.1",6001);
            while (true) {
                // Socket s= skt.accept();
                DataInputStream din = new DataInputStream(s.getInputStream());
                dout =new DataOutputStream(s.getOutputStream());
                

                while (true) {
                    a1.setLayout(new BorderLayout());
                    String msg = din.readUTF();
                    JPanel panel = formatLabel1(msg);

                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel, BorderLayout.LINE_START);
                    vertical.add(left);
                    

                    vertical.add(Box.createVerticalStrut(15));
                    a1.add(vertical,BorderLayout.PAGE_START);
                    f.validate();

                }
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    } 
}