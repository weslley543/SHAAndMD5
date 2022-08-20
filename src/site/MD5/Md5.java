package site.MD5;

import site.Algorithm.Algorithm;

public class Md5  implements Algorithm {
    byte[] input;

    public Md5(byte[] input) {
        this.input = input;
    }
    @Override
    public String encrypt() {
        int messageLength = this.input.length;
        return "";
    }
}
