package model.Decorator;

import model.Artikel;
import model.Verkoop;

import java.util.ArrayList;

/**
 * @author Rafael Polfliet
 */

public abstract class BillDecorator implements BillPrinter{
    private BillPrinter billPrinter;

    public BillDecorator(BillPrinter billPrinter) {
        this.billPrinter = billPrinter;
    }

    public void print(Verkoop verkoop){
        billPrinter.print(verkoop);
    }
}
