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
public class CollectionsTests extends DemoTests {

    @Test
    public void listDemo(){

        //Que peut-on faire sur les listes ?

        List<String> chiffres = new ArrayList<String>();
        chiffres.addAll(Arrays.asList("Un", "Deux", "Trois", "Quatre", "Cinq", "Six", "Sept", "Huit", "Neuf"));

        //remove avec condition
        chiffres.removeIf( s -> s.length() < 3);

        //mapping
        chiffres.replaceAll( s -> s.toUpperCase());

        //tri avec comparator
        chiffres.sort(Comparator.comparing( s -> s.length()));

        //le foreach avec lambda !
        chiffres.forEach(System.out::println);

    }

    @Test
    public void mapDemo(){

        //les concurentHashMap
        ConcurrentHashMap<Integer,List<String>> chiffresMap =

                (ConcurrentHashMap<Integer, List<String>>) Arrays
                                                            .asList("Un","Deux","Trois","Quatre","Cinq","Six","Sept","Huit","Neuf")
        			                                        .stream()
        			                                        .collect(Collectors.groupingByConcurrent(String::length));

        
        // effectuer une recherche dans une map

        for(int i=0;i<=10;i++){
        
	        chiffresMap.forEachEntry(2,System.out::println);

	        out(" ESSAI " + i +" : " +
                    "Y a t'il des mots de plus de deux lettres dont le nombre de lettre est unique ? " +
                    "Si oui donnez un exemple.  " +
                    chiffresMap.search(1, (/*nombre de lettre du mot*/key , /*liste de tels mots*/ value) ->

                                                    {
		        		                                if(key > 2 && value.size() == 1){ // liste de mots de plus de deux lettre
                                                                                          // avec un seul element
		        			                                return value;

		        		                                }else{

                                                            return null;

                                                        }
	        		                                }
	                                    )
            );
	        
        }

        
    }

    @Test
    public void listAndOptionalDemo(){

        List<String> chiffresList = new ArrayList();
        chiffresList.addAll(Arrays.asList("Un","Deux","Trois","Quatre","Cinq","Six","Sept","Huit","Neuf"));

        List<String> chiffresListWithQuatri = new ArrayList();
        chiffresListWithQuatri.addAll(chiffresList);
        chiffresListWithQuatri.add("Quatri");

        List<String> emptyChiffresList = Collections.emptyList();

        out("max de la list de chiffres : " + maxList(chiffresList));
        out("max de la list de chiffres avec quatri : " + maxList(chiffresListWithQuatri));
        out("max emptyChiffresList: " + maxList(emptyChiffresList));

    }
    
    private String maxList(List<String> list){

        return list
                .stream()
                .max(Comparator.comparing(String::length) // on compare par taille
                                .thenComparing(s -> s.charAt(s.length()-1) ) // puis par la derniere lettre
    	            )
                .orElse("Pas de Max !");

    }

}
