package ru.gorbunov.test.post.my;

import ru.gorbunov.test.post.stepic.MailMessage;
import ru.gorbunov.test.post.stepic.MailService;
import ru.gorbunov.test.post.stepic.Sendable;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Spy implements MailService {

    public static final String AUSTIN_POWERS = "Austin Powers";

    private Logger logger;

    public Spy(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        if (!(mail instanceof MailMessage)) {
            return mail;
        }
        MailMessage mailMessage = (MailMessage) mail;
        if (mailMessage.getTo().equals(AUSTIN_POWERS) || mailMessage.getFrom().equals(AUSTIN_POWERS)) {
            logger.log(Level.WARNING, "Detected target mail correspondence: from {0} to {1} \"{2}\"",
                    new Object[] {mailMessage.getFrom(), mailMessage.getTo(), mailMessage.getMessage()});
        } else {
            logger.log(Level.INFO, "Usual correspondence: from {0} to {1}",
                    new Object[] {mailMessage.getFrom(), mailMessage.getTo()});
        }
        return mail;
    }
}
