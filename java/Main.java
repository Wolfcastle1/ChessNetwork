import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.*;
import java.io.*;
import java.net.*;
import java.util.*;

import static com.github.bhlangonijr.chesslib.move.MoveGenerator.generatePseudoLegalCaptures;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        List<Move> moves = board.legalMoves();
        System.out.println("Legal moves: " + moves);

        Graph<Integer, DefaultEdge> g1 = new DefaultDirectedGraph<>(DefaultEdge.class);
        for (Integer i = 1; i <= 64; i++) {
            g1.addVertex(i);
        }
        for (Move move : moves) {
            insertAllMoves(g1, move);
        }

        List<Move> moves2 =  generatePseudoLegalCaptures(board);
        System.out.println("Legal moves: " + moves2);
        Graph<Integer, DefaultEdge> g2 = new DefaultDirectedGraph<>(DefaultEdge.class);
        for (Integer i = 1; i <= 64; i++) {
            g2.addVertex(i);
        }
        for (Move move : moves2) {
            insertAllMoves(g2, move);
        }
    }

    public static void insertAllMoves(Graph<Integer, DefaultEdge> g, Move move) {
        String first = move.getFrom().value();
        String second = move.getTo().value();
        g.addEdge(getIndex(first), getIndex(second));
    }

    public static Integer getIndex(String str){
        char ch1 = str.charAt(0);
        Integer num1 = 1;
        switch (ch1){
            case 'a':
                num1 = 0;
            case 'b':
                num1 = 1;
            case 'c':
                num1 = 2;
            case 'd':
                num1 = 3;
            case 'e':
                num1 = 4;
            case 'f':
                num1 = 5;
            case 'g':
                num1 = 6;
            case 'h':
                num1 = 7;
        }
        Integer num2 = Integer.valueOf(str.charAt(1));
        Integer ret = (num1 * 8) + num2;
        return ret;
    }

}