package com.japanese;


public enum VerbType {
	GODAN, ICHIDAN, IRREGULAR;
	
	public static boolean isVerbType(String string) {
		VerbType[] values = VerbType.values();
		for (VerbType value : values)
			if (string.equals(value.toString()))
				return true;
		return false;
	}
}
