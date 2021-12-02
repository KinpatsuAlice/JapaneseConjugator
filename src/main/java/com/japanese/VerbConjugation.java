package com.japanese;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

import com.kinpatsu.VerbController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum VerbConjugation {
	
	//Casual form
	NEGATIVE_FORM("Ne","あ","ない","Negative form","Casual"),
	NEGATIVE_PAST_FORM("Ne-Pa","あ","なかった","Negative Past form","Casual"),
	PAST_FORM("Pa","た","た","Past form","Casual"),
	//Polite form
	POLITE_FORM("Po","い","ます","Polite form","Polite"),
	POLITE_NEGATIVE_FORM("Po-Ne","い","ません","Polite Negative form","Polite"),
	POLITE_NEGATIVE_PAST_FORM("Po-Ne-Pa","い","ませんでした","Polite Negative Past form","Polite"),
	POLITE_PAST_FORM("Po-Pa","い","ました","Polite Past form","Polite"),
	//Volitional form
	VOLITIONAL_FORM("Vo","お","う","よう","Volitional form","Volitional"),
	POLITE_VOLITIONAL_FORM("Po-Vo","い","ましょう","Polite Volitional form","Volitional"),
	//Hypothetical form
	HYPOTHETICAL_FORM("Hy","え","ば","れば","Hypothetical form","Hypothetical"),
	NEGATIVE_HYPOTHETICAL_FORM("Ne-Hy","あ","なければ","Negative Hypothetical form","Hypothetical"),
	//Conditional form
	CONDITIONAL_FORM("Cond","たら","たら","Conditional form","Conditional"),
	NEGATIVE_CONDITIONAL_FORM("Ne-Cond","あ","なかったら","Negative Conditional form","Conditional"),
	POLITE_NEGATIVE_CONDITIONAL_FORM("Po-Ne-Cond","い","ませんでしたら","Polite Negative Conditional form","Conditional"),
	POLITE_CONDITIONAL_FORM("Po-Cond","い","ましたら","Polite Conditional form","Conditional"),
	//Conjunctive form
	CONJUNCTIVE_FORM("Conj","て","て","Conjunctive form","Conjunctive"),
	NEGATIVE_CONJUNCTIVE_FORM("Ne-Conj","あ","ないで","Negative Conjunctive form","Conjunctive"),
	POLITE_CONJUNCTIVE_FORM("Po-Conj","い","まして","Polite Conjunctive form","Conjunctive"),
	POLITE_NEGATIVE_CONJUNCTIVE_FORM("Po-Ne-Conj","い","ませんで","Polite Negative Conjuctive form","Conjunctive"),
	//Imperative form
	NEGATIVE_IMPERATIVE_FORM("Ne-Im","う","な","Negative Imperative","Imperative"),
	POLITE_IMPERATIVE_FORM("Po-Im","い","なさい","Polite Imperative","Imperative"),
	//Passive form
	PASSIVE_FORM("Ps","あ","れる","られる","Passive form","Passive & Causative"),
	//Causative form
	CAUSATIVE_FORM("Cs","あ","せる","させる","Causative form","Passive & Causative"),
	//Tai form
	TAI_FORM("Tai","い","たい","Tai form","Tai"),
	NEGATIVE_TAI_FORM("Tai-Ne","い","たくない","Negative Tai form","Tai"),
	NEGATIVE_PAST_TAI_FORM("Tai-Ne-Pa","い","たくなかった","Negative Past Tai form","Tai"),
	PAST_TAI_FORM("Tai-Pa","い","たかった","Past Tai form","Tai"),
	SUGIRU_FORM("Sugi","い","すぎる","すぎる form","Others");
	
	//Enum attributes
	private final String id;
	private final String godanForm;
	private final String godanConjugation;
	private final String ichidanConjugation;
	private final String conjugationName;
	private final String conjugationClass;
	
	private static final List<String> conjugationClasses =
			Arrays.asList(new String[] {"Casual","Polite","Volitional",
					"Hypothetical","Conditional","Conjunctive","Imperative",
					"Passive & Causative","Tai","Others"});
	private static final Map<String, VerbConjugation> conjugations;
	private static final Map<String, String[]> godanInflections;
	private static final Map<String, String[]> irregulars;
	
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
		Map<String, String[]> exceptions = new HashMap<>();
		exceptions.put("くる",new String[] {"こない","こなかった","きた","きます","きません","きませんでした","きました","こよう","きましょう"
				,"きれば","こなければ","きたら","こなかったら","きませんでしたら","きましたら","きて","こないで","きまして","きませんで","くるな","きなさい"
				,"こられる","こされる","きたい","きたくない","きたくなかった","きたかった","きすぎる"});
		exceptions.put("する", new String[] {"しない","しなかった","した","します","しません","しませんでした","しました","しよう","しましょう",
				"すれば","しなければ","したら","しなかったら","しませんでしたら","しましたら","して","しないで","しまして","しませんで","するな","しなさい"
				,"される","させる","したい","したくない","したくなかった","したかった","しすぎる"});
		irregulars = Collections.unmodifiableMap(exceptions);
	}

	//Constructors
	private VerbConjugation(String id,String godanForm, String godanConjugation, String conjugationName, String conjugationClass) {
		this(id, godanForm, godanConjugation, godanConjugation, conjugationName,conjugationClass);
	}

	private VerbConjugation(String id,String godanForm, String godanConjugation, String ichidanConjugation, String conjugationName, String conjugationClass) {
		this.id = id;
		this.godanForm = godanForm;
		this.godanConjugation = godanConjugation;
		this.ichidanConjugation = ichidanConjugation;
		this.conjugationName = conjugationName;
		this.conjugationClass = conjugationClass;
	}

	public void printSteps(Verb verb) {
		
	}
	
	public static VerbConjugation getVerbConjugation(String s) {
		return conjugations.get(s);
	}
	
	public String getGodanInflection(Verb verb) {
		switch(this.godanForm) {
			case "あ":
				return godanInflections.get(verb.getVerbEnding())[0];
			case "い":
				return godanInflections.get(verb.getVerbEnding())[1];
			case "う":
				return godanInflections.get(verb.getVerbEnding())[2];
			case "え":
				return godanInflections.get(verb.getVerbEnding())[3];
			case "お":
				return godanInflections.get(verb.getVerbEnding())[4];
			case "た":
				return godanInflections.get(verb.getVerbEnding())[5];
			case "たら":
				return godanInflections.get(verb.getVerbEnding())[6];
			case "て":
				return godanInflections.get(verb.getVerbEnding())[7];
			default:
				return "";
		}
	}
	
	public String getIrregularConjugation(Verb verb) {
		String furigana = verb.getFurigana();
		return furigana.substring(0,furigana.length() - 2)
				.concat(irregulars.get(furigana.substring(furigana.length() - 2,furigana.length()))[this.ordinal()]);
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