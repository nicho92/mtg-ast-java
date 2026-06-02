package org.magic.api.ast.costs;

public sealed interface CostNode permits ManaCost, TapCost, SacrificeCost {}