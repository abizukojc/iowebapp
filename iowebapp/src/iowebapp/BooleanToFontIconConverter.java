package iowebapp;

import java.util.Locale;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.server.FontAwesome;

/**
 * Klasa która służy do kontwertowania typu boolean na podane w 
 * konstruktorze ikony ze zbioru FontAwesome.
 * 
 * @author Krzysztof Perchlicki
 */
public class BooleanToFontIconConverter implements Converter<String, Boolean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Ikona ze zbioru FontAwesome, używana gdy wartość boolean jest true.
	 */
	private FontAwesome trueIcon;
	/**
	 * Ikona ze zbioru FontAwesome, u�ywana gdy wartość boolean jest false.
	 */
	private FontAwesome falseIcon;

	/**
	 * Konstruktor przyjmuj�cy dwie ikony ze zbioru FontAwesome.
	 * 
	 * @param trueIcon
	 * @param falseIcon
	 */
	public BooleanToFontIconConverter(final FontAwesome trueIcon, final FontAwesome falseIcon) {
		super();
		this.trueIcon = trueIcon;
		this.falseIcon = falseIcon;
	}

	/** 
	 * Konwertuje string na typ boolean (tutaj niewspierane).
	 */
	@Override
    public Boolean convertToModel(final String value,
            final Class<? extends Boolean> targetType, final Locale locale)
            throws ConversionException {
        throw new ConversionException("Not supported");
    }

    /** Metoda, która konwertuje typ boolean na kod html do ikon FontAwesome.
     * W przypadku gdy jest wartość true to jest to kod do trueIcon,
     * a gdy jest to wartość false jest to kod do falseIcon.
     */
    @Override
    public String convertToPresentation(final Boolean value,
            final Class<? extends String> targetType, final Locale locale)
            throws ConversionException {
    	String result;
        if (value.equals(true)) {
            result=trueIcon.getHtml();
        } else {
            result=falseIcon.getHtml();
        }
        return result;
    }

    /** 
     * Metoda, która zwraca klasę która jest konwertowana (Boolean).
     */
    @Override
    public Class<Boolean> getModelType() {
        return Boolean.class;
    }

    
    /** 
     * Metoda, która zwraca klasę na którą typ boolean jest konwertowany.
     */
    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }

	/**
	 * Metoda, która daje dostęp do pola trueIcon.
	 * 
	 * @return referencja do trueIcon.
	 */
	public FontAwesome getTrueIcon() {
		return trueIcon;
	}

	/**
	 * Metoda, która pozwala zmienić trueIcon.
	 * 
	 * @param trueIcon
	 *            obiekt klasy enum FontAwesome (ikona gdy true).
	 */
	public void setTrueIcon(final FontAwesome trueIcon) {
		this.trueIcon = trueIcon;
	}

	/**
	 * Metoda, która daje dostęp do pola falseIcon.
	 * 
	 * @return referencja do falseIcon.
	 */
	public FontAwesome getFalseIcon() {
		return falseIcon;
	}

	/**
	 * Metoda, która pozwala zmienić falseIcon.
	 * 
	 * @param falseIcon
	 *            obiekt klasy enum FontAwesome (ikona gdy false).
	 */
	public void setFalseIcon(final FontAwesome falseIcon) {
		this.falseIcon = falseIcon;
	}
	
}