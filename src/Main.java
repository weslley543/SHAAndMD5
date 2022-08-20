public class Main {
    public static void main(String[] args) throws Exception {
        String encripted = "";
        if(args.length < 2) {
            throw new Exception("Invalid number of arguments, arguments must be have two arguments algorithm and word");
        }

        if(args[0].equals("SHA512")) {
            Sha512 algorithm = new Sha512(args[1]);
            encripted = algorithm.encrypt();
        }

        System.out.print(encripted);
    }
}