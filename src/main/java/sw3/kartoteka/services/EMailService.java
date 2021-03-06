package sw3.kartoteka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import sw3.kartoteka.model.entity.Korisnik;

@Service
public class EMailService {
	private JavaMailSender javaMailSender;
	
	@Autowired
	public EMailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendMail(Korisnik user, String subject, String text) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom("kartotekaisa@outlook.com");
		mail.setSubject(subject);
		mail.setText(text);
		javaMailSender.send(mail);
		
	}

}
