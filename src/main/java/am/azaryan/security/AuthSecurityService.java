package am.azaryan.security;

import am.azaryan.model.User;

@FunctionalInterface
public interface AuthSecurityService {
    User getCurrentUser();
}
