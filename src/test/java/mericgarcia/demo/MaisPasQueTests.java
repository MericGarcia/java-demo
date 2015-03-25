package mericgarcia.demo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

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



        //On peut recuperer un stream sur les lignes ...
        Stream<String> lines = Files.lines(Paths.get("src/test/resources","./test_copy.csv"));

        // ... et afficher toutes les lignes bien formatees distinctes
        lines.filter(
                             (s) -> s.chars()
                                .filter( c -> c == ';')
                                .count() == 3
                 )
                .distinct()
                .forEach(s -> out(s));



        //On peut recuperer un second stream sur les lignes ...
        Stream<String> lines2 = Files.lines(Paths.get("src/test/resources","./test_copy.csv"));

        // ... et compter toutes les lignes bien formatees distinctes
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

        //les concurentHashMap
        ConcurrentHashMap<Integer,List<String>> chiffresMap =
        (ConcurrentHashMap<Integer, List<String>>) Arrays.asList("Un","Deux","Trois","Quatre","Cinq","Six","Sept","Huit","Neuf")
        			.stream()
        			.collect(Collectors.groupingByConcurrent(String::length));

        
        // effectuer une recherche dans une map
        for(int i=0;i<=10;i++){
        
	        chiffresMap.forEachEntry(2,System.out::println);
	        out(" ESSAI " + i +" : Y a t'il des mots de plus de deux lettres dont le nombre de lettre est unique ? Si oui donnez un exemple.  " + 
	        chiffresMap.search(1,
	        	(k,v) -> {
		        		if(k > 2 && v.size() == 1){
		        			return v ;
		        		}
		        		else{
		        			return  null; 
		        		}
	        		}
	        ));
	        
        }
        
        
        // optionnal on list
        out("");
        out("Optionnal on List ");
        out("");
        List<String> chiffresList = new ArrayList();
        chiffresList.addAll(Arrays.asList("Un","Deux","Trois","Quatre","Cinq","Six","Sept","Huit","Neuf"));
        List<String> chiffresListWithQuatri = new ArrayList();
        chiffresListWithQuatri.addAll(Arrays.asList("Un","Deux","Trois","Quatre","Quatri","Cinq","Six","Sept","Huit","Neuf"));
        List<String> emptyChiffresList = Collections.emptyList();
 
        out("max de la list de chiffres : " + maxList(chiffresList));
        out("max de la list de chiffres avec quatri : " + maxList(chiffresListWithQuatri));
        out("max emptyChiffresList: " + maxList(emptyChiffresList));
        
        
    }
    
    public String maxList(List<String> list){
    	return list.stream().max(Comparator.comparing(String::length).
    			thenComparing(s ->
    			s.charAt(s.length() -1)
    					)
    	).orElse("Pas de Max !");
    }
}
