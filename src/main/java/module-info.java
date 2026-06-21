module org.magic.api.ast {
    
    //  Required Dependencies
    requires org.apache.logging.log4j;
    requires com.google.gson;
    
    // public access
    exports org.magic.api.ast.engine;
    exports org.magic.api.ast.interfaces;
    exports org.magic.api.ast.interfaces.visitors;
    exports org.magic.api.ast.interfaces.parsers;
    exports org.magic.api.ast.abilities;
    exports org.magic.api.ast.abilities.model;
    exports org.magic.api.ast.costs;
    exports org.magic.api.ast.costs.model;
    exports org.magic.api.ast.effects;
    exports org.magic.api.ast.selectors;
    exports org.magic.api.ast.triggers;
    exports org.magic.api.ast.modifiers;
    exports org.magic.api.ast.util;
    
    // allow Gson reflections
    opens org.magic.api.ast.engine to com.google.gson;
    opens org.magic.api.ast.abilities to com.google.gson;
    opens org.magic.api.ast.abilities.model to com.google.gson;
    opens org.magic.api.ast.costs to com.google.gson;
    opens org.magic.api.ast.costs.model to com.google.gson;
    opens org.magic.api.ast.effects to com.google.gson;
    opens org.magic.api.ast.selectors to com.google.gson;
    opens org.magic.api.ast.triggers to com.google.gson;
    opens org.magic.api.ast.modifiers to com.google.gson;
}