package com.laurentiuspilca.ssia.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laurentiuspilca.ssia.entities.Otp;
import com.laurentiuspilca.ssia.entities.User;
import com.laurentiuspilca.ssia.repositories.OtpRepository;
import com.laurentiuspilca.ssia.repositories.UserRepository;
import com.laurentiuspilca.ssia.utils.GenerateCodeUtil;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OtpRepository otpRepository;
	
	public void addUser(User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword())); 
		userRepository.save(user);
	}
	
	// 첫 번째 인증 단계
	public void auth(User user) {
		
		Optional<User> o = userRepository.findUserByUsername(user.getUsername());
		
		if(o.isPresent()) { // 암호 확인
			
			User u = o.get();
			if(passwordEncoder.matches(user.getPassword(), u.getPassword())) {
				
				renewOtp(u); // 암호가 맞으면 새 OTP 생성
			} else {
				
				throw new BadCredentialsException("Bad credentials.");
			}
		} else {
			
			throw new BadCredentialsException("Bad credentials.");
		}
	}
	
	// OTP 검증
	public boolean check(Otp otpToValidate) {
		
		Optional<Otp> userOtp = otpRepository.findOtpByUsername(otpToValidate.getUsername()); // 사용자 이름으로 OTP 검색
		
		// 데이터베이스에 OTP가 있고 비즈니스 논리 서버에서 받은 OTP와 일치하면 true 반환
		if(userOtp.isPresent()) {
			
			Otp otp = userOtp.get();
			if(otpToValidate.getCode().equals(otp.getCode())) {
				
				return true;
			}
		}
		
		return false;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////
	private void renewOtp(User u) {
		
		String code = GenerateCodeUtil.generateCode(); // OTP를 위한 임의의 수 생성
		
		Optional<Otp> userOtp = otpRepository.findOtpByUsername(u.getUsername());
		
		if(userOtp.isPresent()) {
			
			Otp otp = userOtp.get();
			otp.setCode(code);
		} else {
			
			Otp otp = new Otp();
			otp.setUsername(u.getUsername());
			otp.setCode(code);
			otpRepository.save(otp);
		}
	}
}
