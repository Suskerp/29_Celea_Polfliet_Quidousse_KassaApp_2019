package model.Decorator;

import model.Verkoop;

public class FooterExlBTW extends BillDecorator {
    public FooterExlBTW(BillPrinter billPrinter) {
        super(billPrinter);
    }

    @Override
    public void print(Verkoop verkoop) {
        super.print(verkoop);
        System.out.println("Betaalde prijs exl. BTW: " + String.format("%.2f", verkoop.getFinalSum()-(verkoop.getFinalSum() * 0.06)) +" Euro\nBTW: " + String.format("%.2f", verkoop.getSum()*0.06)+" Euro\n");
    }
}
