package ru.gorbunov.test.post.my;

import ru.gorbunov.test.post.stepic.MailPackage;
import ru.gorbunov.test.post.stepic.MailService;
import ru.gorbunov.test.post.stepic.Package;
import ru.gorbunov.test.post.stepic.Sendable;

public class Thief implements MailService {

    private int priceLimit;
    private int stolenValue;


    public Thief(int priceLimit) {
        this.priceLimit = priceLimit;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailPackage && ((MailPackage) mail).getContent().getPrice() >= priceLimit) {
            MailPackage mailPackage = ((MailPackage) mail);
            Package targetPackage = mailPackage.getContent();

            stolenValue += targetPackage.getPrice();
            return new MailPackage(mailPackage.getFrom(), mailPackage.getTo(),
                    new Package("stones instead of " + targetPackage.getContent(), 0));
        }
        return  mail;
    }

    public int getStolenValue() {
        return stolenValue;
    }
}
