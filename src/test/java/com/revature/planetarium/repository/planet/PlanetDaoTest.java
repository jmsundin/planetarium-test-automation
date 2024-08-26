package com.revature.planetarium.repository.planet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.Setup;
import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.utility.DatabaseConnector;

public class PlanetDaoTest {

    private PlanetDao planetDao;
    static String planetImage;
    private Planet validPlanet;
    private Planet existingPlanet;
    
}
