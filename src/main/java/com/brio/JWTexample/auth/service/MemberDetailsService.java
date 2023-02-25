package com.brio.JWTexample.auth.service;

import com.brio.JWTexample.auth.utils.CustomAuthorityUtils;
import com.brio.JWTexample.member.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class MemberDetailsService implements UserDetailsService {
    // USER의
//    private final CustomAuthorityUtils authorityUtils
//    private final MemberRepository memberRepository;
    private final CustomAuthorityUtils authorityUtils;
    private final PasswordEncoder passwordEncoder;

    public MemberDetailsService(CustomAuthorityUtils authorityUtils, PasswordEncoder passwordEncoder) {
        this.authorityUtils = authorityUtils;
        this.passwordEncoder = passwordEncoder;
    }

    //    public MemberDetailsService(MemberRepository memberRepository, CustomAuthorityUtils authorityUtils) {
//        this.memberRepository = memberRepository;
//        this.authorityUtils = authorityUtils;
//    }
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // username을 통해 레포지토리에 저장된 멤버인지 확인한다.
//        Optional<Member> optionalMember = memberRepository.findByEmail(username);
//        Member findMember = optionalMember.orElseThrow(() -> new RuntimeException());
//        return new MemberDetails(findMember);
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 간결한 예시 코드를 위해 레포지토리와의 연결을 해제한 더미 데이터입니다.
        Member findMember = new Member();
        findMember.setMemberId(1L);
        findMember.setEmail("jwt@test.com");
        findMember.setPassword(passwordEncoder.encode("testword"));
        findMember.setRoles(List.of("USER", "ADMIN"));
        return new MemberDetails(findMember);
    }

    private class MemberDetails extends Member implements UserDetails {
        public MemberDetails(Member member) {
            setMemberId(member.getMemberId());
            setEmail(member.getEmail());
            setPassword(member.getPassword());
            setRoles(member.getRoles());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorityUtils.createAuthorities(this.getRoles());
        }

        @Override
        public String getUsername() {
            return getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
