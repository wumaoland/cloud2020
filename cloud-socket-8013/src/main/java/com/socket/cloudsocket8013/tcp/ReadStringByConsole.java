package com.socket.cloudsocket8013.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadStringByConsole {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String s = null;
            try {
                s = bufferedReader.readLine();
                System.out.println(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
