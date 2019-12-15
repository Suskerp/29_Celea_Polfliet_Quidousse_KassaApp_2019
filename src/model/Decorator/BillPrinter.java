package model.Decorator;

import model.Artikel;
import model.Verkoop;

import java.util.ArrayList;

public interface BillPrinter {
    void print(Verkoop verkoop);
}
