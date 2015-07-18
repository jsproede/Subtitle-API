package de.jenssproede.subapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class Services {

    private static Services instance;

    private ServiceLoader<IService> loader;

    private Services() {
        loader = ServiceLoader.load(IService.class);
    }

    public static Services getInstance() {
        if (instance == null) {
            instance = new Services();
        }
        return instance;
    }

    public IService getService(String service) {
        for (IService iService : loader) {
            if (iService.getClass().getSimpleName().equalsIgnoreCase(service)) {
                return iService;
            }
        }

        return null;
    }

    public List<IService> getAvailableServices() {
        List<IService> services = new ArrayList<>();
        loader.iterator().forEachRemaining(services::add);
        return services;
    }
}
