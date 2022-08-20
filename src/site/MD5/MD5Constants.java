package site.MD5;

public class MD5Constants {
    public static final int[] r = {
            7, 12, 17, 22,
            5,  9, 14, 20,
            4, 11, 16, 23,
            6, 10, 15, 21
    };
    public static final int INIT_A = 0x67452301;
    public static final int INIT_B = (int)0xEFCDAB89L;
    public static final int INIT_C = (int)0x98BADCFEL;
    public static final int INIT_D = 0x10325476;

    public int [] getR() {
        return this.r;
    }

    public int [] getK() {
      int [] k = new int[64];
      for (int i = 0 ; i<k.length; i++) {

          k[i] = (int)(long)((1L << 32) * Math.abs(Math.sin(i + 1)));
      }
      return k;
    }
}
