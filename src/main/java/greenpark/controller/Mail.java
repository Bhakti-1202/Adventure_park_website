package greenpark.controller;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Mail extends Authenticator
{
	@Override
	protected PasswordAuthentication getPasswordAuthentication() 
	{
		return new PasswordAuthentication("rushijadhavrj9566@gmail.com", "jzsmxvibeuqrojft") ;
	}
}
