package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImplementation;
import static it.unibo.deathnote.api.DeathNote.RULES;

class TestDeathNote {

    private DeathNote deathnote;
    private static final String PERSON1 = "Mario Rossi";
    private static final String PERSON2 = "Filippo Bianchi";

    @BeforeEach
    void init(){
        deathnote = new DeathNoteImplementation();
    }

    @Test
    void testIllegalNumber(){
        for (final int index : List.of(0, -1, RULES.size() + 1)) {
            try {
                deathnote.getRule(index);
            } catch (IllegalArgumentException e) {
                assertNotNull(e.getMessage());
                assertFalse(e.getMessage().isBlank());
                assertFalse(e.getMessage().isEmpty());
            }
        }
    }

    @Test
    void testRules(){
        for(String rules : RULES){
            assertNotNull(true);
            assertFalse(rules.isBlank());
        }
    }

    @Test
    void testHumanDies(){
        assertFalse(deathnote.isNameWritten(PERSON1));
        deathnote.writeName(PERSON1);
        assertTrue(deathnote.isNameWritten(PERSON1));
        assertTrue(deathnote.isNameWritten(PERSON2));
        assertFalse(deathnote.isNameWritten(""));
    }

    @Test
    void testCheckDeathInTime() throws InterruptedException{
        try{
            deathnote.writeDeathCause("seizure");
            fail("");
        }catch (IllegalStateException e){
            assertFalse(e.getMessage().isBlank());
        }
        deathnote.writeName(PERSON1);
        assertEquals("heart attack", deathnote.getDeathCause(PERSON1));
        deathnote.writeName(PERSON2);
        assertTrue(deathnote.writeDeathCause("karting accident"));
        assertEquals("karting accident", deathnote.getDeathCause(PERSON2));
        Thread.sleep(100);
        assertFalse(deathnote.writeDeathCause("cancer"));
        assertEquals("karting accident", deathnote.getDeathCause(PERSON2));
    }

    @Test
    void testDetails() throws InterruptedException{
        deathnote.writeName(PERSON1);
        assertTrue(deathnote.getDeathDetails(PERSON1).isBlank());
        assertTrue(deathnote.writeDetails("ran for too long"));
        assertEquals("ran for too long", deathnote.getDeathDetails(PERSON1));
        deathnote.writeDetails(PERSON2);
        Thread.sleep(6100);
        assertFalse(deathnote.writeDetails("he didn't care"));
        assertEquals("", deathnote.getDeathDetails(PERSON2));
    }
}