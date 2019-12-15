package model.Decorator;

import model.Artikel;
import model.Verkoop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HeaderDateTime extends  BillDecorator {
    public HeaderDateTime(BillPrinter billPrinter) {
        super(billPrinter);
    }

    @Override
    public void print(Verkoop verkoop) {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))+"\n");
        super.print(verkoop);
    }
}
