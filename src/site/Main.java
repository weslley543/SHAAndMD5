package site;

import site.SHA512.Sha512;

import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws Exception {
        String encrypted = "";

        if(args.length != 2){
            throw new Error("args must be equal 2");
        }

        if(args[0].equals("SHA512")) {
            Sha512 algorithm = new Sha512(args[1].getBytes(StandardCharsets.UTF_8));
            encrypted = algorithm.encrypt();
        }

        if(args[0].equals("MD5")) {
            // implementar aqui
            encrypted = "";
        }

        System.out.print(encrypted + "\n");
    }
}