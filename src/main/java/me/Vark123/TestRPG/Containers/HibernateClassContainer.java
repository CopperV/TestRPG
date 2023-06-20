package me.Vark123.TestRPG.Containers;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;

@Getter
public class HibernateClassContainer {

	private static final HibernateClassContainer inst = new HibernateClassContainer();
	
	private final Set<Class<?>> classes;
	
	private HibernateClassContainer() {
		this.classes = new HashSet<>();
	}
	
	public static final HibernateClassContainer get() {
		return inst;
	}
	
}
