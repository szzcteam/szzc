package com.me.szzc.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Slf4j
public class PasswordHelper {

	final static Base64.Encoder encoder = Base64.getEncoder();
	final static Base64.Decoder decoder = Base64.getDecoder();


	private static String algorithmName = "md5";
	private static int hashIterations = 2;

	public static String encryString(String pwd, String salt) {
	    String newPassword = new SimpleHash(algorithmName, Utils.getMD5_32_xx(pwd+"hello, YZS"), ByteSource.Util.bytes(salt), hashIterations).toHex();

	    return newPassword;
	}

	/**
	 * 给字符串加密
	 */
	public static String encode(String text) {
		byte[] textByte = new byte[0];
		try {
			textByte = text.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("base64加密异常", e);
		}
		String encodedText = encoder.encodeToString(textByte);
		return encodedText;
	}

	/**
	 * 将加密后的字符串进行解密
	 */
	public static String decode(String encodedText) {
		String text = null;
		try {
			text = new String(decoder.decode(encodedText), "UTF-8");
		} catch (Exception e) {
			log.error("base64解密异常", e);
		}
		return text;
	}


	
	public static void main(String args[]) throws Exception {
		String str = encryString("abc123","26003658957127684");
		System.out.println(str);
		AesCipherService aesCipherService = new AesCipherService();
		//decrypt(Hex.decode(encrptText),key.getEncoded()).getBytes()
		// aesCipherService.decrypt(Hex.decode(str),"TEST")
		DefaultHashService hashService = new DefaultHashService();
		//hashService.

		String password = "古城区";

		// 加密
		System.out.println("====  [加密后] 用户名/密码  =====");
		System.out.println(PasswordHelper.encode(password));

		// 解密
		System.out.println("\n====  [解密后] 用户名/密码  =====");
		System.out.println(PasswordHelper.decode(PasswordHelper.encode(password)));



	}


}