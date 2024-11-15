package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImplementation implements DeathNote{

    private Map<String, Death> deaths = new HashMap<>();
    private String latestName;
    
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
        private static final String DEFAULT_DIED = "heart attack";
        private static final int TIME_TO_WRITE_CAUSE = 40;
        private static final int TIME_TO_WRITE_DETAILS = 6040;
        private String cause;
        private String details;
        private long deathTime;

        public Death(){
            this.cause = DEFAULT_DIED;
            this.details = "";
            this.deathTime = System.currentTimeMillis();
        }

        public boolean setCause(final String cause) {
            if(System.currentTimeMillis() < deathTime + TIME_TO_WRITE_CAUSE){
                this.cause = cause;
                return true;
            } else{
                return false;
            }
        }
    
        public boolean setDetails(final String details) {
            if(System.currentTimeMillis() < deathTime + TIME_TO_WRITE_DETAILS){
                this.details = details;
                return true;
            } else{
                return false;
            }
        }

        public String getCause(){
            return this.cause;
        }

        public String getDetails(){
            return this.details;
        }
    }
}