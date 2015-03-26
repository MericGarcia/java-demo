package mericgarcia.demo;

import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by mericgarcia on 23/03/15.
 */
public class FilesTests extends DemoTests {

    @Test
    public void fileDemo() throws Exception{

        // copier un fichier

        Files.copy(Paths.get("src/test/resources","test.csv"),
                   Paths.get("src/test/resources","test_copy.csv"),
                   StandardCopyOption.REPLACE_EXISTING);



        //On peut recuperer un stream sur les lignes ...

        Stream<String> lines = Files.lines(Paths.get("src/test/resources","test_copy.csv"));

        // ... et afficher toutes les lignes bien formatees distinctes

        lines.filter(
                        line -> line.chars()
                                    .filter( character -> character == ';')
                                    .count() == 3
                    )
                .distinct()
                .forEach(line -> out(line));



        //On peut recuperer un second stream sur les lignes ...

        Stream<String> lines2 = Files.lines(Paths.get("src/test/resources","test_copy.csv"));

        // ... et compter toutes les lignes bien formatees distinctes

        long number = lines2.filter(
                                    line -> line.chars().filter( character -> character == ';')
                                            .count() == 3)
                            .distinct()
                            .count();

        out("");
        out("Nombre de lignes distinctes bien formatees : " + number);
        Assert.assertEquals(5L,number);

        //delete
        Files.delete(Paths.get("src/test/resources","test_copy.csv"));

    }

}
