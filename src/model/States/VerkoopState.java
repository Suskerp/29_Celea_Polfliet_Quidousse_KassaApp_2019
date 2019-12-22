package model.States;

import model.States.StateException;

/**
 * @author Jef Quidousse
 */

public interface VerkoopState {

    default void scan(){throw new StateException("Onmogelijk deze actie uit te voeren terwijl er nog gescand wordt");}
    default void betaald(){throw new StateException("Onmogelijk deze actie uit te voeren er is reeds betaald");}
    default void afgesloten(){throw new StateException("Onmogelijk deze actie uit te voeren nadat verkoop reeds is afgesloten");}
    default void annuleer(){throw new StateException("Onmogelijk deze actie uit te voeren na annulering");}
    default void hold(){throw new StateException("Onmogelijk deze actie uit te voeren terwijl uw lijst op hold staat");}
    default void verwijder(String id){throw new StateException("Onmoglijk om nog een item te verwijderen");}
    default double korting(){throw new StateException("Onmogelijk om momenteel de korting te berekenen");}
    default double finalSum(){throw new StateException("Onmogelijk om de eindsom momenteel te berekenen");}
    default void save(){throw new StateException("Onmogelijk om de artikelen op te slaan");}
    default void scanItem(String id){throw new StateException("Onmogelijk om nu nog items te scannen");}
}
