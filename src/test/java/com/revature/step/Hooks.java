package com.revature.step;

import com.revature.Setup;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void beforeScenario(){
        Setup.resetTestDatabase();
    }

}
