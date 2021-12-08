package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import ca.jrvs.practice.resources.HashJMap;
import ca.jrvs.practice.resources.JMap;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompareMapsTest {

  private final Logger logger = LoggerFactory.getLogger(CompareMapsTest.class);

  CompareMaps compareMaps;

  Map<Integer, String> m1, m2, m3, m4;
  JMap<Integer, String> jm1, jm2, jm3, jm4, jm5;

  @Before
  public void setUp() throws Exception {
    BasicConfigurator.configure();
    compareMaps = new CompareMaps();
  }

  @Test
  public void compareMapsSolution1() {

    m1 = new HashMap<>();
    m1.put(1,"One");
    m1.put(2,"Two");
    m1.put(3,"Three");

    m2 = new HashMap<>();
    m2.put(1,"One");
    m2.put(2,"Two");
    m2.put(3,"Three");

    m3 = new HashMap<>();
    m3.put(1,"One");
    m3.put(2,"Two");
    m3.put(4,"Four");

    m4 = new HashMap<>();
    m4.put(1,"One");
    m4.put(2,"Two");
    m4.put(3,"Three");
    m4.put(4,"Four");

    logger.info("m1 is equal to m2: " + (compareMaps.compareMapsSolution1(m1, m2)));
    logger.info("m1 is equal to m3: " + (compareMaps.compareMapsSolution1(m1, m3)));
    logger.info("m1 is equal to m4: " + (compareMaps.compareMapsSolution1(m1, m4)));
  }

  @Test
  public void compareMapsSolution2() {

    jm1 = new HashJMap<>();
    jm1.put(1,"One");
    jm1.put(2,"Two");
    jm1.put(3,"Three");

    jm2 = new HashJMap<>();
    jm2.put(1,"One");
    jm2.put(2,"Two");
    jm2.put(3,"Three");

    jm3 = new HashJMap<>();
    jm3.put(1,"One");
    jm3.put(2,"Two");
    jm3.put(4,"Four");

    jm4 = new HashJMap<>();
    jm4.put(1,"One");
    jm4.put(2,"Two");
    jm4.put(3,"Three");
    jm4.put(4,"Four");

    jm5 = new HashJMap<>();
    jm5.put(1,"One");
    jm5.put(3,"Three");
    jm5.put(2,"Two");

    logger.info("jm1 is equal to jm2: " + (compareMaps.compareMapsSolution2(jm1, jm2)));
    logger.info("jm1 is equal to jm3: " + (compareMaps.compareMapsSolution2(jm1, jm3)));
    logger.info("jm1 is equal to jm4: " + (compareMaps.compareMapsSolution2(jm1, jm4)));
    logger.info("jm1 is equal to jm5: " + (compareMaps.compareMapsSolution2(jm1, jm5)));
  }
}