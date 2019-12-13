package model.States;

import model.States.StateException;

/**
 * @author Jef Quidousse
 */

public interface VerkoopState {

    default void scan(){throw new StateException();}
    default void afgesloten(){throw new StateException();}
    default void betaald(){throw new StateException();}
    default void annuleer(){throw new StateException();}
    default void hold(){throw new StateException();}
}
