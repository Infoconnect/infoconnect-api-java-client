package com.infogroup.api.types;

public enum Ethnicity {
	ALEUT("Aleut"), AFGHAN("Afghan"), AFRICAN_OTHER("African(other)"), AFRICANAMERICAN65("AfricanAmerican65%+"), AFRICANAMERICAN75("AfricanAmerican75%+"), AFRICANAMERICAN85(
			"AfricanAmerican85%+"), AFRICANAMERICAN95("AfricanAmerican95%+"), ALBANIAN("Albanian"), ALGERIAN("Algerian"), AMERICANINDIAN("AmericanIndian"), ANGOLAN(
			"Angolan"), ARAB("Arab"), ARMENIAN("Armenian"), ASANTE("Asante"), ASHANTI("Ashanti"), AUSTRALIAN("Australian"), AUSTRIAN("Austrian"), AZERB("Azerb"), AZERBAIJANI(
			"Azerbaijani"), BAHRAINI("Bahraini"), BANGLADESH("Bangladesh"), BASOTHO("Basotho"), BASQUE("Basque"), BELARUSIAN("Belarusian"), BELGIAN("Belgian"), BENINESE(
			"Beninese"), BHUTANESE("Bhutanese"), BOSNIAN("Bosnian"), BOTSWANAN("Botswanan"), BULGARIAN("Bulgarian"), BURKINAFASO("BurkinaFaso"), BURMA("Burma"), BURUNDIAN(
			"Burundian"), BYELORUSSIAN("Byelorussian"), CAMEROONIAN("Cameroonian"), CARIBBEANAFRICANAMERICAN("CaribbeanAfricanAmerican"), CENTRALAFRICANREPUBLIC(
			"CentralAfricanRepublic"), CEYLON("Ceylon"), CHAD("Chad"), CHECHNIAN("Chechnian"), CHECHNYAN("Chechnyan"), CHINESE("Chinese"), COMOROS("Comoros"), CONGO(
			"Congo"), COTEDIVOIRE("Coted'Ivoire"), CROATIAN("Croatian"), CZECH("Czech"), DANISH("Danish"), DEMOCRATICREPUBLICOFTHECONGO(
			"Democratic Republic of the Congo"), DJIBOUTIAN("Djiboutian"), DUTCH("Dutch"), EGYPTIAN("Egyptian"), ENGLISH("English"), EQUATORIALGUINEA(
			"EquatorialGuinea"), ESTONIAN("Estonian"), ETHIOPIAN("Ethiopian"), FIJIAN("Fijian"), FILIPINO("Filipino"), FINNISH("Finnish"), FRENCH("French"), GABONESE(
			"Gabonese"), GAMBIAN("Gambian"), GEORGIAN("Georgian"), GERMAN("German"), GHANAIAN("Ghanaian"), GREEK("Greek"), GUINEA_BISSAU("Guinea-Bissau"), GUINEAN(
			"Guinean"), GUYANA("Guyana"), GUYANESE("Guyanese"), HAUSA("Hausa"), HAWAIIAN("Hawaiian"), HISPANIC("Hispanic"), HUNGARIAN("Hungarian"), ICELANDIC(
			"Icelandic"), INDIAN("Indian"), INDONESIAN("Indonesian"), IRAQI("Iraqi"), IRISH("Irish"), ISLEOFMAN("IsleofMan"), ITALIAN("Italian"), IVORYCOAST(
			"IvoryCoast"), JAPANESE("Japanese"), JEWISH("Jewish"), KAZAKH("Kazakh"), KAZAKHSTAN("Kazakhstan"), KENYAN("Kenyan"), KHMER_CAMBODIAN(
			"Khmer(Cambodian)"), KHMER_KAMPUCHEAN("Khmer(Kampuchean)"), KHMER("Khmer"), KIRGHIZ("Kirghiz"), KOREAN("Korean"), KURDISH("Kurdish"), KUWAITI(
			"Kuwaiti"), KYRGYZSTAN("Kyrgyzstan"), LAOTIAN("Laotian"), LATVIAN("Latvian"), LESOTHO("Lesotho"), LIBERIAN("Liberian"), LIBYAN("Libyan"), LIECHTENSTEIN(
			"Liechtenstein"), LITHUANIAN("Lithuanian"), LUXEMBOURGIAN("Luxembourgian"), MACEDONIAN("Macedonian"), MADAGASCAR("Madagascar"), MALAWIAN("Malawian"), MALAY(
			"Malay"), MALAYSIAN("Malaysian"), MALDIVIAN("Maldivian"), MALIAN("Malian"), MALTESE("Maltese"), MANX("Manx"), MAURITANIAN("Mauritanian"), MOLDAVIAN(
			"Moldavian"), MOLDOVAN("Moldovan"), MONGOLIAN("Mongolian"), MOROCCAN("Moroccan"), MOZAMBICAN("Mozambican"), MOZAMBIQUE("Mozambique"), MULTI_ETHNIC(
			"multi-ethnic"), MYANMAR("Myanmar"), NAMIBIAN("Namibian"), NATIVEAMERICAN("NativeAmerican"), NAURUAN("Nauruan"), NEPALESE("Nepalese"), NEWZEALAND(
			"NewZealand"), NIGER("Niger"), NIGERIAN("Nigerian"), NORWEGIAN("Norwegian"), OTHERASIAN("otherAsian"), PAKISTANI("Pakistani"), PAPUANEWGUINEAN(
			"PapuaNewGuinean"), PERSIAN("Persian"), PHILIPPINE("Philippine"), PILI("Pili"), PILIPINO("Pilipino"), POLISH("Polish"), PORTUGUESE("Portuguese"), QATARI(
			"Qatari"), ROMANIAN("Romanian"), RUANDAN("Ruandan"), RUSSIAN("Russian"), RWANDAN("Rwandan"), SAUDI("Saudi"), SCOTCH("Scotch"), SCOTTISH("Scottish"), SENEGALESE(
			"Senegalese"), SERBIAN("Serbian"), SEYCHELLES("Seychelles"), SIERRALEONE("SierraLeone"), SLOVAK("Slovak"), SLOVAKIAN("Slovakian"), SLOVENE(
			"Slovene"), SLOVENIAN("Slovenian"), SOMALIAN("Somalian"), SOUTHAFRICAN("SouthAfrican"), SRILANKAN("SriLankan"), SUDANESE("Sudanese"), SURINAMESE(
			"Surinamese"), SWAHILI("Swahili"), SWAZILAND("Swaziland"), SWEDISH("Swedish"), SWISS("Swiss"), SYRIAN("Syrian"), TAJIK("Tajik"), TAJIKISTAN(
			"Tajikistan"), TANZANIAN("Tanzanian"), TELUGAN("Telugan"), THAI("Thai"), TIBETAN("Tibetan"), TOGO("Togo"), TOGOLESE("Togolese"), TONGAN("Tongan"), TUNISIAN(
			"Tunisian"), TURKISH("Turkish"), TURKMENISTAN("Turkmenistan"), UGANDAN("Ugandan"), UKRAINIAN("Ukrainian"), UZBEKISTANI("Uzbekistani"), VANUATUAN(
			"Vanuatuan"), VIETNAMESE("Vietnamese"), WELSH("Welsh"), WESTERNSAMOAN("WesternSamoan"), XHOSA("Xhosa"), YEMENI("Yemeni"), ZAIRIAN("Zairian"), ZAMBIAN(
			"Zambian"), ZIMBABWEAN("Zimbabwean"), ZULU("Zulu");

	private String val;

	private Ethnicity(String val) {
		this.val = val;
	}

	public String toString() {
		return val;
	}

}
