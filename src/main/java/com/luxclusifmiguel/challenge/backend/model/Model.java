package com.luxclusifmiguel.challenge.backend.model;

/**
 * Common interface for a model, providing methods to get and set id.
 */
public interface Model {

    /**
     *  Gets the model id
     *
     * @return the model id
     */
    Integer getId();

    /**
     * sets the model id;
     *
     * @param id the id to set
     */
    void setId(Integer id);
}
