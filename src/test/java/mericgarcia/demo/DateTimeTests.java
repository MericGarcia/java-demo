package mericgarcia.demo;

import org.junit.Assert;
import mericgarcia.demo.model.*;
import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.List;


/**
 * Created by mericgarcia on 27/01/15.
 */
public class DateTimeTests extends DemoTests{

    @Test
    public void instants() throws Exception{

        methodeHead("instants()");
        out("");

        //Duration = duree entre deux instants
        Instant startOfTest = Instant.now();

        Thread.sleep(1000L);

        Instant endOfTest = Instant.now();

        out("Duree du test : " + Duration.between(startOfTest,endOfTest).toNanos() + " ns");
        Assert.assertTrue(!Duration.between(startOfTest,endOfTest).minus(1, ChronoUnit.SECONDS).isNegative());

    }

    @Test
    public void localDateTime() throws Exception {

        methodeHead("localDateTime()");
        out("");

        LocalDate now = LocalDate.now();
        LocalDate date = LocalDate.of(2012, Month.DECEMBER, 12);

        Temporal depart = LocalTime.of(7, 30); // mon train pour venir
        Temporal secondTime = LocalTime.parse("10:15:30");

        //Operation
        Temporal arrivee = depart.plus(30, ChronoUnit.MINUTES);
        LocalDate newDate = date.minusYears(2);

        //Duree
        Duration trajet = Duration.between(depart,arrivee);

        out("Trajet : " + trajet.toMinutes());
        Assert.assertEquals(trajet, Duration.of(30, ChronoUnit.MINUTES));


        //Periode
        Period period = Period.between(now,date);
        out("Periode : " + period.toTotalMonths());
        Assert.assertTrue(period.isNegative());

        Period secondPeriod = Period.between(newDate,date);
        out("Second Periode : " + secondPeriod.toTotalMonths());
        Assert.assertEquals(24, secondPeriod.toTotalMonths());
        out("");


    }


}


