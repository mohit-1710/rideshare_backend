package com.ridehub.backend.service;

import com.ridehub.backend.dto.LoginRequest;
import com.ridehub.backend.dto.SignupRequest;
import com.ridehub.backend.dto.TokenResponse;
import com.ridehub.backend.exception.InvalidRequestException;
import com.ridehub.backend.model.Account;
import com.ridehub.backend.repository.AccountRepository;
import com.ridehub.backend.util.TokenManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;

    public AuthenticationService(
            AccountRepository accountRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            TokenManager tokenManager
    ) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
    }

    public void signup(SignupRequest request) {
        if (accountRepository.existsByUsername(request.getUsername())) {
            throw new InvalidRequestException("This username is already taken");
        }

        Account newAccount = Account.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        accountRepository.save(newAccount);
    }

    public TokenResponse authenticate(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new InvalidRequestException("Incorrect username or password");
        }

        Account account = accountRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new InvalidRequestException("Account does not exist"));

        String accessToken = tokenManager.createAccessToken(
                account.getUsername(),
                account.getRole()
        );

        return new TokenResponse(accessToken);
    }
}
