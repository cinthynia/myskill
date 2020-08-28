package com.my.logdemo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	private static String StringPoolNULL="null";
	private static String StringPoolBLANK="";
	
	public static boolean equals(String s1, String s2) {
		if ((s1 == null) && (s2 == null)) {
			return true;
		} else if ((s1 == null) || (s2 == null)) {
			return false;
		} else {
			return s1.equals(s2);
		}
	}
	
	public static boolean isChar(char c) {
		return Character.isLetter(c);
	}
	
	public static boolean isChar(String s) {
		if (isNull(s)) {
			return false;
		}

		char[] c = s.toCharArray();

		for (int i = 0; i < c.length; i++) {
			if (!isChar(c[i])) {
				return false;
			}
		}

		return true;
	}

	public static boolean isDigit(char c) {
		int x = c;

		if ((x >= 48) && (x <= 57)) {
			return true;
		}

		return false;
	}

	public static boolean isNotDigit(String s) {
		return !isDigit(s);
	}
	public static boolean isDigit(String s) {
		if (isNull(s)) {
			return false;
		}

		char[] c = s.toCharArray();

		for (int i = 0; i < c.length; i++) {
			if (!isDigit(c[i])) {
				return false;
			}
		}

		return true;
	}

	public static boolean isHex(String s) {
		if (isNull(s)) {
			return false;
		}

		return true;
	}

	public static boolean isHTML(String s) {
		if (isNull(s)) {
			return false;
		}

		if (((s.indexOf("<html>") != -1) || (s.indexOf("<HTML>") != -1))
				&& ((s.indexOf("</html>") != -1) || (s.indexOf("</HTML>") != -1))) {

			return true;
		}

		return false;
	}

	public static boolean isDate(int month, int day, int year) {
		return isGregorianDate(month, day, year);
	}

	public static boolean isGregorianDate(int month, int day, int year) {
		if ((month < 0) || (month > 11)) {
			return false;
		}

		int[] months = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		if (month == 1) {
			int febMax = 28;

			if (((year % 4) == 0) && ((year % 100) != 0) || ((year % 400) == 0)) {

				febMax = 29;
			}

			if ((day < 1) || (day > febMax)) {
				return false;
			}
		} else if ((day < 1) || (day > months[month])) {
			return false;
		}

		return true;
	}

	public static boolean isJulianDate(int month, int day, int year) {
		if ((month < 0) || (month > 11)) {
			return false;
		}

		int[] months = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		if (month == 1) {
			int febMax = 28;

			if ((year % 4) == 0) {
				febMax = 29;
			}

			if ((day < 1) || (day > febMax)) {
				return false;
			}
		} else if ((day < 1) || (day > months[month])) {
			return false;
		}

		return true;
	}


	public static boolean isEmailAddressSpecialChar(char c) {

		// LEP-1445

		for (int i = 0; i < _EMAIL_ADDRESS_SPECIAL_CHAR.length; i++) {
			if (c == _EMAIL_ADDRESS_SPECIAL_CHAR[i]) {
				return true;
			}
		}

		return false;
	}


	public static boolean isName(String name) {
		if (isNull(name)) {
			return false;
		}

		char[] c = name.trim().toCharArray();

		for (int i = 0; i < c.length; i++) {
			if (((!isChar(c[i])) && (!Character.isWhitespace(c[i])))
					|| (c[i] == ',')) {

				return false;
			}
		}

		return true;
	}

	public static boolean isNumber(String number) {
		if (isNull(number)) {
			return false;
		}

		char[] c = number.toCharArray();

		for (int i = 0; i < c.length; i++) {
			if (!isDigit(c[i])) {
				return false;
			}
		}

		return true;
	}

	
	public static boolean isNull(Integer i) {
		if ((i == null) || i.intValue() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isNull(Long l) {
		if ((l == null) || l.longValue() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNull(String s) {
		if (s == null) {
			return true;
		}

		s = s.trim();

		if ((s.equals(StringPoolNULL)) || (s.equals(StringPoolBLANK))) {
			return true;
		}

		return false;
	}
	
	public static boolean isNull(Object obj) {
		return obj == null;
	}

	public static boolean isNull(Object[] array) {
		if ((array == null) || (array.length == 0)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotNull(Integer i) {
		return !isNull(i);
	}

	public static boolean isNotNull(Long l) {
		return !isNull(l);
	}

	public static boolean isNotNull(String s) {
		return !isNull(s);
	}
	
	public static boolean isNotNull(Object obj) {
		return obj != null;
	}

	public static boolean isNotNull(Object[] array) {
		return !isNull(array);
	}

	public static boolean isPassword(String password) {
		if (isNull(password)) {
			return false;
		}

		if (password.length() < 4) {
			return false;
		}

		char[] c = password.toCharArray();

		for (int i = 0; i < c.length; i++) {
			if ((!isChar(c[i])) && (!isDigit(c[i]))) {

				return false;
			}
		}

		return true;
	}
	
	public static boolean isMail(String mail) {
		String mailregex = "^([a-z0-9A-Z]+[-_\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		return match(mailregex, mail);
	}

	public static boolean match(String regexstr, String str) {
		Pattern regex = Pattern.compile(regexstr, Pattern.CASE_INSENSITIVE
				| Pattern.DOTALL);
		Matcher matcher = regex.matcher(str);
		return matcher.matches();
	}

	public static boolean isMobile(String mobiles) {
		Pattern p = Pattern.compile("^(13|15|18|14|17)[0-9]{9}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	public static boolean isTel(String tel) {
		Pattern p = Pattern
				.compile("^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{1,5}))?$");
		Matcher m = p.matcher(tel);
		return m.matches();
	}

	public static boolean isUsername(final String username) {
		Pattern pattern = Pattern.compile("^[a-z_][a-z0-9_]{4,32}$");
		Matcher matcher = pattern.matcher(username);
		return matcher.matches();
	}
	
	public static boolean isVariableTerm(String s) {
		if (s.startsWith("[$") && s.endsWith("$]")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 检查传入的对象是否存在null
	 * @param objects 对象数组
	 * @return 是否存在null值
     */
	public static boolean existsNull(Object... objects){
		for(Object object : objects){
			if(isNull(object)){
				return true;
			}
		}
		return false;
	}

	private static char[] _EMAIL_ADDRESS_SPECIAL_CHAR = new char[] { '.', '!',
			'#', '$', '%', '&', '\'', '*', '+', '-', '/', '=', '?', '^', '_',
			'`', '{', '|', '}', '~' };

}
