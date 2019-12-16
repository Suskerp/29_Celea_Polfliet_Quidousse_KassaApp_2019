package model.Decorator;

import model.Verkoop;
/**
 * @auther Rafael Polfliet
 */

public class FooterExclKorting extends BillDecorator {

    public FooterExclKorting(BillPrinter billPrinter) {
        super(billPrinter);
    }

    @Override
    public void print(Verkoop verkoop) {
        super.print(verkoop);
        System.out.println("Totale prijs zonder korting: " + String.format("%.2f", verkoop.getSum()) +" Euro\nTotale korting: " + String.format("%.2f", verkoop.getKorting())+" Euro\n");
    }

}
