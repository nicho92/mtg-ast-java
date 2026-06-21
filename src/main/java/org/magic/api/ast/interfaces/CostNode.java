package org.magic.api.ast.interfaces;

import org.magic.api.ast.costs.LifeCost;
import org.magic.api.ast.costs.ManaValue;
import org.magic.api.ast.costs.SacrificeCost;
import org.magic.api.ast.costs.TapCost;

public sealed interface CostNode permits TapCost, SacrificeCost, ManaValue, LifeCost {
    String text();
}