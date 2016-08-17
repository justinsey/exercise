package com.lunatech.configuration.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author Justin Seyvecou
 */
public interface Bootstrap extends ApplicationListener<ContextRefreshedEvent> {

}
