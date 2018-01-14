package ru.gorbunov.test.post.my;

import ru.gorbunov.test.post.stepic.MailService;
import ru.gorbunov.test.post.stepic.RealMailService;
import ru.gorbunov.test.post.stepic.Sendable;

public class UntrustworthyMailWorker implements MailService {

    private final RealMailService realMailService = new RealMailService();

    private final MailService[] services;

    public UntrustworthyMailWorker(MailService[] services) {
        this.services = services;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        for (MailService service : services) {
            mail = service.processMail(mail);
        }
        return realMailService.processMail(mail);
    }

    public RealMailService getRealMailService() {
        return realMailService;
    }
}
