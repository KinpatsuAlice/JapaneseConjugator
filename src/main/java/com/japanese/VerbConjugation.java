package com.japanese;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

import static com.japanese.VerbConjugationClassification.*;

public enum VerbConjugation {
	
	//Casual form
	NEGATIVE_FORM("Ne","あ","ない","Negative form",CASUAL),
	NEGATIVE_PAST_FORM("Ne-Pa","あ","なかった","Negative Past form",CASUAL),
	PAST_FORM("Pa","た","た","Past form",CASUAL),
	//Polite form
	POLITE_FORM("Po","い","ます","Polite form",POLITE),
	POLITE_NEGATIVE_FORM("Po-Ne","い","ません","Polite Negative form",POLITE),
	POLITE_NEGATIVE_PAST_FORM("Po-Ne-Pa","い","ませんでした","Polite Negative Past form",POLITE),
	POLITE_PAST_FORM("Po-Pa","い","ました","Polite Past form",POLITE),
	//Volitional form
	VOLITIONAL_FORM("Vo","お","う","よう","Volitional form",VOLITIONAL),
	POLITE_VOLITIONAL_FORM("Po-Vo","い","ましょう","Polite Volitional form",VOLITIONAL),
	//Hypothetical form
	HYPOTHETICAL_FORM("Hy","え","ば","れば","Hypothetical form",HYPOTHETICAL),
	NEGATIVE_HYPOTHETICAL_FORM("Ne-Hy","あ","なければ","Negative Hypothetical form",HYPOTHETICAL),
	//Conditional form
	CONDITIONAL_FORM("Cond","たら","たら","Conditional form",CONDITIONAL),
	NEGATIVE_CONDITIONAL_FORM("Ne-Cond","あ","なかったら","Negative Conditional form",CONDITIONAL),
	POLITE_NEGATIVE_CONDITIONAL_FORM("Po-Ne-Cond","い","ませんでしたら","Polite Negative Conditional form",CONDITIONAL),
	POLITE_CONDITIONAL_FORM("Po-Cond","い","ましたら","Polite Conditional form",CONDITIONAL),
	//Conjunctive form
	CONJUNCTIVE_FORM("Conj","て","て","Conjunctive form",CONJUNCTIVE),
	NEGATIVE_CONJUNCTIVE_FORM("Ne-Conj","あ","ないで","Negative Conjunctive form",CONJUNCTIVE),
	POLITE_CONJUNCTIVE_FORM("Po-Conj","い","まして","Polite Conjunctive form",CONJUNCTIVE),
	POLITE_NEGATIVE_CONJUNCTIVE_FORM("Po-Ne-Conj","い","ませんで","Polite Negative Conjuctive form",CONJUNCTIVE),
	//Imperative form
	NEGATIVE_IMPERATIVE_FORM("Ne-Im","う","な","Negative Imperative form",IMPERATIVE),
	POLITE_IMPERATIVE_FORM("Po-Im","い","なさい","Polite Imperative form",IMPERATIVE),
	//Potential form
	POTENTIAL_FORM("Pt","え","る","られる","Potential form",POTENTIAL),
	NEGATIVE_POTENTIAL_FORM("Ne-Pt","え","ない","られない","Negative Potential form",POTENTIAL),
	NEGATIVE_PAST_POTENTIAL_FORM("Ne-Pa-Pt","え","なかった","られなかった","Negative Past Potential form",POTENTIAL),
	PAST_POTENTIAL_FORM("Pa-Pt","え","た","られた","Past Potential form",POTENTIAL),
	//Passive form
	PASSIVE_FORM("Ps","あ","れる","られる","Passive form",PASSIVE_CAUSATIVE),
	//Causative form
	CAUSATIVE_FORM("Cs","あ","せる","させる","Causative form",PASSIVE_CAUSATIVE),
	//Tai form
	TAI_FORM("Tai","い","たい","Tai form",TAI),
	NEGATIVE_TAI_FORM("Tai-Ne","い","たくない","Negative Tai form",TAI),
	NEGATIVE_PAST_TAI_FORM("Tai-Ne-Pa","い","たくなかった","Negative Past Tai form",TAI),
	PAST_TAI_FORM("Tai-Pa","い","たかった","Past Tai form",TAI),
	SUGIRU_FORM("Sugi","い","すぎる","すぎる form",OTHERS);
	
