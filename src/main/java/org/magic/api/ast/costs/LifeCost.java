package org.magic.api.ast.costs;

import org.magic.api.ast.interfaces.CostNode;

public record LifeCost(String text,int qty) implements CostNode {

}