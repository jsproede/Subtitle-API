package de.jenssproede.subapi.data;

import de.jenssproede.subapi.service.IService;

import java.util.HashMap;
import java.util.Map;

public class ServiceHolder {

    private static final Map<String, IService> services = new HashMap<>();

    public static void registerTokenWithService(String token, IService service) {
        services.put(token, service);
    }

    public static boolean tokenExists(String token) {
        return services.containsKey(token);
    }

    public static IService getService(String token) {
        return services.get(token);
    }

    public static void removeToken(String token) {
        services.remove(token);
    }
}
