package model.Decorator;

import model.Artikel;
import model.Verkoop;

import java.util.ArrayList;
/**
 * @auther Rafael Polfliet
 */

public class HeaderGeneral extends  BillDecorator {
    private String header;
    public HeaderGeneral(String header,BillPrinter billPrinter) {
        super(billPrinter);
        this.header = header + "\n";
    }



    @Override
    public void print(Verkoop verkoop) {
        System.out.println(header);
        super.print(verkoop);
    }
}
