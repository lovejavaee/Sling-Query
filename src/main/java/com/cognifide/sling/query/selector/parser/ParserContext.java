package com.cognifide.sling.query.selector.parser;

import java.util.ArrayList;
import java.util.List;

public class ParserContext {
	private final List<Selector> selectors = new ArrayList<Selector>();

	private final List<SelectorSegment> segments = new ArrayList<SelectorSegment>();

	private final List<Attribute> attributes = new ArrayList<Attribute>();

	private final List<Modifier> modifiers = new ArrayList<Modifier>();

	private char hierarchyOperator;

	private State state = State.START;

	private StringBuilder builder = new StringBuilder();

	private String type;

	private String name;

	private String attributeKey;

	private String attributeOperator;

	private String attributeValue;

	private String currentModifierName;

	private int parenthesesCount = 0;

	List<Attribute> getAttributes() {
		return attributes;
	}

	List<Modifier> getModifiers() {
		return modifiers;
	}

	String getType() {
		return type;
	}

	String getName() {
		return name;
	}

	char getHierarchyOperator() {
		return hierarchyOperator;
	}

	public State getState() {
		return state;
	}

	void increaseParentheses() {
		parenthesesCount++;
	}

	int decreaseParentheses() {
		return --parenthesesCount;
	}

	void setType() {
		type = builder.toString();
		builder = new StringBuilder();
	}

	void setName() {
		name = builder.toString();
		builder = new StringBuilder();
	}

	void setAttributeKey() {
		attributeKey = builder.toString();
		builder = new StringBuilder();
	}

	void setAttributeOperator() {
		attributeOperator = builder.toString();
		builder = new StringBuilder();
	}

	void setAttributeValue() {
		attributeValue = builder.toString();
		builder = new StringBuilder();
	}

	void addAttribute() {
		attributes.add(new Attribute(attributeKey, attributeOperator, attributeValue));
		attributeKey = null;
		attributeOperator = null;
		attributeValue = null;
	}

	void setModifierName() {
		currentModifierName = builder.toString();
		builder = new StringBuilder();
	}

	void addModifier() {
		Modifier modifier;
		if (currentModifierName == null) {
			modifier = new Modifier(builder.toString(), null);
		} else {
			modifier = new Modifier(currentModifierName, builder.toString());
			currentModifierName = null;
		}
		modifiers.add(modifier);
		builder = new StringBuilder();
	}

	void setState(State state) {
		this.state = state;
	}

	void setHierarchyOperator(char hierarchyOperator) {
		this.hierarchyOperator = hierarchyOperator;
	}

	void finishSelectorSegment() {
		segments.add(new SelectorSegment(this, segments.isEmpty()));
		attributes.clear();
		modifiers.clear();
		hierarchyOperator = ' ';
		type = null;
		name = null;
	}

	void finishSelector() {
		selectors.add(new Selector(segments));
		segments.clear();
	}

	void append(char c) {
		builder.append(c);
	}

	public List<Selector> getSelectors() {
		return selectors;
	}
}
