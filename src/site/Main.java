package site;

import site.SHA512.Sha512;
import site.MD5.Md5;
import java.nio.charset.StandardCharsets;

import site.Utils.RandomString;

public class Main {
    public static void main(String[] args) throws Exception {
        String encrypted = "";
        long start = 0;
        long end = 0;

        if (args.length < 2) {
            throw new Error("args must be have at last 2");
        }

        if (args[0].equals("SHA512")) {
            start = System.currentTimeMillis();
            Sha512 algorithm = new Sha512(args[1].getBytes(StandardCharsets.UTF_8));
            encrypted = algorithm.encrypt();
            end = System.currentTimeMillis();
        }

        if (args[0].equals("MD5")) {
            start = System.currentTimeMillis();
            Md5 algorithm = new Md5(args[1].getBytes(StandardCharsets.UTF_8));
            encrypted = algorithm.encrypt();
            end = System.currentTimeMillis();
        }

        System.out.println("Word " + args[1] +" encrypted in algorithm " + args[0]+ " is: " + encrypted + "\n");
        System.out.println("Elapsed Time in milli seconds for execution of algorithm " + args[0] + " is: " + (end-start)+ "ms" + "\n");
    }
}