	//Enum attributes
	private final String id;
	private final String godanForm;
	private final String godanConjugation;
	private final String ichidanConjugation;
	private final String conjugationName;
	private final VerbConjugationClassification conjugationClass;
	
	private static final Map<String, VerbConjugation> conjugations;
	private static final Map<String, String[]> godanInflections;
	private static final Map<String, Map<VerbConjugation,String>> irregulars;
	
	static {
		List<VerbConjugation> conjugationList = Arrays.asList(VerbConjugation.values());
		
		//Conjugations Map Initialization
		Map<String, VerbConjugation> map = new HashMap<>();
		map = conjugationList.stream()
				.collect(Collectors.toMap(VerbConjugation::getId, Function.identity()));
		conjugations = Collections.unmodifiableMap(map);
		
		//Godan Inflections Map Initialization
		Map<String, String[]> inflections = new HashMap<>();
		inflections.put("う",new String[] {"わ","い","う","え","お","った","ったら","って"});
		inflections.put("く",new String[] {"か","き","く","け","こ","いた","いたら","いて"});
		inflections.put("ぐ",new String[] {"が","ぎ","ぐ","げ","ご","いだ","いだら","いで"});
		inflections.put("す",new String[] {"さ","し","す","せ","そ","した","したら","して"});
		inflections.put("つ",new String[] {"た","ち","つ","て","と","った","ったら","って"});
		inflections.put("ぬ",new String[] {"な","に","ぬ","ね","の","んだ","んだら","んで"});
		inflections.put("ぶ",new String[] {"ば","び","ぶ","べ","ぼ","んだ","んだら","んで"});
		inflections.put("む",new String[] {"ま","み","む","め","も","んだ","んだら","んで"});
		inflections.put("る",new String[] {"ら","り","る","れ","ろ","った","ったら","って"});
		godanInflections = Collections.unmodifiableMap(inflections);
		
		//Exceptions Map Initialization
		/*Map<String, String[]> exceptions = new HashMap<>();
		exceptions.put("くる",new String[] {"こない","こなかった","きた","きます","きません","きませんでした","きました","こよう","きましょう"
				,"きれば","こなければ","きたら","こなかったら","きませんでしたら","きましたら","きて","こないで","きまして","きませんで","くるな","きなさい"
				,"こられる","こされる","きたい","きたくない","きたくなかった","きたかった","きすぎる"});
		exceptions.put("する", new String[] {"しない","しなかった","した","します","しません","しませんでした","しました","しよう","しましょう",
				"すれば","しなければ","したら","しなかったら","しませんでしたら","しましたら","して","しないで","しまして","しませんで","するな","しなさい"
				,"される","させる","したい","したくない","したくなかった","したかった","しすぎる"});
		irregulars = Collections.unmodifiableMap(exceptions);*/
		irregulars = Collections.unmodifiableMap(initIrregulars());
	}

	//Constructors
	//Constructor for conjugations whose ichidan conjugation is equal to the godan conjugation
	private VerbConjugation(String id,String godanForm, String godanConjugation,
			String conjugationName, VerbConjugationClassification conjugationClass) {
		this(id, godanForm, godanConjugation, godanConjugation, conjugationName,conjugationClass);
	}

