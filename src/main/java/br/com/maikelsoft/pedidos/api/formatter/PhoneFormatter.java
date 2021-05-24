package br.com.maikelsoft.pedidos.api.formatter;

import java.util.regex.Pattern;

import br.com.caelum.stella.format.BaseFormatter;
import br.com.caelum.stella.format.Formatter;

public class PhoneFormatter implements Formatter{

	private static final Pattern FORMATED = Pattern.compile("[(](\\d{2})[)](\\d{4})[-](\\d{4})");
	private static final Pattern UNFORMATED = Pattern.compile("(\\d{2})(\\d{4})(\\d{4})");

	private final BaseFormatter base;

	public PhoneFormatter() {
		this.base = new BaseFormatter(FORMATED, "($1)$2-$3", UNFORMATED, "$1$2$3");
	}

	@Override
	public String format(String value) throws IllegalArgumentException {
		return base.format(value);
	}

	@Override
	public String unformat(String value) throws IllegalArgumentException {
		return base.unformat(value);
	}

	@Override
	public boolean isFormatted(String value) {
		return base.isFormatted(value);
	}

	@Override
	public boolean canBeFormatted(String value) {
		return base.canBeFormatted(value);
	}
}
