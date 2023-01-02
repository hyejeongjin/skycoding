package kr.util;

public class StringUtil {
	/*
	 * HTML 태그를 허용하면서 줄바꿈 
	 */
	public static String useBrHtml(String str) {
		if(str == null) return null; //null값은 null 그대로 반환
		
		return str.replaceAll("\r\n", "<br>")
				  .replaceAll("\r", "<br>") //이렇게 있어도 <br>처리
				  .replaceAll("\n", "<br>"); //이렇게 있어도 <br>처리
	}
	
	/*
	 * HTML 태그를 허용하지 않으면서 줄바꿈 
	 */
	public static String useBrNoHtml(String str) {
		if(str == null) return null;
		
		return str.replaceAll("<", "&lt;")
				  .replaceAll(">", "&gt;")
				  .replaceAll("\r\n","<br>")
				  .replaceAll("\r", "<br>")
				  .replaceAll("\n", "<br>");
	}
	
	/*
	 * HTML 태그를 허용하지 않음
	 */
	public static String useNoHtml(String str) {
		if(str == null) return null;
		
		return str.replaceAll("<", "&lt;")
				  .replaceAll(">", "&gt;");
	}
	
	/*
	 * 큰 따옴표 처리
	 */
	public static String parseQuot(String str) {
		if(str == null) return null;
		
		return str.replaceAll("\"", "&quot;");
	}
}






