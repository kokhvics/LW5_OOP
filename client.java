import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class client {
    private static List<String> connectedUsers = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JTextField ipField = new JTextField();
        ipField.setBounds(20, 20, 200, 30);
        frame.add(ipField);

        JButton connectButton = new JButton("Подключиться");
        connectButton.setBounds(20, 60, 120, 30);
        frame.add(connectButton);

        JTextArea userStatusArea = new JTextArea();
        userStatusArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(userStatusArea);
        scrollPane.setBounds(20, 100, 360, 150);
        frame.add(scrollPane);

        JButton disconnectButton = new JButton("Отключиться");
        disconnectButton.setBounds(150, 60, 120, 30);
        frame.add(disconnectButton);

        connectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ip = ipField.getText();
                int port = 8080; // Порт сервера

                try {
                    Socket socket = new Socket(ip, port);
                    System.out.println("Join to server " + ip + ":" + port + " is successful.");

                    connectedUsers.add(ip); // Добавляем пользователя в список подключенных

                    updateConnectedUsers(userStatusArea); // Обновляем список подключенных пользователей

                    System.out.println("Client from " + ip + " joined."); // Выводим сообщение о подключении пользователя

                    socket.close(); // Закрываем соединение с сервером
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        disconnectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ip = ipField.getText();

                connectedUsers.remove(ip); // Удаляем пользователя из списка подключенных

                updateConnectedUsers(userStatusArea); // Обновляем список подключенных пользователей

                System.out.println("Client from " + ip + " left."); // Выводим сообщение об отключении пользователя
            }
        });

        frame.setLayout(null);
        frame.setVisible(true);
    }

    private static void updateConnectedUsers(JTextArea userStatusArea) {
        StringBuilder sb = new StringBuilder();

        for (String user : connectedUsers) {
            sb.append(user).append("\n");
        }

        userStatusArea.setText(sb.toString());
    }
}