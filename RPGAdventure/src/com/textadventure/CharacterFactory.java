package com.textadventure;

import entity.Entity;
import entity.HealthComponent;

public abstract class CharacterFactory {

    public static Entity createCharacter() {
        Entity entity = new Entity();
        entity.attach(new HealthComponent());
        return entity;
    }

}
