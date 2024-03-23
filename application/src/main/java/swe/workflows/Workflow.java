package swe.workflows;

public interface Workflow<C, R> {
    R execute(C command);
}
