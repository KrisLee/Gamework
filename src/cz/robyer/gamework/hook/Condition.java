package cz.robyer.gamework.hook;

import cz.robyer.gamework.scenario.BaseObject;
import cz.robyer.gamework.scenario.variable.BooleanVariable;
import cz.robyer.gamework.scenario.variable.DecimalVariable;
import cz.robyer.gamework.scenario.variable.Variable;

public class Condition extends BaseObject {
	public static final int TYPE_EQUALS = 0;
	public static final int TYPE_NOTEQUALS = 1;
	public static final int TYPE_GREATER = 3;
	public static final int TYPE_SMALLER = 4;
	public static final int TYPE_GREATEREQUALS = 5;
	public static final int TYPE_SMALLEREQUALS = 6;
	
	protected int type;
	protected String variable;
	protected String value;
	
	public Condition(int type, String variable, String value) {
		super();
		this.type = type;
		this.variable = variable;
		this.value = value;
	}
	
	public int getType() {
		return type;
	}
	
	public boolean isValid() {
		Variable variable = getScenario().getVariable(this.variable);
		boolean valid = false;
		
		if (variable instanceof BooleanVariable) {
			boolean varValue = ((BooleanVariable)variable).getValue();
			boolean condValue = Boolean.parseBoolean(value);
			
			switch (type) {
			case TYPE_EQUALS:
				valid = (varValue == condValue);
				break;
			case TYPE_NOTEQUALS:
				valid = (varValue != condValue);
				break;
			}
		} else if (variable instanceof DecimalVariable) {
			int varValue = ((DecimalVariable)variable).getValue();
			int condValue = Integer.parseInt(value);
			
			switch (type) {
			case TYPE_EQUALS:
				valid = (varValue == condValue);
				break;
			case TYPE_NOTEQUALS:
				valid = (varValue != condValue);
				break;
			case TYPE_GREATER:
				valid = (varValue > condValue);
				break;
			case TYPE_SMALLER:
				valid = (varValue < condValue);
				break;
			case TYPE_GREATEREQUALS:
				valid = (varValue >= condValue);
				break;
			case TYPE_SMALLEREQUALS:
				valid = (varValue <= condValue);
				break;
			}
		}

		return valid;
	}

}