	private VerbConjugation(String id,String godanForm, String godanConjugation,
			String ichidanConjugation, String conjugationName,
			VerbConjugationClassification conjugationClass) {
		this.id = id;
		this.godanForm = godanForm;
		this.godanConjugation = godanConjugation;
		this.ichidanConjugation = ichidanConjugation;
		this.conjugationName = conjugationName;
		this.conjugationClass = conjugationClass;
	}

	
	public static Map<String, Map<VerbConjugation,String>> initIrregulars() {
		Map<String, Map<VerbConjugation,String>> exceptions = new HashMap<>();
		//する
		exceptions.put("する",new HashMap<>());
		exceptions.get("する").put(NEGATIVE_FORM,"しない");
		exceptions.get("する").put(NEGATIVE_PAST_FORM,"しなかった");
		exceptions.get("する").put(PAST_FORM,"した");
		exceptions.get("する").put(POLITE_FORM,"します");
		exceptions.get("する").put(POLITE_NEGATIVE_FORM,"しません");
		exceptions.get("する").put(POLITE_NEGATIVE_PAST_FORM,"しませんでした");
		exceptions.get("する").put(POLITE_PAST_FORM,"しました");
		exceptions.get("する").put(VOLITIONAL_FORM,"しよう");
		exceptions.get("する").put(POLITE_VOLITIONAL_FORM,"しましょう");
		exceptions.get("する").put(HYPOTHETICAL_FORM,"すれば");
		exceptions.get("する").put(NEGATIVE_HYPOTHETICAL_FORM,"しなければ");
		exceptions.get("する").put(CONDITIONAL_FORM,"したら");
		exceptions.get("する").put(NEGATIVE_CONDITIONAL_FORM,"しなかったら");
		exceptions.get("する").put(POLITE_NEGATIVE_CONDITIONAL_FORM,"しませんでしたら");
		exceptions.get("する").put(POLITE_CONDITIONAL_FORM,"しましたら");
		exceptions.get("する").put(CONJUNCTIVE_FORM,"して");
		exceptions.get("する").put(NEGATIVE_CONJUNCTIVE_FORM,"しないで");
		exceptions.get("する").put(POLITE_CONJUNCTIVE_FORM,"しまして");
		exceptions.get("する").put(POLITE_NEGATIVE_CONJUNCTIVE_FORM,"しませんｄえ");
		exceptions.get("する").put(NEGATIVE_IMPERATIVE_FORM,"するな");
		exceptions.get("する").put(POLITE_IMPERATIVE_FORM,"しなさい");
		exceptions.get("する").put(POTENTIAL_FORM,"できる");
		exceptions.get("する").put(NEGATIVE_POTENTIAL_FORM,"できない");
		exceptions.get("する").put(NEGATIVE_PAST_POTENTIAL_FORM,"できなかった");
		exceptions.get("する").put(PAST_POTENTIAL_FORM,"できた");
		exceptions.get("する").put(PASSIVE_FORM,"される");
		exceptions.get("する").put(CAUSATIVE_FORM,"させる");
		exceptions.get("する").put(TAI_FORM,"したい");
		exceptions.get("する").put(NEGATIVE_TAI_FORM,"したくない");
		exceptions.get("する").put(NEGATIVE_PAST_TAI_FORM,"したくなかった");
		exceptions.get("する").put(PAST_TAI_FORM,"したかった");
		exceptions.get("する").put(SUGIRU_FORM,"しすぎる");
		exceptions.put("くる",new HashMap<>());
		exceptions.get("くる").put(NEGATIVE_FORM,"こない");
		exceptions.get("くる").put(NEGATIVE_PAST_FORM,"こなかった");
		exceptions.get("くる").put(PAST_FORM,"きた");
		exceptions.get("くる").put(POLITE_FORM,"きます");
		exceptions.get("くる").put(POLITE_NEGATIVE_FORM,"きません");
		exceptions.get("くる").put(POLITE_NEGATIVE_PAST_FORM,"きませんでした");
		exceptions.get("くる").put(POLITE_PAST_FORM,"きました");
		exceptions.get("くる").put(VOLITIONAL_FORM,"こよう");
		exceptions.get("くる").put(POLITE_VOLITIONAL_FORM,"きましょう");
		exceptions.get("くる").put(HYPOTHETICAL_FORM,"くれば");
		exceptions.get("くる").put(NEGATIVE_HYPOTHETICAL_FORM,"こなければ");
		exceptions.get("くる").put(CONDITIONAL_FORM,"きたら");
		exceptions.get("くる").put(NEGATIVE_CONDITIONAL_FORM,"こなかったら");
		exceptions.get("くる").put(POLITE_NEGATIVE_CONDITIONAL_FORM,"きませんでしたら");
		exceptions.get("くる").put(POLITE_CONDITIONAL_FORM,"きましたら");
		exceptions.get("くる").put(CONJUNCTIVE_FORM,"きて");
		exceptions.get("くる").put(NEGATIVE_CONJUNCTIVE_FORM,"こないで");
		exceptions.get("くる").put(POLITE_CONJUNCTIVE_FORM,"きまして");
		exceptions.get("くる").put(POLITE_NEGATIVE_CONJUNCTIVE_FORM,"きませんで");
		exceptions.get("くる").put(NEGATIVE_IMPERATIVE_FORM,"くるな");
		exceptions.get("くる").put(POLITE_IMPERATIVE_FORM,"きなさい");
		exceptions.get("くる").put(POTENTIAL_FORM,"こられる");
		exceptions.get("くる").put(NEGATIVE_POTENTIAL_FORM,"こられない");
		exceptions.get("くる").put(NEGATIVE_PAST_POTENTIAL_FORM,"こられなかった");
		exceptions.get("くる").put(PAST_POTENTIAL_FORM,"こられた");
		exceptions.get("くる").put(PASSIVE_FORM,"こられる");
		exceptions.get("くる").put(CAUSATIVE_FORM,"こさせる");
		exceptions.get("くる").put(TAI_FORM,"きたい");
		exceptions.get("くる").put(NEGATIVE_TAI_FORM,"きたくない");
		exceptions.get("くる").put(NEGATIVE_PAST_TAI_FORM,"きたくなかった");
		exceptions.get("くる").put(PAST_TAI_FORM,"きたかった");
		exceptions.get("くる").put(SUGIRU_FORM,"きすぎる");
		return exceptions;
	}
	/*public void printSteps(Verb verb) {
		
	}*/
	
