package com.me.szzc.utils;

import com.me.szzc.constant.Constant;
import com.me.szzc.pojo.ro.ResultRO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;

@Slf4j
public class PasswordHelper {

	final static Base64.Encoder encoder = Base64.getEncoder();
	final static Base64.Decoder decoder = Base64.getDecoder();


	private static String algorithmName = "md5";
	private static int hashIterations = 2;

	/***
	 * 密码加密: 进行md5后，再利用盐值 进行hash
	 * @param pwd
	 * @param salt
	 * @return
	 */
	public static String encryString(String pwd, String salt) {
	    String newPassword = new SimpleHash(algorithmName, Utils.getMD5_32_xx(pwd+"hello, YZS"), ByteSource.Util.bytes(salt), hashIterations).toHex();

	    return newPassword;
	}

	/**
	 * 给字符串加密
	 */
	public static String base64Encode(String text) {
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
	public static String base64Decode(String encodedText) {
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

		/*String password = "古城区";

		// 加密
		System.out.println("====  [加密后] 用户名/密码  =====");
		System.out.println(PasswordHelper.base64Encode(password));

		// 解密
		System.out.println("\n====  [解密后] 用户名/密码  =====");
		System.out.println(PasswordHelper.base64Decode(PasswordHelper.base64Encode(password)));*/

		String user = "d7275e";
		String time = System.currentTimeMillis()+"";
//		String time = "1591070892695";
		String projectName = "武昌区";

		//用户标识+时间+项目名称+密钥 =  MD5加密字符串， 放到Url当中，顺序是：用户标识、时间、md5、项目名称
		String md5Value = "d7275e15910708926958a6d1f40ed48ea46019f2af0b16fe087%E6%AD%A6%E6%98%8C%E5%8C%BA";
		String pwd = user+time+projectName+Constant.WX_SALT;
		System.out.println(pwd);
		String md5 = Utils.getMD5_32_xx(user+time+projectName+Constant.WX_SALT);
		System.out.println(md5);


		//将字符串进行url解码
		String pwda = URLDecoder.decode(md5Value, Constant.UTF8);
		System.out.println("解码后" + pwda);

		//前6位=用户标识
		String usera = pwda.substring(0,6);
		//接着是13位=时间戳
		String timea = pwda.substring(6, 19);
		//接着是32位的md5
		String md5Valuea = pwda.substring(19, 51);
		//最后是项目名称
		String projectNamea = pwda.substring(51, pwda.length());

		System.out.println(usera+ " " + timea+"  "+ md5Valuea + "  "+ projectNamea);


	}



}