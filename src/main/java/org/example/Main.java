package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Сервер запущен");
        int port = 8090;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));)
                {

                    System.out.printf("New connection accepted. Port: %d%n", clientSocket.getPort());

                    //Вывел сообщение с сервера
                    out.println(String.format("Write your name: ->>"));
                    //Считал имя с клиента
                    final String firstName = in.readLine();
                    //Второе сообщение с сервера
                    out.println(String.format("Are you child?"));
                    //Считаю ответ от пользователя

                    String questionOfChilde;
                    while(true) {
                        questionOfChilde = in.readLine();

                        if (questionOfChilde.equals("yes")) {
                            System.out.format("%s зашел ",firstName);
                            out.println(String.format("Welcome to the kids area, %s! Let's play!",
                                    firstName));
                            break;
                        } else if (questionOfChilde.equals("no")) {
                            System.out.format("%s не зашел",firstName);
                            out.println(String.format("Welcome to the adult zone, %s! " +
                                            "Have a good rest, or a good working day!",
                                    firstName));
                            break;
                        } else {
                            out.println(String.format("Are you child?"));
                        }
                    }
                }
            }

        }

    }
}