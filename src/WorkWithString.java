public class WorkWithString {

	/**
	 * @param args
	 * @author Wise
	 *            Variant 15
	 *  ������ ����������� �������.
	 *  ������������ �� �����������
	 *  �)������� �� �� �����
	 *  �)�������� �� ������� ��������
	 *  �)�������� ��������������� "����" �� "���"
	 */
	public static void main(String[] args) {

		String sourse = "��������: 112 ����.";
		String[] tokensVal = sourse.replaceAll(" ", "  ")
				.replaceAll("����", "���").split("\\d");
		String result = "";
		for (String sts : tokensVal) {
			result += sts;
		}
		System.out.println(sourse);
		System.out.println(result);

	}

}
