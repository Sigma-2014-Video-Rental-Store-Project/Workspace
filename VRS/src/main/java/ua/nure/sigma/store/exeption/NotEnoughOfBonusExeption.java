package ua.nure.sigma.store.exeption;

/**
 * Created by vlad on 13.10.14.
 */
public class NotEnoughOfBonusExeption extends Exception {
    public NotEnoughOfBonusExeption() {
        super("Customer has no enough of bonus");
    }
}
