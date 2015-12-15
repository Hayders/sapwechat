package com.sapwechat.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CheckAuthority {

	private static final String token = "SAPWechat";

	public static boolean checkSignature(String signature, String timestamp,
			String nonce) {
		String[] arr = new String[] { token, timestamp, nonce };
		// 字典排序
		Arrays.sort(arr);
		// 拼接成字符串
		StringBuffer content = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		// sha1加密
		String temp = getSha1(content.toString());

		return temp.equals(signature);
	}

	public static String getSha1(String decript) {

		if (decript == null || decript.length() == 0) {
			return null;
		}

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA1");
			digest.update(decript.getBytes("UTF-8"));

			byte[] messageDigest = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			//
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

}
