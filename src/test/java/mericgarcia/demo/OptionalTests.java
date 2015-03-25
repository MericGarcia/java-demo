package mericgarcia.demo;

import mericgarcia.demo.model.Boulanger;
import mericgarcia.demo.model.Interpreter;
import mericgarcia.demo.model.Traducteur;
import mericgarcia.demo.model.Worker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;


/**
 * Created by mericgarcia on 27/01/15.
 */
public class OptionalTests extends DemoTests{

	List<Worker> workers = initWorkerList();
	
    
    private List<Worker> initWorkerList(){
    	Worker boulanger = new Boulanger("Aimable Castanier");
	    Worker traducteurFrancais = new Traducteur("Aurelien LeJeune");
	    Worker traducteurAnglais = new Interpreter("James Henry");
	    Worker inconnu = new Worker() {
    	        @Override
    	        public String name() {
    	            return "Un inconnu";
    	        }
    	    };

	    return Arrays.asList(boulanger,traducteurFrancais,traducteurAnglais,inconnu);
    }


    @Test
    public void optionalTest() throws Exception {

    	Optional<Worker> optTraducteur = getOptionalWorkerByName("Aurelien LeJeune");
    	Optional<Worker> optUnknown = getOptionalWorkerByName("Julien Houeix");
 	
    	
    	Optional<Worker> boulanger = Optional.ofNullable(getWorkerByName("Aimable Castanier"));
    	Optional<Worker> unknown = Optional.ofNullable(getWorkerByName("Julien Toto"));
    	
    	List<Optional<Worker>> optWorkers = Arrays.asList(optTraducteur,optUnknown,boulanger,unknown);
    	
    	
    	out("forEachPresent :");
    	out("");
    	optWorkers.forEach(opt -> opt.ifPresent(DemoTests::out));
    	out("");
    	
    	
    	 Worker inconnu = new Worker() {
 	        @Override
 	        public String name() {
 	            return "Un inconnu";
 	        }
 	    };
 	   
 	    out("orElse : ");
 	    out("");
    	optWorkers.forEach(opt -> out(opt.orElse(inconnu)));
    	
    	
    	
    	
    	
    }


	private Worker getWorkerByName(String name) {
		for(Worker worker : workers){
			if(name.equals(worker.name())){
				return worker;
			}
		}
		return null;
	}
	
	private Optional<Worker> getOptionalWorkerByName(String name) {
		for(Worker worker : workers){
			if(name.equals(worker.name())){
				return Optional.of(worker);
			}
		}
		return Optional.empty();
	}


}
