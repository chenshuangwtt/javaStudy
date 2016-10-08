package com.cs.test.week7.filesearch;
/**
 * Basic class for all filters
 */
public abstract class AbstractFileFilter {
    private int priority;
    private String param;
    private AbstractFileFilter next;

    public AbstractFileFilter(int priority, String param) {
        this.priority = priority;
        this.param = param;
    }

    public void setNext(AbstractFileFilter next) {
        this.next = next;
    }

    /**
     * The method for run with the necessary level of a filtration.
     *
     * @param level, parameter for level of a filtration.
     */
    public void toProcessFilter(int level) {
        if (level <= priority) {
            filtrationByParam(param);
        }
        if (next != null) {
            next.toProcessFilter(level);
        }
    }

    /**
     * The method for send of parameters to filter.
     *
     * @param param the string, parameter for a filtration.
     */
    abstract void filtrationByParam(String param);
}