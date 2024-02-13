package shop.remindermemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.remindermemo.domain.RefreshToken;
import shop.remindermemo.repository.RefreshTokenRepository;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }
}
