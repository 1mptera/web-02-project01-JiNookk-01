package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvitationTest {
    @Test
    void creation() {
        Invitation invitation = new Invitation(1);

        assertEquals(1, new Invitation(1).userId());
        assertEquals(2, new Invitation(2).userId());

        assertFalse(new Invitation(1).checked());
    }

    @Test
    void setChecked() {
        Invitation invitation = new Invitation(1);

        invitation.setChecked(true);

        assertTrue(invitation.checked());

        invitation.setChecked(false);

        assertFalse(invitation.checked());
    }
}