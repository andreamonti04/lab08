package it.unibo.deathnote.impl;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImplementation implements DeathNote{

    public static final String DEFAULT_DEATH_CAUSE = "heart attack";

    private Map<String, Death> deaths = new HashMap<>();
        private String latestName;    
        public static final int MS_TO_WRITE_DEATH_CAUSE = 40;
        public static final int MS_TO_WRITE_DEATH_DETAILS = 6040;
    
        public DeathNoteImplementation(){
            this.deaths = new HashMap<>();
        this.latestName = "";
    }

    @Override
    public String getRule(final int ruleNumber) {
        if(ruleNumber < 1 || ruleNumber > RULES.size()){
            throw new IllegalArgumentException("It is an incorrect number");
        }
        return RULES.get(ruleNumber - 1);
    }

    @Override
    public void writeName(final String name) {
        if(name == null){
            throw new NullPointerException("The name cannot be null");
        }
        latestName = name;
        deaths.put(latestName, new Death());
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if (deaths.isEmpty() || cause == null) {
            throw new IllegalStateException("There is no name written in this DeathNote or the cause is null");
        }
        return deaths.get(latestName).setCause(cause);
    }

    @Override
    public boolean writeDetails(String details) {
        if(deaths.isEmpty() || details == null){
            throw new IllegalStateException("The details cannot be null or there are no name in deathNote");
        }
        return deaths.get(latestName).setDetails(details);
    }

    @Override
    public String getDeathCause(String name) {
        if(!deaths.containsKey(name)){
            throw new IllegalArgumentException("the name is not written in this DeathNote");
        }
        return deaths.get(name).getCause();
    }

    @Override
    public String getDeathDetails(String name) {
        if(!deaths.containsKey(name)){
            throw new IllegalArgumentException("the name is not written in this DeathNote");
        }
        return deaths.get(name).getDetails();
    }

    @Override
    public boolean isNameWritten(String name) {
        return deaths.containsKey(name);
    }

    private class Death {

        private String cause = DEFAULT_DEATH_CAUSE;
    
        private String details = "";
    
        public boolean setCause(final String cause) {
          this.cause = cause;
          return true;
        }
    
        public boolean setDetails(final String details) {
          this.details = details;
          return true;
        }

        public String getCause(){
            return this.cause;
        }

        public String getDetails(){
            return this.details;
        }
    }
}