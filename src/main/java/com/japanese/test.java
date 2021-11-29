package com.japanese;

import java.util.stream.Stream;


import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

public class test {

	public static void main(String[] args) {
		
	
		Adjective adj = new Adjective("寒い","さむい","i", new String[] {"Cold"});
		adj.printAllConjugations();
//		AdjectiveConjugation.NEGATIVE_CONDITIONAL_FORM.showSteps(adj).forEach(System.out::println);
//		System.out.println(Verb.changeVowel(c, Verb.A_FORM));
//		System.out.println(Verb.changeVowel(c, Verb.I_FORM));
//		System.out.println(Verb.changeVowel(c, Verb.U_FORM));
//		System.out.println(Verb.changeVowel(c, Verb.E_FORM));
//		System.out.println(Verb.changeVowel(c, Verb.O_FORM));
//		Verb verb = new Verb("食べる","たべる","ICHIDAN", new String[] {"to eat"},true);
//		verb.setConjugation("Tai");
//		Verb godan = new Verb("脱ぐ","ぬぐ","Godan", new String[] {"to undress"},true);
//		godan.printAllConjugations();
//		System.out.println(godan.conjugate("Cond"));
//		System.out.println(gson.toJson(verb));
	}

}
