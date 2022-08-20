package site.MD5;

import site.Algorithm.Algorithm;
import site.MD5.MD5Constants;
public class Md5  implements Algorithm {
    byte[] input;

    MD5Constants md5ConstantsImpl = null;
    public Md5(byte[] input) {
        this.input = input;
        this.md5ConstantsImpl = new MD5Constants();
    }
    @Override
    public String encrypt() {
        int messageLength = this.input.length;
        int blockNumber = ((messageLength + 8) >>> 6)  + 1;
        int totalSize = blockNumber << 6;
        int[] TABLE_T = this.md5ConstantsImpl.getK();

        byte[] paddingBytes = new byte[totalSize - messageLength];

        long messageLenBits = (long)messageLength << 3;

        for(int i=0 ; i<8; i++){
            paddingBytes[paddingBytes.length - 8 +i] = (byte)messageLength;
            messageLenBits >>>=8;
        }

        int a = this.md5ConstantsImpl.INIT_A;
        int b = this.md5ConstantsImpl.INIT_B;
        int c = this.md5ConstantsImpl.INIT_C;
        int d = this.md5ConstantsImpl.INIT_D;

        int [] buffer = new int [16];
        for(int i=0; i<blockNumber ; i++) {
            int index = i << 6;
            for(int j=0 ; j< 64; j++, index++) {
                buffer[j >> 2] = ((int) ((index < messageLength) ? this.input[index] : paddingBytes[index - messageLength]) << 24) | (buffer[j >>> 2] >>> 8);
            }
                int originalA = a;
                int originalB = b;
                int originalC = c;
                int originalD = d;

                for(int j = 0 ; j<64; j++) {
                    int div16 = j >>> 4;
                    int f = 0;
                    int bufferIndex = j;
                    switch (div16) {
                        case 0:
                            f = (b & c) | (~b & d);
                            break;
                        case 1:
                            f = (b & d ) | (~b & d);
                            bufferIndex = (bufferIndex * 5 + 1) & 0x0F;
                            break;
                        case 2:
                            f = b ^ c ^ d;
                            bufferIndex = (bufferIndex*3 + 5) & 0x0F;
                        case 3:
                            f = c ^(b | ~d);
                            bufferIndex = (bufferIndex* 7) & 0x0F;
                    }
                    int temp = b + Integer.rotateLeft(a + f + buffer[bufferIndex] +TABLE_T[j], this.md5ConstantsImpl.r[div16 << 2] | (j & 3));
                    a = d;
                    d = c;
                    c = b;
                    b= temp;
                }
                a += originalA;
                b += originalB;
                c += originalC;
                d += originalD;

        }
        byte[] md5 = new byte[16];
        int count = 0;
        for (int i = 0; i < 4; i++)
        {
            int n = (i == 0) ? a : ((i == 1) ? b : ((i == 2) ? c : d));
            for (int j = 0; j < 4; j++)
            {
                md5[count++] = (byte)n;
                n >>>= 8;
            }
        }
        return "0x" + toHexString(md5);
    }

    private String toHexString(byte[] b)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++)
        {
            sb.append(String.format("%02X", b[i] & 0xFF));
        }
        return sb.toString();
    }
}