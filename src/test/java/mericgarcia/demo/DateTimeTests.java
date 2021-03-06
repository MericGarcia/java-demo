package mericgarcia.demo;

import org.junit.Assert;
import mericgarcia.demo.model.*;
import org.junit.Test;

import java.time.*;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.time.temporal.*;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


/**
 * Created by mericgarcia on 27/01/15.
 */
public class DateTimeTests extends DemoTests{

    @Test
    public void instants() throws Exception{

        //Duration = duree entre deux instants
        Instant startOfTest = Instant.now();

        Thread.sleep(1000L);

        Instant endOfTest = Instant.now();

        out("Duree du test : " + Duration.between(startOfTest,endOfTest).toNanos() + " ns");

        Assert.assertTrue(!Duration.between(startOfTest,endOfTest)
                                    .minus(1, ChronoUnit.SECONDS)
                                    .isNegative());

    }

    @Test
    public void localDateTime() throws Exception {

        //Init
        Temporal depart = LocalTime.of(7, 30); // mon train pour venir
        Temporal secondTime = LocalTime.parse("10:15:30");

        //Operation
        Temporal arrivee = depart.plus(30, ChronoUnit.MINUTES);

        //Duree
        Duration trajet = Duration.between(depart,arrivee);

        out("Trajet : " + trajet.toMinutes() + " minutes");
        Assert.assertEquals(trajet, Duration.of(30, ChronoUnit.MINUTES));


        //Periode
        LocalDate date = LocalDate.of(2012, Month.DECEMBER, 12);
        LocalDate now = LocalDate.now();

        Period period = Period.between(now,date);
        out("Periode : " + period.toTotalMonths() + " months");
        Assert.assertTrue(period.isNegative());

        //Auter Periode
        LocalDate newDate = date.minusYears(2);

        Period secondPeriod = Period.between(newDate,date);
        out("Second Periode : " + secondPeriod.toTotalMonths() + " months");
        Assert.assertEquals(24, secondPeriod.toTotalMonths());
        out("");


    }

    @Test
    public void temporalAdjusterAndZone() throws Exception {

        LocalDateTime date = LocalDateTime.now();

        //Temporal adjuster
        Temporal premierJeudiDansTroisMois = TemporalAdjusters.firstInMonth(DayOfWeek.THURSDAY)
                                                                .adjustInto(date.plusMonths(3));

        out(
                DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.FULL)
                        .format(premierJeudiDansTroisMois)
        );


        //Zoned date time
        ZonedDateTime alaska = ZonedDateTime.now(ZoneId.of("America/Anchorage"));
         out(
                DateTimeFormatter
                        .ofLocalizedDateTime(FormatStyle.FULL)
                        .format(alaska)
        );

        ZonedDateTime tokyo = alaska.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
        out(
                DateTimeFormatter
                        .ofLocalizedDateTime(FormatStyle.FULL)
                        .format(tokyo)
        );

    }

}


