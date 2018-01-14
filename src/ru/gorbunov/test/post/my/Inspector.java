package ru.gorbunov.test.post.my;

import ru.gorbunov.test.post.stepic.MailPackage;
import ru.gorbunov.test.post.stepic.MailService;
import ru.gorbunov.test.post.stepic.Sendable;

public class Inspector implements MailService {
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";
    @Override
    public Sendable processMail(Sendable mail) {
        if (!(mail instanceof MailPackage)) {
            return mail;
        }
        MailPackage mailPackage = (MailPackage) mail;
        String mailContent = mailPackage.getContent().getContent();
        if (mailContent.contains(WEAPONS) || mailContent.contains(BANNED_SUBSTANCE)) {
            throw new IllegalPackageException();
        }
        if (mailContent.contains("stones")) {
            throw new StolenPackageException();
        }
        return mail;
    }
}
