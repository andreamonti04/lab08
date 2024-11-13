package it.unibo.deathnote.impl;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

class DeathNoteImplementation implements DeathNote{

    public static final String DEFAULT_DEATH_CAUSE = "heart attack";

    private class Death {

        private String cause = DEFAULT_DEATH_CAUSE;
    
        private String details = "";
    
        public void setCause(final String cause) {
          this.cause = cause;
        }
    
        public void setDetails(final String details) {
          this.details = details;
        }
    }

    private final Map<String, Death> deaths = new HashMap<>();
    private String latestName;    
    public static final int MS_TO_WRITE_DEATH_CAUSE = 40;
    public static final int MS_TO_WRITE_DEATH_DETAILS = 6040;

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
        if (this.latestName == null || cause == null) {
            throw new IllegalStateException("There is no name written in this DeathNote or the cause is null");
        }
        final long timeElapsed = timer.partial(); // keep the timer running because the details can still be added.
        if (timeElapsed > MS_TO_WRITE_DEATH_CAUSE) {
            return false;
        }
        deaths.get(latestName).setCause(cause);
        return true;
    }

    @Override
    public boolean writeDetails(String details) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDetails'");
    }

    @Override
    public String getDeathCause(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathCause'");
    }

    @Override
    public String getDeathDetails(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathDetails'");
    }

    @Override
    public boolean isNameWritten(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isNameWritten'");
    }

}