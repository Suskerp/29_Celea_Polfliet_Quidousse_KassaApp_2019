package model.Decorator;

import com.sun.org.apache.xpath.internal.operations.Bool;
import model.Discount.*;

import java.util.HashMap;
/**
 * @auther Rafael Polfliet
 */

public class BillFactory {
    private static BillFactory INSTANCE;

    private BillFactory(){}

    public BillPrinter createBill(HashMap<BillEnum, Boolean> decorators,String general){
        BillPrinter billPrinter = new Bill();

        if (decorators.get(BillEnum.FOOTER_KORTING)){
           billPrinter = new FooterExclKorting(billPrinter);
         }
        if (decorators.get(BillEnum.FOOTER_BTW)){
            billPrinter = new FooterExlBTW(billPrinter);
        }
        if (decorators.get(BillEnum.FOOTER_GENERAL)){
            billPrinter = new FooterGeneral(billPrinter);
        }
        if (decorators.get(BillEnum.HEADER_DATETIME)){
            billPrinter = new HeaderDateTime(billPrinter);
        }
        if (decorators.get(BillEnum.HEADER_GENERAL)){
            billPrinter = new HeaderGeneral(general,billPrinter);
        }


        return billPrinter;
    }

    public static BillFactory getInstance(){
        if (INSTANCE == null){
            INSTANCE = new BillFactory();
        }
        return INSTANCE;
    }
}
