package mericgarcia.demo;

import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by mericgarcia on 23/03/15.
 */
public class MaisPasQueTests extends DemoTests {

    @Test
    public void fileDemo() throws Exception{

        methodeHead("fileDemo()");
        out("");

        // copier un fichier
        Files.copy(Paths.get("src/test/resources","test.csv"),
                Paths.get("src/test/resources","./test_copy.csv"),
                StandardCopyOption.REPLACE_EXISTING);



        //On peut récuperer un stream sur les lignes ...
        Stream<String> lines = Files.lines(Paths.get("src/test/resources","./test_copy.csv"));

        // ... et afficher toutes les lignes bien formatées distinctes
        lines.filter(
                             (s) -> s.chars()
                                .filter( c -> c == ';')
                                .count() == 3)
                .distinct()
                .forEach(s -> out(s));



        //On peut récuperer un second stream sur les lignes ...
        Stream<String> lines2 = Files.lines(Paths.get("src/test/resources","./test_copy.csv"));

        // ... et compter toutes les lignes bien formatées distinctes
        long number = lines2.filter(
                (s) -> s.chars()
                        .filter( c -> c == ';')
                        .count() == 3)
                .distinct().count();

        Assert.assertEquals(5L,number);

        //delete
        Files.delete(Paths.get("src/test/resources","./test_copy.csv"));

    }

    @Test
    public void stringJoinerDemo(){

        methodeHead("stringJoinerDemo()");
        out("");

        StringJoiner joiner = new StringJoiner(",",""," ! ");

        joiner.add("Un");
        joiner.add("deux");
        joiner.add("trois");
        joiner.add("quatre");

        out(joiner.toString());
        Assert.assertEquals(joiner.toString(),"Un,deux,trois,quatre ! ");


    }

    @Test
    public void collectionsDemo(){

        methodeHead("collectionsDemo()");
        out("");

        List<String> chiffres = new ArrayList<String>();
        chiffres.addAll(Arrays.asList("Un", "Deux", "Trois", "Quatre", "Cinq", "Six", "Sept", "Huit", "Neuf"));

        chiffres.removeIf( s -> s.length() < 3);
        chiffres.replaceAll( s -> s.toUpperCase());
        chiffres.sort(Comparator.comparing( s -> s.length()));

        chiffres.forEach(System.out::println);

    }

    @Test
    public void collectionsDemo2(){

        methodeHead("collectionsDemo2()");
        out("");

        ConcurrentHashMap<Integer,List<String>> chiffresMap =
        Arrays.asList("Un","Deux","Trois","Quatre","Cinq","Six","Sept","Huit","Neuf").stream().collect(Collectors
                .groupingByConcurrent(String::length), List::add);

        out("  " + chiffresMap.search(2,(k,v) -> k > 2 && v.lenght() < 1));

        chiffresMap.forEachValue(System.out::println);

    }

}
