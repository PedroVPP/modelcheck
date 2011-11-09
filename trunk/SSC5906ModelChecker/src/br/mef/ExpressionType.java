package br.mef;

public enum ExpressionType {
	NOT, AND, OR, IMP, BIC,// Logical operators 
	EX, AX, AU, EU, AF, EF, EG, AG; // Temporal operators
	
	public static boolean isLogicalOperator(ExpressionType type) {
		if (type == NOT || type == AND ||
			type == OR  || type == IMP ||
			type == BIC) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isTemporalOperator(ExpressionType type) {
		if (type == EX || type == AX || 
			type == AU || type == EU ||
			type == AF || type == EF ||
			type == EG || type == AG) {
				return true;
			} else {
				return false;
			}
	}
	
	public static boolean is_AX_or_EX(ExpressionType type) {
		if (type == EX || type == AX) {
				return true;
			} else {
				return false;
			}
	}
}
