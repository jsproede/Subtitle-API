package de.jenssproede.subapi.service;

public abstract class AbstractService implements IService {

    protected String token;

    protected String series;
    protected String season;
    protected String episode;

    @Override
    public void register(String token) {
        this.token = token;
    }

    /**
     * Needed for ServiceLoader
     */
    public AbstractService() {}

}
