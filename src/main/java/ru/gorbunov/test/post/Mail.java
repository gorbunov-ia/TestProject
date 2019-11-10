package ru.gorbunov.test.post;

import ru.gorbunov.test.post.my.*;
import ru.gorbunov.test.post.stepic.AbstractSendable;
import ru.gorbunov.test.post.stepic.MailMessage;
import ru.gorbunov.test.post.stepic.MailPackage;
import ru.gorbunov.test.post.stepic.MailService;
import ru.gorbunov.test.post.stepic.Package;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Mail {

    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("main");

        Inspector inspector = new Inspector();
        Spy spy = new Spy(logger);
        Thief thief = new Thief(100);
        MailService variousWorkers[] = {
                //spy,
                thief//,
                //inspector
        };
        UntrustworthyMailWorker worker = new UntrustworthyMailWorker(variousWorkers);

        AbstractSendable correspondence[] = {
                new MailMessage("dog", "cat", "Hi!"),
                new MailMessage("cat", "dog", "Hi! What do you want?"),
                new MailMessage("cat", AUSTIN_POWERS, "Dog is writing me again. Can you do something?"),
                new MailMessage(AUSTIN_POWERS, "cat", "I'll do my best."),
                new MailPackage(AUSTIN_POWERS, "dog", new Package("bomb", 300)),
                new MailMessage("dog", AUSTIN_POWERS, "Why do you send me stones?"),
                new MailPackage(AUSTIN_POWERS, "dog", new Package("banned substance", 99)),
                new MailPackage(AUSTIN_POWERS, "dog", new Package("tiny bomb", 90)),
                new MailMessage(AUSTIN_POWERS, "cat", "Done."),
        };
        for (AbstractSendable sendable : correspondence) {
            try {
                worker.processMail(sendable);
            } catch (StolenPackageException e) {
                logger.log(Level.WARNING, "Inspector found stolen package: " + e);
            } catch (IllegalPackageException e) {
                logger.log(Level.WARNING, "Inspector found illegal package: " + e);
            }
        }
    }
}
