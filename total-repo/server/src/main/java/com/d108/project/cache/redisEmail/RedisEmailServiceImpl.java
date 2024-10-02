package com.d108.project.cache.redisEmail;

import com.d108.project.cache.redis.RedisUtil;
import com.d108.project.cache.redisEmail.dto.EmailAuthCheckDto;
import com.d108.project.domain.member.repository.MemberRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

// 별도로 entity 를 설정할 정도로 복잡한 로직은 아닌 것 같음.
@Service
@RequiredArgsConstructor
public class RedisEmailServiceImpl implements RedisEmailService {

    @Value("${spring.mail.username}")
    private String hostEmail;
    private final RedisUtil redisUtil;
    private final JavaMailSender mailSender;
    private final MemberRepository memberRepository;
    private static final SecureRandom random = new SecureRandom();

    /**
     * 이메일 인증을 위한 임의의 6자리 숫자 인증 코드를 생성하고, 레디스에 저장
     * @param email 인증 요청한 이메일
     * @return 인증코드
     */
    // 얘는 private로 설정
    private String createAuthCode(String email) {
        String authCode = String.format("%06d", random.nextInt(900000) + 100000);
        redisUtil.setDataExpire("auth: "+email, authCode, RedisUtil.REDIS_EMAIL_EXPIRE);
        return authCode;
    }

    // 인증코드 검증
    @Override
    public Boolean checkAuthCode(EmailAuthCheckDto emailAuthCheckDto) {
        String storedAuthCode = redisUtil.getData("auth: " + emailAuthCheckDto.getEmail());
        if (emailAuthCheckDto.getAuthCode().equals(storedAuthCode)) {
            redisUtil.deleteData("auth: " + emailAuthCheckDto.getEmail());
            return true;
        }
        return false;
    }
    // 인증 메일 발송
    @Override
    public void sendEmail(String email) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");
        String authCode = createAuthCode(email);
        // TODO: 메일 템플릿 정하기
        String title = "제목";
        String content = "인증번호는" + authCode + "입니다";

        messageHelper.setFrom(hostEmail);
        messageHelper.setTo(email);
        messageHelper.setSubject(title);
        messageHelper.setText(content, true);
        mailSender.send(message);
    }
}
