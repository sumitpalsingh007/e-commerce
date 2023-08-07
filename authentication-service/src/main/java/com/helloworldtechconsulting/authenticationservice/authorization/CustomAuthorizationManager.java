package com.helloworldtechconsulting.authenticationservice.authorization;

import com.helloworldtechconsulting.authenticationservice.model.User;
import com.helloworldtechconsulting.authenticationservice.service.Policy;
import com.helloworldtechconsulting.authenticationservice.service.PolicyEnum;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;

import org.springframework.security.core.Authentication;


import java.util.Optional;
import java.util.function.Supplier;

public class CustomAuthorizationManager<T> implements AuthorizationManager<MethodInvocation> {
    private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, MethodInvocation methodInvocation) {

        if (hasAuthentication(authentication.get())) {

            Policy policyAnnotation = AnnotationUtils.findAnnotation(methodInvocation.getMethod(), Policy.class);

            User user = (User) authentication.get()
                    .getPrincipal();

            return new AuthorizationDecision(Optional.ofNullable(policyAnnotation)
                    .map(Policy::value)
                    .filter(policy -> policy == PolicyEnum.OPEN || (policy == PolicyEnum.RESTRICTED && user.isAccessToRestrictedPolicy()))
                    .isPresent());

        }

        return new AuthorizationDecision(false);
    }

    private boolean hasAuthentication(Authentication authentication) {
        return authentication != null && isNotAnonymous(authentication) && authentication.isAuthenticated();
    }

    private boolean isNotAnonymous(Authentication authentication) {
        return !this.trustResolver.isAnonymous(authentication);
    }
}
