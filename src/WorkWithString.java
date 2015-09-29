public class WorkWithString {

	/**
	 * @param args
	 * @author Wise
	 *            Variant 15
	 *  Задана послідовність символів.
	 *  Роздрукувати цю послідовність
	 *  а)витерши всі всі цифри
	 *  б)подвоївши всі одинарні пропуски
	 *  в)замінивши буквосполучееня "карб" на "крб"
	 */
	public static void main(String[] args) {

		String sourse = "Отримано: 112 карб.";
		String[] tokensVal = sourse.replaceAll(" ", "  ")
				.replaceAll("карб", "крб").split("\\d");
		String result = "";
		for (String sts : tokensVal) {
			result += sts;
		}
		System.out.println(sourse);
		System.out.println(result);
			
		int r = 0;
		int g = 11;
		int b = 150;
		
		int rgb = ((r&0x0ff)<<16)|((g&0x0ff)<<8)|(b&0x0ff);
		
		int r2 = r+15;
		int g2 = g+15;
		int b2 = b+15;
		int rgb2 = ((r2&0x0ff)<<16)|((g2&0x0ff)<<8)|(b2&0x0ff);
		
		int diff = ((15&0x0ff)<<16)|((15&0x0ff)<<8)|(15&0x0ff);
		
		System.out.println(rgb);
		System.out.println(rgb2);
		System.out.println(rgb+diff);
		
		int red =   (rgb2>>16)&0x0ff;
		int green = (rgb2>>8) &0x0ff;
		int blue =  (rgb2)    &0x0ff;
		
		System.out.println(red);
		System.out.println(green);
		System.out.println(blue);
		
		
	}

}
