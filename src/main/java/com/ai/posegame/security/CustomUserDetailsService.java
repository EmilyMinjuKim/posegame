package com.ai.posegame.security;

import com.ai.posegame.domain.Member;
import com.ai.posegame.repository.MemberRepository;
import com.ai.posegame.security.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;


@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    /*
    UserDetailsService는 loadUserByUsername이라는 단 하나의 메소드를 가짐
    -> 실제 인증 처리할 때 호출되는 부분

    시큐리티는 아이디, 패스워드를 한번에 조회하는 것이 아니라, 아이디만을 이용해서 사용자 정보를 로딩하고
    나중에 패스워드를 검증하는 방식.

    loadUerByUsername()의 반환타입은 UserDetails라는 인터페이스로, 이는 사용자 인증과 관련된 정보 저장.
    (authorities, password, username, accountNonExpired...)
    */

    // 로그인에 필요한 MemberSecurityDTO 반환을 위해 MemberRepository 주입
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws
                                                        UsernameNotFoundException {
        log.info("loadUserByUsername: " + username);

        Optional<Member> result = memberRepository.getWithRoles(username);

        if(result.isEmpty()){
            throw new UsernameNotFoundException("username not found...");
        }

        Member member = result.get();

        MemberSecurityDTO memberSecurityDTO =
                new MemberSecurityDTO(
                        member.getMid(),
                        member.getMpw(),
                        member.getEmail(),
                        member.isDel(),
                        false,
                        member.getRoleSet()
                                .stream().map(memberRole -> new SimpleGrantedAuthority(
                                        "ROLE_"+memberRole.name()))
                                .collect(Collectors.toList())
                );

        log.info("memberSecurityDTO");
        log.info(memberSecurityDTO);

        return memberSecurityDTO;
    }
}