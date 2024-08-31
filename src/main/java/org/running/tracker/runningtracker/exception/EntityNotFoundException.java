package org.running.tracker.runningtracker.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entityName, Long id) {
        super("No %s with id:%d found".formatted(entityName, id));
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}