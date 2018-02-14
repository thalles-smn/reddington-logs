package com.smn.restlog.logger;

import io.sentry.Sentry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;

@Component
public class Logger {

    @Value("${sentry.dsn}")
    private String sentryDsn;

    @Value("${environment}")
    private String environment;

    @PostConstruct
    public void init() {
        if (environment.equals("production")) {
            Sentry.init(MessageFormat.format("{0}?environment={1}", this.sentryDsn, environment));
        }
    }

    public void capture(Exception e) {
        if (environment.equals("production")) {
            Sentry.capture(e);
        }
    }

}
