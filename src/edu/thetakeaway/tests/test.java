package edu.thetakeaway.tests;

import com.google.zxing.WriterException;
import edu.thetakeaway.entities.Reservation;
import edu.thetakeaway.services.RestaurantService;
import edu.thetakeaway.services.TableService;
import edu.thetakeaway.utils.Charts;
import edu.thetakeaway.utils.MailService;
import edu.thetakeaway.utils.PaiementService;
import edu.thetakeaway.utils.QRCodeService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class test {

    public static void main(String[] args) {
        try {
            Charts xx = new Charts("a", "v");
        } catch (Exception ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
