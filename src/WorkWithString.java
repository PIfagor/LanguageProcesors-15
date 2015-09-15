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

	}

}
