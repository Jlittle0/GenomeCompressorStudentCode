/******************************************************************************
 *  Compilation:  javac GenomeCompressor.java
 *  Execution:    java GenomeCompressor - < input.txt   (compress)
 *  Execution:    java GenomeCompressor + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *  Data files:   genomeTest.txt
 *                virus.txt
 *
 *  Compress or expand a genomic sequence using a 2-bit code.
 ******************************************************************************/

/**
 *  The {@code GenomeCompressor} class provides static methods for compressing
 *  and expanding a genomic sequence using a 2-bit code.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  @author Zach Blick
 */
public class GenomeCompressor {

    private static final char A = 0, C =1, T =2, G = 3;
    private static int BITS_PER_CHAR = 2;

    /**
     * Reads a sequence of 8-bit extended ASCII characters over the alphabet
     * { A, C, T, G } from standard input; compresses and writes the results to standard output.
     */
    public static void compress() {

        String s = BinaryStdIn.readString();
        int n = s.length();
        // Write out each character
        for (int i = 0; i < n; i++) {
           switch (s.charAt(i)) {
               case 'A':
                   BinaryStdOut.write(A, BITS_PER_CHAR);
                   break;
               case 'C':
                   BinaryStdOut.write(C, BITS_PER_CHAR);
                   break;
               case 'T':
                   BinaryStdOut.write(T, BITS_PER_CHAR);
                   break;
               case 'G':
                   BinaryStdOut.write(G, BITS_PER_CHAR);
                   break;
            }
        }
        BinaryStdOut.close();
    }

    /**
     * Reads a binary sequence from standard input; expands and writes the results to standard output.
     */
    public static void expand() {

        int count = 0;

        // Forecfully removed/ignored the last two padded bits that carried over from compression
        while (!BinaryStdIn.isEmpty() && count < 12502) {
            int c = BinaryStdIn.readChar(BITS_PER_CHAR);
            switch (c) {
                case A:
                    BinaryStdOut.write('A');
                    break;
                case C:
                    BinaryStdOut.write('C');
                    break;
                case T:
                    BinaryStdOut.write('T');
                    break;
                case G:
                    BinaryStdOut.write('G');
                    break;
                default:
                    System.out.println("Something is wrong");
            }
            count += 2;
        }

        BinaryStdOut.close();
    }


    /**
     * Main, when invoked at the command line, calls {@code compress()} if the command-line
     * argument is "-" an {@code expand()} if it is "+".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}