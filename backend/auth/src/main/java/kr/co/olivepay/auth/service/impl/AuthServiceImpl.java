package kr.co.olivepay.auth.service.impl;

import kr.co.olivepay.auth.dto.req.LoginReq;
import kr.co.olivepay.auth.dto.req.RefreshReq;
import kr.co.olivepay.auth.dto.res.OwnerLoginRes;
import kr.co.olivepay.auth.dto.res.RefreshRes;
import kr.co.olivepay.auth.dto.res.UserLoginRes;
import kr.co.olivepay.auth.entity.Member;
import kr.co.olivepay.auth.entity.Tokens;
import kr.co.olivepay.auth.enums.Role;
import kr.co.olivepay.auth.global.handler.AppException;
import kr.co.olivepay.auth.global.response.SuccessResponse;
import kr.co.olivepay.auth.repository.MemberRepository;
import kr.co.olivepay.auth.service.AuthService;
import kr.co.olivepay.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static kr.co.olivepay.auth.global.enums.ErrorCode.ACCESS_DENIED;
import static kr.co.olivepay.auth.global.enums.ErrorCode.INVALID_LOGIN_REQUEST;
import static kr.co.olivepay.auth.global.enums.SuccessCode.LOGIN_SUCCESS;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SuccessResponse<UserLoginRes> userLogin(LoginReq loginReq) {
        Member member = validatePhoneNumber(loginReq.phoneNumber());
        if(member.getRole() == Role.OWNER){
            throw new AppException(ACCESS_DENIED);
        }

        //matches(평문, 암호문)으로 확인
        boolean matches = passwordEncoder.matches(loginReq.password(), member.getPassword());
        if(!matches) throw new AppException(INVALID_LOGIN_REQUEST);

        Tokens tokens = tokenService.createTokens(member.getId(), member.getRole());

        UserLoginRes response = UserLoginRes.builder()
                                .accessToken(tokens.getAccessToken())
                                .refreshToken(tokens.getRefreshToken())
                                .role(member.getRole().name())
                                .build();

        return new SuccessResponse<>(LOGIN_SUCCESS, response);
    }

    @Override
    public SuccessResponse<OwnerLoginRes> ownerLogin(LoginReq loginReq) {
        Member member = validatePhoneNumber(loginReq.phoneNumber());
        if(member.getRole() != Role.OWNER){
            throw new AppException(ACCESS_DENIED);
        }

        //matches(평문, 암호문)으로 확인
        boolean matches = passwordEncoder.matches(loginReq.password(), member.getPassword());
        if(!matches) throw new AppException(INVALID_LOGIN_REQUEST);

        // TODO: Franchise Service 에서 franchiseID 받아오기
        Long franchiseId = 3L;

        Tokens tokens = tokenService.createTokens(member.getId(), member.getRole());

        OwnerLoginRes response = OwnerLoginRes.builder()
                                            .accessToken(tokens.getAccessToken())
                                            .refreshToken(tokens.getRefreshToken())
                                            .franchiseId(franchiseId)
                                            .role(member.getRole().name())
                                            .build();

        return new SuccessResponse<>(LOGIN_SUCCESS, response);
    }

    @Override
    public SuccessResponse<RefreshRes> updateToken(RefreshReq refreshReq) {
        return null;
    }


    private Member validatePhoneNumber(String phoneNumber){
        return memberRepository.findByPhoneNumber(phoneNumber)
                               .orElseThrow(() -> new AppException(INVALID_LOGIN_REQUEST));
    }

}