	public static VerbConjugation getVerbConjugation(String s) {
		return conjugations.get(s);
	}
	
	public String getGodanInflection(String lastKana) {
		switch(this.godanForm) {
			case "あ":
				return godanInflections.get(lastKana)[0];
			case "い":
				return godanInflections.get(lastKana)[1];
			case "う":
				return godanInflections.get(lastKana)[2];
			case "え":
				return godanInflections.get(lastKana)[3];
			case "お":
				return godanInflections.get(lastKana)[4];
			case "た":
				return godanInflections.get(lastKana)[5];
			case "たら":
				return godanInflections.get(lastKana)[6];
			case "て":
				return godanInflections.get(lastKana)[7];
			default:
				return "";
		}
	}
	
	public String getIrregularConjugation(VerbDTO verb) {
		String furigana = verb.getKana();
		return furigana.substring(0,furigana.length() - 2)
				.concat(irregulars.get(furigana.substring(furigana.length() - 2,furigana.length())).get(this));
	}
	
	public static Map<String,List<VerbConjugation>> getClassifiedConjugations() {
		VerbConjugationClassification[] classifications = VerbConjugationClassification.values();
		Map<String,List<VerbConjugation>> map = new LinkedHashMap<>();
		Stream.of(classifications).forEach(x -> map.put(x.getName(), new ArrayList<>()));
		map.forEach((x,y) -> y.addAll(
				Stream.of(VerbConjugation.values())
				.filter(z -> z.conjugationClass.getName().equals(x))
				.toList()));
		return map;
	}
	
	//Getters
	public String getId() {
		return id;
	}

	public String getGodanForm() {
		return godanForm;
	}

	public String getGodanConjugation() {
		return godanConjugation;
	}

	public String getIchidanConjugation() {
		return ichidanConjugation;
	}
	
	public String getConjugationName() {
		return conjugationName;
	}
	
}