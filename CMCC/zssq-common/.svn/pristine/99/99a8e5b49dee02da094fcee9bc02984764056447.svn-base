package com.zssq.utils;

import java.io.UnsupportedEncodingException;

import cfca.util.Base64;

public class EasyEncrypt
{
	private static final char[] UC_ENCRYPT_CHARS = { 'M', 'D', 'X', 'U', 'P', 'I', 'B', 'E', 'J', 'C', 'T', 'N', 
		'K', 'O', 'G', 'W', 'R', 'S', 'F', 'Y', 'V', 'L', 'Z', 'Q', 'A', 'H' };

	private static final char[] LC_ENCRYPT_CHARS = { 'm', 'd', 'x', 'u', 'p', 'i', 'b', 'e', 'j', 'c', 't', 'n', 
		'k', 'o', 'g', 'w', 'r', 's', 'f', 'y', 'v', 'l', 'z', 'q', 'a', 'h' };
	
	private static final char[] NUM_ENCRYPT_CHARS = {'2','5','0','1','8','6','4','3','7','9'};
	
	private static char[] UC_DECRYPT_CHARS = new char[26];
	private static char[] LC_DECRYPT_CHARS = new char[26];
	private static char[] NUM_DECRYPT_CHARS = new char[10];
	static {
		for (int i = 0; i < 26; i++) {
			char b = UC_ENCRYPT_CHARS[i];
			UC_DECRYPT_CHARS[b - 'A'] = (char) ('A' + i);

			b = LC_ENCRYPT_CHARS[i];
			LC_DECRYPT_CHARS[b - 'a'] = (char) ('a' + i);
		}
		for(int i=0;i<10;i++){
			char b=NUM_ENCRYPT_CHARS[i];
			NUM_DECRYPT_CHARS[b - '0'] = (char)('0' + i);
		}
	}

	public static char encrypt(char b) {
		if (b >= 'A' && b <= 'Z') {
			return UC_ENCRYPT_CHARS[b - 'A'];
		} else if (b >= 'a' && b <= 'z') {
			return LC_ENCRYPT_CHARS[b - 'a'];
		} else if(b>='0' && b<='9'){
			return NUM_ENCRYPT_CHARS[b - '0'];
		}else if(b=='+'){
			return '#';
		}else if(b=='/'){
			return '$';
		}else if(b=='='){
			return '%';
		}else{
			return b;
		}
	}

	public static char decrypt(char b) {
		if (b >= 'A' && b <= 'Z') {
			return UC_DECRYPT_CHARS[b - 'A'];
		} else if (b >= 'a' && b <= 'z') {
			return LC_DECRYPT_CHARS[b - 'a'];
		} else if(b>='0' && b<='9'){
			return NUM_DECRYPT_CHARS[b - '0'];
		}else if(b=='#'){
			return '+';
		}else if(b=='$'){
			return '/';
		}else if(b=='%'){
			return '=';
		}else{
			return b;
		}
	}
	
	/**
	 * 
	 * @Title: encrypt
	 * @Description: 加密
	 * @param @param input	待加密字符串
	 * @param @return    参数
	 * @return String    返回类型
	 * @throws
	 */
	public static String encrypt(String input){
		StringBuilder sb = new StringBuilder();
		try {
			String base64Str = new String(Base64.encode(input.getBytes("UTF-8")),"UTF-8");
			for(int i = 0; i < base64Str.length(); i++){
				sb.append(encrypt(base64Str.charAt(i)));
			}
		} catch (UnsupportedEncodingException e) {
			
			return null;
		}
		return sb.toString();
	}
	
	
	/**
	 * 
	 * @Title: decrypt
	 * @Description: 解密
	 * @param @param input	待解密字符串
	 * @param @return    参数
	 * @return String    返回类型
	 * @throws
	 */
	public static String decrypt(String input){
		if(input.endsWith("%25")){
			input = input.replace("%25", "%");
		}
		try {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < input.length(); i++){
				sb.append(decrypt(input.charAt(i)));
			}
			byte[] decode = Base64.decode(sb.toString());
			return new String(decode,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

}