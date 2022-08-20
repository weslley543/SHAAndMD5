package site.SHA512;
import site.Algorithm.Algorithm;

public class Sha512 implements Algorithm {
    private byte[] input;
    private Sha512Logic Sha512LogicImpl = null;
    private static final SHAConstants SHAConstantsImpl = new SHAConstants();

    public Sha512 (byte[] input) {
        this.input = input;
        this.Sha512LogicImpl = new Sha512Logic();
    }
    @Override
    public String encrypt() {
        byte[] parsedInput = this.Sha512LogicImpl.pad(this.input);
        long [][] blocks = this.Sha512LogicImpl.toBlocks(parsedInput);

        long [][]W = this.Sha512LogicImpl.Message(blocks);

        long[] buffer = this.SHAConstantsImpl.IV.clone();

        for(int i=0; i< blocks.length; i++) {
            long a = buffer[0];
            long b = buffer[1];
            long c = buffer[2];
            long d = buffer[3];
            long e = buffer[4];
            long f = buffer[5];
            long g = buffer[6];
            long h = buffer[7];

            // Rodada de 80 vezes

            for(int j =0 ; j<80 ; j ++) {
                long t1 = h + this.Sha512LogicImpl.Sigma1(e) + this.Sha512LogicImpl.Ch(e, f, g) + this.SHAConstantsImpl.K[j] + W[i][j];
                long t2 = this.Sha512LogicImpl.Sigma0(a) + this.Sha512LogicImpl.Maj(a, b, c);
                h = g;
                g = f;
                f = e;
                e = d + t1;
                d = c;
                c = b;
                b = a;
                a = t1 + t2;
            }
            buffer[0] = a + buffer[0];
            buffer[1] = b + buffer[1];
            buffer[2] = c + buffer[2];
            buffer[3] = d + buffer[3];
            buffer[4] = e + buffer[4];
            buffer[5] = f + buffer[5];
            buffer[6] = g + buffer[6];
            buffer[7] = h + buffer[7];

        }
        String result = "";
        for (int i = 0; i < 8; i++) {
            result += String.format("%016x", buffer[i]);
        }

        return result;

    }
}
