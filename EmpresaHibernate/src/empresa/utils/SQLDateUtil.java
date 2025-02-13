package empresa.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class SQLDateUtil {

	public static String formatDate(Date date) {
		if (date == null) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}

	public static java.sql.Date parseDate(String dateStr) {
		if (dateStr == null || dateStr.isBlank()) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			java.util.Date utilDate = sdf.parse(dateStr);
			return new java.sql.Date(utilDate.getTime());
		} catch (ParseException e) {
			return null;
		}
	}
}
