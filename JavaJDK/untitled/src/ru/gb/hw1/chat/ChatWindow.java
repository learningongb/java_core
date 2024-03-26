package ru.gb.hw1.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;

/*
Создать окно клиента чата. Окно должно содержать JtextField для ввода логина, пароля, IP-адреса сервера,
порта подключения
к серверу, область ввода сообщений, JTextArea область просмотра сообщений чата и JButton подключения к серверу
и отправки сообщения в чат. Желательно сразу сгруппировать компоненты, относящиеся
к серверу сгруппировать на JPanel сверху экрана, а компоненты, относящиеся к отправке сообщения – на JPanel снизу
 */
public class ChatWindow extends JFrame {
    private static final String logFile = "chat.txt";
    private static final int WINDOW_HEIGHT = 555;
    private static final int WINDOW_WIDTH = 507;
    private static final int WINDOW_POSX = 500;
    private static final int WINDOW_POSY = 100;
    JPanel panel = new JPanel(new GridLayout(1, 2));
    JLabel jlb1 = new JLabel("Логин: ");
    JTextField jtf1 = new JTextField();

    JPanel panel2 = new JPanel(new GridLayout(1, 2));
    JLabel jlb2 = new JLabel("Пароль: ");
    JTextField jtf2 = new JTextField();

    JPanel panel3 = new JPanel(new GridLayout(1, 2));
    JLabel jlb3 = new JLabel("IP адрес сервера: ");
    JTextField jtf3 = new JTextField();

    JPanel panel4 = new JPanel(new GridLayout(1, 2));
    JLabel jlb4 = new JLabel("Номер порта: ");
    JTextField jtf4 = new JTextField();

    JButton btnLogin = new JButton("Подключиться");
    JTextArea textChat = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(textChat);
    JPanel panelMain = new JPanel(new GridLayout(10, 1));

    JLabel jlb6 = new JLabel("Текст сообщения: ");
    JTextArea chatMessage = new JTextArea();
    JButton pushMsg = new JButton("Отправить (ctrl+Enter)");

    public ChatWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("GB_chat");
        setResizable(true);
        setVisible(true);

        panelMain.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setLayout(new GridLayout(1, 1));
        panel.add(jlb1);
        panel.add(jtf1);
        panel2.add(jlb2);
        panel2.add(jtf2);
        panel3.add(jlb3);
        panel3.add(jtf3);
        panel4.add(jlb4);
        panel4.add(jtf4);
        panelMain.add(panel);
        panelMain.add(panel2);
        panelMain.add(panel3);
        panelMain.add(panel4);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {readLog();}
        });
        panelMain.add(btnLogin);
        textChat.setEditable(false);
        textChat.setWrapStyleWord(true);
        textChat.setLineWrap(true);
        panelMain.add(scrollPane);

        panelMain.add(jlb6);
        chatMessage.setWrapStyleWord(true);
        chatMessage.setLineWrap(true);
        chatMessage.getActionMap().put("pressKey", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pushMsg.getModel().setPressed(true);
            }
        });
        chatMessage.getActionMap().put("sendMessage", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionSendMessage();
                pushMsg.getModel().setPressed(false);
            }
        });
        InputMap inputMap = chatMessage.getInputMap();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_DOWN_MASK, false), "pressKey");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_DOWN_MASK, true), "sendMessage");
        panelMain.add(chatMessage);
        pushMsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                actionSendMessage();
            }
        });
        panelMain.add(pushMsg);
        add(panelMain);

    }

    private void actionSendMessage() {
        String message = chatMessage.getText();
        if (message.isEmpty())
            return;
        message = "\n"+message;
        writeToLog(message);
        writeToChat(message);
        chatMessage.setText("");
    }

    private void readLog() {
        textChat.setText("");
        File file = new File(logFile);
        if ( !file.exists() || file.isDirectory() )
            return;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(logFile), StandardCharsets.UTF_8))) {
            int b;
            while ((b = bufferedReader.read()) != -1) {
                textChat.append(Character.valueOf((char) b).toString());
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void writeToLog(String message) {
        try (FileOutputStream fw = new FileOutputStream(logFile, true)) {
            for (byte b: message.getBytes(StandardCharsets.UTF_8)) {
                fw.write(b);
            }
            fw.flush();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    private void writeToChat(String message) {
        textChat.append(message);
    }

}