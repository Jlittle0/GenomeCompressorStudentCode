public class TestingSpace {

    public TestingSpace() {

    }
        public static void main(String[] args) {

            String test = Integer.toBinaryString(12502);
            String result = "";
            for (int i = 0; i < 32; i++) {
                if (i < 32 - test.length())
                    result += "0";
                else
                    result += test.charAt(i - 32 + test.length());
            }
            System.out.println(result + " | " + result.length());
            System.out.println(Integer.parseInt(result, 2));
            for (int i = 0; i < result.length(); i++)
                System.out.println((int)result.charAt(i) - 48);

        }
}
