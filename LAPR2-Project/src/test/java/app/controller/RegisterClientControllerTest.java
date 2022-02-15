package app.controller;

import org.junit.Test;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class RegisterClientControllerTest {

    @Test(expected = IllegalStateException.class)
    public void ensureNonAuthorizedUserCantAccessController() {
        new RegisterClientController();
    }

}