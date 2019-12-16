package model.Decorator;

import model.Artikel;
import model.Verkoop;

import java.util.ArrayList;
/**
 * @auther Rafael Polfliet
 */

public class FooterGeneral extends  BillDecorator{
    String footer = "Dank voor je aankopen\n";

    public FooterGeneral(BillPrinter billPrinter) {
        super(billPrinter);
    }

    @Override
    public void print(Verkoop verkoop) {
        super.print(verkoop);
        System.out.println(footer);
    }
}
