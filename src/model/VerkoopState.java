package model;

/**
 * @author Jef Quidousse
 */

public interface VerkoopState {

    default void scan(){throw new StateException();}
    default void afgesloten(){throw new StateException();}
    default void betaald(boolean genoegGeld){throw new StateException();}
    default void annuleer(boolean genoegGeld){throw new StateException();}
}
